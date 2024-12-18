package advent_2022.puzzles

import kotlin.IllegalArgumentException

fun day7() {
    var currentDir = FileTree("/", null, 0)
    val totalSize = 70_000_000
    val neededSize = 30_000_000

    input.reader().readLines().forEach { line ->
        if(line.startsWith("$")) {
            when(val command = parseCommand(line)) {
                is Command.cd -> currentDir = currentDir.moveTo(command.dir)
                else -> {}
            }
        } else {
            parseCommandResult(line)?.let { currentDir.addFile(it) }
        }
    }

    val allDirsAndSize = currentDir.moveTo("/").allDirsAndSize

    val sizePart1 = allDirsAndSize.sumOf {
        if(it.second < 100_000) it.second else 0
    }

    val sortedDirs = allDirsAndSize.sortedBy { it.second }
    val unusedSpace = totalSize - sortedDirs.maxOf { it.second }
    val part2 = sortedDirs.first { (unusedSpace + it.second) >= neededSize }

    println("December 7th (Part 1) : $sizePart1")
    println("December 7th (Part 2) : $part2")
}


private fun parseCommand(line: String) : Command =
    line.split(" ").let {
        when(it[1]) {
            "ls" -> Command.ls
            "cd" -> Command.cd(it[2])
            else -> throw IllegalArgumentException("Command not found")
        }
}

private fun parseCommandResult(line: String) : File? =
    line.split(" ").let {
        if(it[0] == "dir") return null

        File(it[1], it[0].toInt())
    }

class FileTree(val name: String, val parent: FileTree?, val depth: Int) {
    val files: MutableList<File> = mutableListOf()
    val dirs: MutableList<FileTree> = mutableListOf()

    val filesSize: Int
        get() = files.sumOf { it.size } + dirs.sumOf { it.filesSize }

    val allDirsAndSize: List<Pair<String, Int>>
        get() = mutableListOf<Pair<String, Int>>().apply { add(name to filesSize) }.apply { dirs.forEach { addAll(it.allDirsAndSize) } }

    fun moveTo(dir: String) : FileTree =
        if(name == dir) this
        else if(dir == "..") parent ?: this
        else if(dir == "/") parent?.moveTo("/") ?: this
        else {
            dirs.firstOrNull { it.name == dir }?.let {
                return it
            } ?: run {
                val newDir = FileTree(dir, this, depth + 1)
                dirs.add(newDir)

                return newDir
            }
        }

    fun addFile(file: File) {
        files.add(file)
    }

    override fun toString(): String =
        buildString {
            repeat(depth) { append("   ") }
            append("- $name (dir)\n")
            files.forEach { append(it.toString( depth + 1)) }
            dirs.forEach { append(it.toString()) }
        }
}

data class File(val name: String, val size: Int) {

    fun toString(withDepth: Int = 0): String =
        buildString {
            repeat(withDepth) { append("   ") }
            append("- $name (file, size=$size)\n")
        }
}

sealed class Command {
    object ls : Command()
    data class cd(val dir: String) : Command()
}





private val testInput = """
    ${'$'} cd /
    ${'$'} ls
    dir a
    14848514 b.txt
    8504156 c.dat
    dir d
    ${'$'} cd a
    ${'$'} ls
    dir e
    29116 f
    2557 g
    62596 h.lst
    ${'$'} cd e
    ${'$'} ls
    584 i
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd d
    ${'$'} ls
    4060174 j
    8033020 d.log
    5626152 d.ext
    7214296 k
""".trimIndent()

private val input = """
    ${'$'} cd /
    ${'$'} ls
    dir bjrbjh
    dir dppgvlh
    dir fcfqp
    dir mtbt
    95962 mzvb
    dir qtfmf
    dir sfjrs
    dir trtl
    ${'$'} cd bjrbjh
    ${'$'} ls
    80731 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd dppgvlh
    ${'$'} ls
    180122 bbjw
    210923 ctprm.bpc
    304465 hhg
    dir rtdnhb
    ${'$'} cd rtdnhb
    ${'$'} ls
    295880 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd fcfqp
    ${'$'} ls
    dir cts
    dir gjzdf
    61601 hqvhrpnv
    27922 hqvhrpnv.sgf
    dir hvsnr
    191405 mzvb
    263646 nbjp.fdm
    dir qmsllmtw
    dir rpvstz
    dir vbhh
    dir zwllwsq
    ${'$'} cd cts
    ${'$'} ls
    47983 nhzpb
    ${'$'} cd ..
    ${'$'} cd gjzdf
    ${'$'} ls
    dir fcfqp
    161310 vtrhs.mlh
    ${'$'} cd fcfqp
    ${'$'} ls
    145412 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd hvsnr
    ${'$'} ls
    dir sftjlqbm
    ${'$'} cd sftjlqbm
    ${'$'} ls
    96517 qlgp
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd qmsllmtw
    ${'$'} ls
    172998 hhg
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    dir nccm
    dir tsstr
    ${'$'} cd nccm
    ${'$'} ls
    181998 fcfqp
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    258571 tsstr
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd vbhh
    ${'$'} ls
    317169 ctprm.bpc
    dir rpvstz
    dir scsclh
    307868 vcbnl
    118337 zbltwtj
    ${'$'} cd rpvstz
    ${'$'} ls
    dir fcfqp
    dir nzg
    dir pmhprnbb
    dir szshbn
    ${'$'} cd fcfqp
    ${'$'} ls
    dir hrhftz
    ${'$'} cd hrhftz
    ${'$'} ls
    163301 vlrjptvv.fsr
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd nzg
    ${'$'} ls
    230765 hhg
    235220 ptg.dbs
    dir qsbpc
    220737 vtttmhf.dcl
    ${'$'} cd qsbpc
    ${'$'} ls
    74559 hqvhrpnv.phh
    322306 tqb.wnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd pmhprnbb
    ${'$'} ls
    293892 hhg
    ${'$'} cd ..
    ${'$'} cd szshbn
    ${'$'} ls
    303519 tsstr.pml
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd scsclh
    ${'$'} ls
    dir rdznzm
    ${'$'} cd rdznzm
    ${'$'} ls
    dir hthgb
    dir vgfhz
    270085 zjlcp
    ${'$'} cd hthgb
    ${'$'} ls
    2762 gjgczrpm.hlw
    ${'$'} cd ..
    ${'$'} cd vgfhz
    ${'$'} ls
    160621 svplfhqh.rfr
    27592 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd zwllwsq
    ${'$'} ls
    dir bmgpnmbt
    dir fbphbmbb
    217873 hhg
    dir hqvhrpnv
    ${'$'} cd bmgpnmbt
    ${'$'} ls
    dir fcfqp
    67327 hhg
    175579 qrgrtg.gtn
    185356 rdrtvn
    ${'$'} cd fcfqp
    ${'$'} ls
    16285 wqtnzw.cvj
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd fbphbmbb
    ${'$'} ls
    dir fpnwjb
    dir hqvhrpnv
    dir jbm
    dir jsfscjd
    dir qmpbb
    dir sjhrg
    dir tsstr
    79487 tsstr.czd
    226172 tvdpb.vss
    ${'$'} cd fpnwjb
    ${'$'} ls
    78102 pgv.snz
    32886 wqtnzw.szw
    ${'$'} cd ..
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    247435 ctprm.bpc
    dir drpffn
    dir fvqzjjhp
    312573 hqvhrpnv
    103964 swbvbwd
    dir szdbbtw
    129434 trjpn.htm
    33772 vgjnhmbc.hcr
    dir vvrhmrbs
    ${'$'} cd drpffn
    ${'$'} ls
    204660 wdhl.dgs
    ${'$'} cd ..
    ${'$'} cd fvqzjjhp
    ${'$'} ls
    dir qtc
    ${'$'} cd qtc
    ${'$'} ls
    74576 wgfffz
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd szdbbtw
    ${'$'} ls
    dir fcfqp
    ${'$'} cd fcfqp
    ${'$'} ls
    56006 bthq.wlm
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd vvrhmrbs
    ${'$'} ls
    dir sqwfj
    21051 vcbnl
    ${'$'} cd sqwfj
    ${'$'} ls
    dir chjpwmb
    170226 fcfqp.wrn
    ${'$'} cd chjpwmb
    ${'$'} ls
    305407 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd jbm
    ${'$'} ls
    138219 fcfqp
    dir jwbrhmzj
    67905 mbvpfmgs.lbq
    125465 mzvb
    dir tsstr
    211711 vrqpss.qtr
    ${'$'} cd jwbrhmzj
    ${'$'} ls
    22219 zqmldh.jwc
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    141377 ctprm.bpc
    28965 jhwr.tvf
    dir rmbpb
    ${'$'} cd rmbpb
    ${'$'} ls
    185176 mzvb
    204877 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd jsfscjd
    ${'$'} ls
    210670 jqwcrvg
    263796 qcv.crz
    23224 slcgw.hmz
    dir tws
    ${'$'} cd tws
    ${'$'} ls
    244925 dnrswnh
    307737 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd qmpbb
    ${'$'} ls
    175766 fcfqp.pgc
    155950 nnrgl.qtd
    215226 rjjw
    218856 rpvstz.cls
    ${'$'} cd ..
    ${'$'} cd sjhrg
    ${'$'} ls
    dir dbdc
    dir jprmnvv
    dir wbqrzrcd
    ${'$'} cd dbdc
    ${'$'} ls
    159323 cqfmgtr.fpp
    ${'$'} cd ..
    ${'$'} cd jprmnvv
    ${'$'} ls
    314162 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd wbqrzrcd
    ${'$'} ls
    177804 mzvb
    11757 wfmhd.srn
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    303871 hhg
    311124 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    75235 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd mtbt
    ${'$'} ls
    277478 bqvdgj.tdt
    dir gtgdhl
    100015 hnmvhb.dqb
    dir hqvhrpnv
    dir pdhn
    dir ptmpqgj
    dir qfdmhqhm
    dir tsstr
    178843 tsstr.crd
    dir ttjltb
    45660 vbqjdj.znj
    204359 vch.zrz
    ${'$'} cd gtgdhl
    ${'$'} ls
    305753 hsjc
    ${'$'} cd ..
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    131065 cvt.pwb
    dir dzrlrdc
    dir fcfqp
    175755 hqvhrpnv.gjg
    313719 jjpnjhqz.wtf
    3622 nhr.vtv
    dir spz
    ${'$'} cd dzrlrdc
    ${'$'} ls
    65797 wqtnzw.tpr
    ${'$'} cd ..
    ${'$'} cd fcfqp
    ${'$'} ls
    141146 hhg
    ${'$'} cd ..
    ${'$'} cd spz
    ${'$'} ls
    292906 gjgczrpm.hlw
    202319 gjn.ptw
    311120 vcbnl
    93748 wqtnzw
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd pdhn
    ${'$'} ls
    dir rjgsq
    ${'$'} cd rjgsq
    ${'$'} ls
    424 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ptmpqgj
    ${'$'} ls
    10127 mdnllcs
    178992 npbr
    dir rpvstz
    dir wqtnzw
    ${'$'} cd rpvstz
    ${'$'} ls
    248549 gjnpwldn.jsh
    31443 nstp.jpj
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    47276 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd qfdmhqhm
    ${'$'} ls
    254322 wqtnzw.qrn
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    265555 cqbs.thq
    273707 ggnr
    ${'$'} cd ..
    ${'$'} cd ttjltb
    ${'$'} ls
    82810 cmln.qlj
    23429 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd qtfmf
    ${'$'} ls
    108080 hqvhrpnv
    dir hrl
    dir mnb
    85284 qrfjg
    dir rfghjdj
    dir tsstr
    dir wqtnzw
    ${'$'} cd hrl
    ${'$'} ls
    dir dwgnv
    dir lgn
    dir qhcjcc
    dir rpvstz
    dir zrqf
    ${'$'} cd dwgnv
    ${'$'} ls
    dir mrhvqqc
    ${'$'} cd mrhvqqc
    ${'$'} ls
    113150 qbhmdfwg.wrt
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd lgn
    ${'$'} ls
    293311 gzzdwd.wnn
    ${'$'} cd ..
    ${'$'} cd qhcjcc
    ${'$'} ls
    298893 fcfqp
    253573 lvb.brw
    301515 ndbrjbw.ssq
    236001 vmdgmm.gmh
    124715 wqtnzw.cqd
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    dir hqvhrpnv
    dir rpvstz
    dir tbdnjbmr
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    271956 vrtgr.vdt
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    185836 cjcf.rwc
    289321 vcbnl
    ${'$'} cd ..
    ${'$'} cd tbdnjbmr
    ${'$'} ls
    124297 ctprm.bpc
    323010 hhg
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd zrqf
    ${'$'} ls
    dir fcfqp
    dir vrp
    ${'$'} cd fcfqp
    ${'$'} ls
    1896 cdfldlv.ptw
    ${'$'} cd ..
    ${'$'} cd vrp
    ${'$'} ls
    5610 fmmvbft.rjq
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd mnb
    ${'$'} ls
    64404 ctprm.bpc
    dir tsstr
    ${'$'} cd tsstr
    ${'$'} ls
    172859 gjgczrpm.hlw
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rfghjdj
    ${'$'} ls
    78613 cpfclgj.wqh
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    dir cjdbwvn
    135418 gjgczrpm.hlw
    dir lsv
    ${'$'} cd cjdbwvn
    ${'$'} ls
    dir hqvhrpnv
    264636 wqtnzw.gcq
    124125 zvpwlrbr.nnz
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    43899 tdzsmzw
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd lsv
    ${'$'} ls
    202987 hhg
    251479 vcbnl
    dir vdltrlzg
    ${'$'} cd vdltrlzg
    ${'$'} ls
    dir nlchndbr
    ${'$'} cd nlchndbr
    ${'$'} ls
    134010 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    dir hqvhrpnv
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    50703 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd sfjrs
    ${'$'} ls
    dir cgl
    dir cjjls
    184472 hhg
    241700 pdswzfq.scs
    dir rfwnnr
    dir rpvstz
    dir tsstr
    ${'$'} cd cgl
    ${'$'} ls
    dir tdwn
    ${'$'} cd tdwn
    ${'$'} ls
    119119 lzqfb.tgc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd cjjls
    ${'$'} ls
    dir hgppstcv
    ${'$'} cd hgppstcv
    ${'$'} ls
    223932 bvt
    85898 hhg
    195499 ppbrb.vtq
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rfwnnr
    ${'$'} ls
    96712 gmzchms.wst
    271527 mzvb
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    78920 cbj.mlh
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    17373 bdlrvwv
    50170 vcbnl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd trtl
    ${'$'} ls
    dir dflwctzs
    dir gsl
    168166 hhg
    dir jlfrcp
    97874 mzvb
    dir pfnlc
    dir pjj
    186837 qzlwgts
    132833 tnb.mpv
    dir vmjljc
    ${'$'} cd dflwctzs
    ${'$'} ls
    6196 fcfqp.qcg
    181057 mrjvtvl.hmm
    ${'$'} cd ..
    ${'$'} cd gsl
    ${'$'} ls
    dir gmlp
    dir mjrs
    dir rpvstz
    91777 wqtnzw.hpl
    ${'$'} cd gmlp
    ${'$'} ls
    167819 qmtmtppc
    254187 sbn.chs
    119156 tbhhgmz.vqs
    31966 tptqzrqr.zgh
    ${'$'} cd ..
    ${'$'} cd mjrs
    ${'$'} ls
    167756 fcfqp.jgz
    221559 tzj.rfb
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    93065 hhg
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd jlfrcp
    ${'$'} ls
    dir fcfqp
    dir hmlmvqc
    256413 mrtwp
    141186 mzvb
    106340 nvwfhv.rvt
    dir pqmfswh
    dir qghq
    274275 qmmgnjh
    dir trhs
    dir tsstr
    55881 wbtf
    ${'$'} cd fcfqp
    ${'$'} ls
    265006 fcfqp.dtr
    dir tdflqz
    dir thjdsqmw
    136055 vshqb
    ${'$'} cd tdflqz
    ${'$'} ls
    dir dtvnrl
    ${'$'} cd dtvnrl
    ${'$'} ls
    277524 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd thjdsqmw
    ${'$'} ls
    dir chhf
    dir hsnrrc
    218719 qgt.sdb
    dir tsstr
    134210 twvn
    dir wqtnzw
    ${'$'} cd chhf
    ${'$'} ls
    6278 ctprm.bpc
    ${'$'} cd ..
    ${'$'} cd hsnrrc
    ${'$'} ls
    dir bphrpw
    dir dmjvnrhz
    dir rpvstz
    dir wqtnzw
    ${'$'} cd bphrpw
    ${'$'} ls
    dir brj
    dir hqvhrpnv
    dir rpvstz
    ${'$'} cd brj
    ${'$'} ls
    13067 rpvstz.bqh
    ${'$'} cd ..
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    148840 ctprm.bpc
    dir fsvgbd
    94116 hghq.gqm
    ${'$'} cd fsvgbd
    ${'$'} ls
    222743 ctprm.bpc
    80460 vwp.nnq
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    287030 ctprm.bpc
    145932 rhpt
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd dmjvnrhz
    ${'$'} ls
    dir mlh
    ${'$'} cd mlh
    ${'$'} ls
    238301 tsstr.vfl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    59172 mcv
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    50806 tlwnzqgb.dqq
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    dir rpvstz
    ${'$'} cd rpvstz
    ${'$'} ls
    109284 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    dir hwpdsfg
    dir mrn
    183742 rpvstz.pdq
    ${'$'} cd hwpdsfg
    ${'$'} ls
    295333 vbbrvhqm.mvj
    ${'$'} cd ..
    ${'$'} cd mrn
    ${'$'} ls
    46589 mzvb
    235773 pppwz
    69304 rsrbq.qdl
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd hmlmvqc
    ${'$'} ls
    77344 ctprm.bpc
    291828 hhg
    226384 rpvstz.qfl
    10758 tpmdrpg.hcj
    ${'$'} cd ..
    ${'$'} cd pqmfswh
    ${'$'} ls
    127361 bczcpjln.ffs
    208033 ctprm.bpc
    144048 hqvhrpnv.bzm
    88561 pgf.ltz
    149879 twj.drs
    ${'$'} cd ..
    ${'$'} cd qghq
    ${'$'} ls
    165758 hqvhrpnv.mqb
    ${'$'} cd ..
    ${'$'} cd trhs
    ${'$'} ls
    dir bnrqbrv
    177929 rlnln.gcv
    60631 rpvstz
    dir snd
    ${'$'} cd bnrqbrv
    ${'$'} ls
    150034 ltjjfp
    ${'$'} cd ..
    ${'$'} cd snd
    ${'$'} ls
    21614 hqvhrpnv
    95241 wvtc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    dir cdvgvc
    dir mbmn
    dir mprbjtnd
    dir rvwqgn
    8263 tsstr
    dir tvtddch
    ${'$'} cd cdvgvc
    ${'$'} ls
    dir gvssnh
    121417 hqvhrpnv
    180690 hqvhrpnv.dnl
    dir nhrvzn
    266104 psj.fdv
    dir rpvstz
    278434 rww.nbt
    dir wqtnzw
    ${'$'} cd gvssnh
    ${'$'} ls
    20979 fpqgb.nbl
    ${'$'} cd ..
    ${'$'} cd nhrvzn
    ${'$'} ls
    dir hqvhrpnv
    dir nlv
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    202973 hhg
    ${'$'} cd ..
    ${'$'} cd nlv
    ${'$'} ls
    235158 hqvhrpnv
    246378 hqvhrpnv.vmg
    278166 rfzjcbpv.crc
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    181642 tsstr.wjh
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    dir pgnzftwn
    ${'$'} cd pgnzftwn
    ${'$'} ls
    dir njtgddhw
    217983 sqwmjb
    149152 wqtnzw.pzv
    ${'$'} cd njtgddhw
    ${'$'} ls
    75203 fvbr
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd mbmn
    ${'$'} ls
    82237 hhg
    36010 hqvhrpnv.hcf
    ${'$'} cd ..
    ${'$'} cd mprbjtnd
    ${'$'} ls
    23927 gbrvbz.rgc
    20857 hqvhrpnv
    203364 zpbhr.svj
    ${'$'} cd ..
    ${'$'} cd rvwqgn
    ${'$'} ls
    291142 fcfqp.mdf
    124256 gjgczrpm.hlw
    235579 mzvb
    ${'$'} cd ..
    ${'$'} cd tvtddch
    ${'$'} ls
    317689 dshzl.plf
    44518 vphnqd
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd pfnlc
    ${'$'} ls
    dir vdsjh
    dir wfvgc
    ${'$'} cd vdsjh
    ${'$'} ls
    202584 pzs
    ${'$'} cd ..
    ${'$'} cd wfvgc
    ${'$'} ls
    315910 ctmrb.qsq
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd pjj
    ${'$'} ls
    242824 qhngjlvt.pcb
    dir wzv
    ${'$'} cd wzv
    ${'$'} ls
    221150 clrchzw.vbj
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd vmjljc
    ${'$'} ls
    dir blm
    31640 dbtnn
    dir fcfqp
    dir frjdzh
    dir scjhzc
    dir tmz
    dir vpvh
    dir zcwpb
    ${'$'} cd blm
    ${'$'} ls
    249527 hjsjqbw.rst
    dir hqvhrpnv
    dir pvnrmvb
    dir pvt
    dir rjpcb
    161576 slp.gbn
    dir srrzgt
    97355 wtps.brr
    ${'$'} cd hqvhrpnv
    ${'$'} ls
    dir tsstr
    dir wqtnzw
    dir zphvcch
    ${'$'} cd tsstr
    ${'$'} ls
    dir fzrpgcn
    ${'$'} cd fzrpgcn
    ${'$'} ls
    10603 hhg
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd wqtnzw
    ${'$'} ls
    56612 ctprm.bpc
    22521 fljtffmz.rcl
    ${'$'} cd ..
    ${'$'} cd zphvcch
    ${'$'} ls
    dir fcfqp
    ${'$'} cd fcfqp
    ${'$'} ls
    171787 ctprm.bpc
    134010 mzvb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd pvnrmvb
    ${'$'} ls
    100624 fcfqp.nzt
    120945 mzvb
    ${'$'} cd ..
    ${'$'} cd pvt
    ${'$'} ls
    dir lslcvfv
    8511 mzvb
    81176 srnqlrn.lvg
    315976 ttzwwnn.fmz
    dir wvhrf
    ${'$'} cd lslcvfv
    ${'$'} ls
    dir gmj
    13666 tcbf
    ${'$'} cd gmj
    ${'$'} ls
    174220 drpztfvs.shg
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd wvhrf
    ${'$'} ls
    19033 gqggsrc
    270841 hhg
    dir nvvpbmgm
    ${'$'} cd nvvpbmgm
    ${'$'} ls
    241543 pzht
    72906 rmhrd
    dir tllpvrdv
    ${'$'} cd tllpvrdv
    ${'$'} ls
    309970 lmqcbbz
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd rjpcb
    ${'$'} ls
    215654 fcfqp.gjw
    241891 gjgczrpm.hlw
    dir gqql
    101620 sqjtc
    ${'$'} cd gqql
    ${'$'} ls
    214991 wctr.qnb
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd srrzgt
    ${'$'} ls
    dir bwfnrbs
    147738 hhg
    dir qdctrw
    dir svpm
    dir tsstr
    ${'$'} cd bwfnrbs
    ${'$'} ls
    133922 gpsmlzbp.ghd
    ${'$'} cd ..
    ${'$'} cd qdctrw
    ${'$'} ls
    56213 ppp
    ${'$'} cd ..
    ${'$'} cd svpm
    ${'$'} ls
    205352 hhg
    ${'$'} cd ..
    ${'$'} cd tsstr
    ${'$'} ls
    248839 fcfqp.hlg
    299835 lfdlfrhs.vjz
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd fcfqp
    ${'$'} ls
    dir ctbbgw
    dir rpvstz
    265076 wqtnzw.cts
    ${'$'} cd ctbbgw
    ${'$'} ls
    306172 vcbnl
    ${'$'} cd ..
    ${'$'} cd rpvstz
    ${'$'} ls
    40277 hhg
    225110 mllhtfm.jwd
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd frjdzh
    ${'$'} ls
    69285 jlscvz.lsw
    ${'$'} cd ..
    ${'$'} cd scjhzc
    ${'$'} ls
    221073 gjgczrpm.hlw
    ${'$'} cd ..
    ${'$'} cd tmz
    ${'$'} ls
    35287 fcfqp
    146224 mnz.zwd
    189997 mzvb
    dir tsn
    31655 ttwjfbl.jbb
    dir wjszmnp
    170705 wqtnzw.slq
    ${'$'} cd tsn
    ${'$'} ls
    196121 mzvb
    ${'$'} cd ..
    ${'$'} cd wjszmnp
    ${'$'} ls
    dir pbtmjghf
    dir smbbnl
    ${'$'} cd pbtmjghf
    ${'$'} ls
    151036 vcbnl
    ${'$'} cd ..
    ${'$'} cd smbbnl
    ${'$'} ls
    299800 mbt
    dir wqtnzw
    ${'$'} cd wqtnzw
    ${'$'} ls
    203547 hhg
    220352 wqtnzw.dgs
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd vpvh
    ${'$'} ls
    69506 vbgrhzjs.tdb
    ${'$'} cd ..
    ${'$'} cd zcwpb
    ${'$'} ls
    73403 fcfqp.hpq
""".trimIndent()