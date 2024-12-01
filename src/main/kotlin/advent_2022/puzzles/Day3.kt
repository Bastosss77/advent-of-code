package advent_2022.puzzles

//7701, 2644

fun day3() {
    println("December 3rd (Part 1) : ${partOne(input)}")
    println("December 3rd (Part 1) : ${partTwo(input)}")
}

private fun partOne(input: String) : Int {
    var totalPriority = 0

    input.reader()
        .readLines()
        .forEach { bag ->
            val (firstPocket, secondPocket) = bag.asIterable().chunked(bag.length / 2).apply {
                first() to get(1)
            }

            totalPriority += firstPocket
                .intersect(secondPocket.toSet())
                .sumOf { if (it.isLowerCase()) it.code - 96 else it.code - 38 }

    }

    return totalPriority
}

private fun partTwo(input: String) : Int {
    var priorities = 0
    
    input.reader()
        .readLines()
        .chunked(3)
        .forEach { group ->
            var currentRes: Set<Char> =  group.first().asIterable().toSet()

            for(i in 1 until group.size) {
                currentRes = currentRes.intersect(group[i].asIterable())
            }

            priorities += currentRes.sumOf { if (it.isLowerCase()) it.code - 96 else it.code - 38 }
        }

    return priorities
}

private val testInput = """
    vJrwpWtwJgWrhcsFMMfFFhFp
    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
    PmmdzqPrVvPwwTWBwg
    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
    ttgJtRGJQctTZtZT
    CrZsJsPPZsGzwwsLwLmpwMDw
""".trimIndent()

private val input = """
    MVWpzTTrTFNNLtssjV
    hRJncnJCnhPCnBSbCQRhhQRPFHmsbHLzbLNHsjNNFmGGGsGF
    lSBQJBBBBcnccnQvBnPQznfrgwlrTZfDwTfWqrrpgMpw
    sRPgrzSgrSbfTrgspBPsDWWTmdnvdZWZwTmwvdmd
    tVGpCGqCGjlHcNGVNHZDmnZMWdWMWCdZDvnZ
    HqpQptLlclLGtlpcjHNhQqfRhrSBrrbfbrSRrsPfBSgg
    JpjLbQbFPBjDBBJLWltglfBfhhlcctht
    vNFmsdFsnmzGtWvgzhzc
    rqwRCCqmCTqHCnqRNTNFsJVMQSjLRbbVVbjQVLbLSV
    mLNNCNDwBwDnmCwnJwLRvdlqZclRccsgvcZndc
    QWMtVWbpVlgHHcgMHs
    VsTVWhThsVQWzjtQPpVWjWbpwJNCJDCzSNNCCCSfmfBCSGLL
    NbSfHnwDvwwfHwwQsHbWPgrsZsZjRPLRgLWhWP
    lmMlTGFzVmzqjGLLZWWGhrCh
    qFclMprqmrvbcnwDQtNQ
    tWQZFvvtWQWbqQQggZZLvpLrpzDrmGDmmDHPzPzHrfnHTG
    NMlhlTMccTCVBlRNHzJnzDDmnJmnGGBf
    NMMSSSSSlNVMdjdNSNNhFwTbvbLqjbtLwWQwZqgg
    jPwcJwRmmhJpbhNJVgDbrHzzzQzzBQHg
    tdZqlCnnnlvZCqlnlCSqZdFCHGDBgzsDzssBtHGLQtrHsssL
    TZZFSdrdlZMFZRMwMPmNcwNmwm
    nsdhzmDBGQWQPvJPjbbW
    gCgBqCNpMHTwgwqMPRJJTtWjbFRJFJvP
    ZlCwBrwgmzhGzDrd
    sTBHfcnBTnqHRvqPgFFbLtrQTPLjjm
    GWzpwSJSpbwbNNGJPQrrtrrrrgzLtjzm
    NNSlCCdplWwplCwSndnssdZfqVbHvfqc
    rrfHgqnlllRrDgrCbQfszMPtmzPQzFsFMQ
    JJLGVGjcwVcPQNNNtRPmLM
    WThVJJWJBdGwBpBTqDrSRCCggShqbSCb
    TbCqzqzmbCffzDbHRddLbdRFRS
    ZmvZJPjPwwWNZJGtWSDRRDFWSLhhhMVVWL
    ZwplGwmptNjZnjvnGGPjJlZppTrTsCczfggBgfgfggCsBrqr
    gMBBbfBbBMfnMsvRvWJhDsQW
    ZZqHLzczjjsLzlpjqTprNJvhQpRvtRJJQrtQtJ
    llZlzZZzPZZqsTZscHczfnSwSPSwwgSBwwSnPwnf
    chMtcPPgQtthqgvczhMTcCSBLBlGpsFnBnnsGvLplSFG
    bhRmJWDRmHSmFGBnGBps
    jdDZWbrDdDbbdbDrRRWwRjZRVqztCzqtcThcTQtgqVMMVzjq
    flNmNHgcZwTzRLzMLRPlzz
    nntqBJtFbbCbBVCnBtFjJjhVhLzLvRLvgRRvPvpMpvpp
    CgJWjJQDjgBtnGNGfcssNfcwNW
    MrMpMrGBznjPMGCmCrrjdwndfJLQNfdLQNdNggdL
    sJVcZqvhZtVqhDFFsDJslcdwgwvwQwwQNbbTbwfLLTgL
    RDDRcsSsstJstVDDqctszzmRmGjpjBRHPmmGHGrj
    tdplZtlrBGwTlLQQ
    sfsPPvNhWLQBhGQG
    zPVfzVbbMcscvVfzzNgcJHnJZgtrrndJqjJqndrL
    nglLjRCCHLLCnNCLHQnFNQmmVMbVmwMwlMwMMMWwBTsT
    cqtfcqZpzhSvvBfWwbvrbT
    PPqpDSqcSJPdPhtPtqZcpPtGjFRFFFNLJFGRgjFbGRNbHn
    gjtRSLMqLdSgLMCltTSDQcQQqhDcfcfrWDhWrr
    GwFZCwNzFJsPmFFmZmPPNhvDDfDWWmpvQWWfVDQppW
    swNGZbPBGwnCgBBlBljl
    BsrDsnQGwFFQQtfNTBNSffBgBt
    VJlhWVLlRppLQZTCbtZTttgJ
    ppLqqRhQdRPhqPVhdPjhljqHFnGnzFrjFFDznHFHrmwwnH
    CJMmmJLmlshCCdmzjHjPWztgdnjttt
    GwZvGwrgTcFpzHWjnT
    GbqrvrRwbrbGbwwZBbgfhmJMmsDJhNRfLChhCh
    CfgfjCLCgfgFgBhBsccswQwtsQHvBBtc
    SbSMGbnmDMGJWmRmDmvzwtcscWtQzsrPsvHc
    DbJdNpJSnMSJmpSSNVqgqgTTFVQTFqdZLq
    MBMCmlllPSSlmmPPjCMpPgggJcnZgntJsDvHsDZt
    hrNzhrRNbrhbGRbfpVLRGNqqngvnttngJctgDZJGcZvHvZnc
    LTTzVqppSmwdTmQW
    mmlBQmLbsbmRnFnwlqqprF
    dZScZSZSdHcNMDcJwLqRfppzpzfTpHfF
    JLWJLJJJdMmGtgCWjQjt
    PPMzpVDblwGVMMzDLLjrcrjdzjdTzLjd
    RRcCJRcNQRBqtCFBRJJsZWBWrjLWLHZZWndBLWjh
    qQQtttNqsqqtJRgqQfcpcgDcbggplGbfMp
    QmmSTQPmLSmjpczMJtwPzg
    BHHHdwdvDpllvctjZv
    HdrDHNfrrBDGGBhBNfHNsLFqmbRQSwqmGTLnTbSF
    gcMmgRQPqqPPsgjFSvctCHvHllSSHcvd
    JTWfZwhTwzbWwTFTrZnTrDDlDSVtVHLVShtvSHvlHL
    bWbWBfzTfwrWJNbTrnzfTwJFpmMQgqsFRsQGqRMggPmPBG
    GqCWpGGLpmpWjbSDGjGGmwCzZlvMBTrCvsrlwwswCl
    FPFHFVdJgQHJZnslrgvsTrwMlNgw
    hQVchcdcZZpZqcDG
    JbBRgBsRffgPPFQttQvQQMvG
    dmNZgmZVtGTNtNGC
    ZqqndLZnccqRbsrgpggsBc
    DDvMVmTjwFWPBBTzBF
    cqnggcbNNCqbQQqbZbpfQpqgRWlFhLRBhRzRPLFJhlJBfPLh
    dpscpcncbbqcpMVSvSrPDMsrjr
    hGCGZmVRRcMVsGMtmZWssmFLzbFblnnzfmqbfnzNNb
    wjrSPBJdSjjDrggpSJpdrSnlFNlzLTnqNLqqpbqqfMln
    dMwPBHPPJHDdvrBBhshWCGcsQRcHRZGV
    vdHwhqdtLdVnHBZbFBFzbBPS
    TmNCLNDpWfCNmpCgTWNTDMMZlzSggBMzlZlMBlBbZZ
    NQfWcscDNQrhqvGLrdhVjh
    lZLqzzqvgrCRcQcCLD
    HSVVwNTJzwVNTDQrRrrdrBwdhd
    TpNTzsfSTVsSpHVppzFpgvvlqZWZPMvMjPPGGsgP
    BCMLshLdLDDCgwFRwHHqqRqRWd
    QnSqQlGSfpQzTQJNTPNwNPFRFcccHc
    mfJJmztnQpGpvnSzGrsqgrhrBBhqjBrthL
    BSlmzmlvdNnlQlQQJnJHRVFVFVVqMtqRMfSfCw
    WBPsDPBBjfssFHMRRq
    DLWpGhbPjbhZrhZDnBQgdNZmQNgmvdzg
    WWvgBFgHWChBzgBFbjbtPtnPrsHlsRMrwRrMRR
    SGfNpfdGfVpVSGGppSDdwRwclMlfPMwccsntPqPw
    TVdpQDSpQZJpVpDQQFBbthvmWzmgvhbjzJ
    VVCCbzqdbzhFHvbdhZFPmhCPSNRNGSrPJfTNRSGJfGwPST
    LngtBnlcnDvLcTTRfTTwRtGNTG
    DpnBjMpLlLDQWDgvpLvbqzmbjzVjVHqbFFqbFq
    SbzMbNQQSDdmvqqzdSlWFpwZnvpFWWllpFww
    CjLPTPjjLCPtBCLJjBLPLBTFsFFgfwwpZgplpgFnWWRl
    nPncrBHGnmrbdmdmNN
    FnlblGlTTbNVLVtRvQQvgqRQBCvgNr
    DPMDMpMHmnzjPqDhQWvvQvhghq
    zMMcddznsjFTldVGlFGT
    cLSNGLhmRRVmlVCq
    HvzbQBzBMQMpQDpCSlSVZRSCqV
    QwWznWnTbQSMMJQHnvwbWjrhNhLFgsGNNrFLNnFNhd
    dBrWNQWWcTNqqnNN
    bPlmgRgRghlCVlbhwZccCZjZqvmqvmTTvGqmJTvqnGTGvLLJ
    DCDZhjllcpDMrSQS
    ddtNNTFTwRzGRGCwqnBMjlqMHMfqnB
    hDpPsQLLSprhnHVhqhVfHM
    QWLWDQZpgpWbQgfspGGRdcGcCcCcztTGZC
    GGHFdGwFlswcFtnvTfjMjBFfNBjNBZ
    JWmSJLPSRprWWPWVMMVQpZfBvvfQtj
    RzPSPDbDDhbhPDLRhCgGHHccsqGCqqtHzG
    dbSdptWddDMNtdFvttFclqMTZJlJTlMZqJTJTqjC
    BzfwRzrwPzfzLNGmZZCZBTGBqqlH
    hVNVQPNQQQVLPwhRrQwgWWvFdDsFWSbdWgdFSFDb
    hhSnmhtZSFSqZBJSSqqmJJRHPPLgHtPcGGGcWGtvvHwgvG
    fCMpfTQjTrzrzCQMsQdHGHvPGPwLppPRvWPLLc
    MdTzCsCMzNzDCTjlmNhRmRnZBllRVh
    RrFglccgBVVvFNvCvWlgmDbbDfQDtCdjjDbDwmZD
    STnMqSLHJhHHnqLqtnBndbBdfQQZDtZD
    GHJPTBsTSsMMSpSBHJFNlWzcvFlzsVzvzgsv
    lplNdrVrVrWMMVcJfcDDzbqCCpDL
    SSSgvBRSjggPgzvTTRHTvFnfJLbcLsCDLnCLDCcJBsJq
    GmHjjRwvHSjHTRjrlZrNMzVMhVrmVW
    gdtFtgStSbHCbHMPZrFLPLrVlrVZrP
    hQnjMGfDqTvzvpBjVVjPRLRRjJ
    QmsQmhvvMtssHtWw
    RNjTGSCLJCGdRqMRFvMrfzMvzz
    ZpcWcVDpWBmWQMZZpZDpwBcznrshtntvfvhfFtzmvnzvhf
    WHHHcVWgQVCbCllbgMLN
    ZjjdJHSdSzvcZFMhhhDqDHtthw
    rNTlNqVWTmRPlshsDPDlph
    WbTNGNmQBRQbRNQgNGmCLdvvjzcCSBqLcLnScL
    bZwpSpBvSHCBqNzpdFffqQft
    nWRnGRnVnljmlDnzdPfQfdcQPWWfNq
    dmRDGMMlDmnVjgMlhBSwCbCgwHbBHhvv
    NwqLgLBLgnwNNBGpgsQsddhhpQQg
    JcztcZnzVtZvnVcJMTvTJtWtppsQHGdQhhHHQsPhWdPS
    fJTJnMmvZvMvRDqFblNBNNjlBf
    drZVzZzzNWWzwwTWTZrjWcLCqnRqNnLNLqCqnsPPRL
    JhlBgvHBBLnwMBqDwC
    GmGFSHmhJSGJwgFJmwJhJhgQVWVdbSWZQzZTrWtZzjzjTz
    wPGRPpnzgwwGgLddFBFrnrnJdc
    jCsVclQWmCTrJJddrdFs
    lWjlCqfmlWccpGPPSgcf
    hCThCzTdPcPhzqTzMfVfHrhMMmhVHgVM
    lJSJNqwJsZBSsSBFsMprDmFmFDfDDHgHDf
    JNGQsSSNGsbZZZSBwZLPtdLjttnqPCbtPbjC
    vnlWNpbrNrpShhQDLRLB
    MzCjPgffVTVgCJSRQhBdRdJS
    VPHcfcBfTzVMTttMzMfgzMfHvrllWvlnvNvlmGwWNwwNmw
    BwwsqPJqwBssLlFqLRCDzWwzDGRGGWfSRG
    vTtmmthvpphpnNgNvvpvRrDCddDQrCQCzCDrCfnf
    pppccNpTVVlqssHHVzBH
    HWHphZWVWvMZNvpMtfJZgssffsjJgBlslJ
    RLmrFFnGFrFFrrFCRwCrLNPwqfjSJjqJSJBbsqjbbsblfq
    LnFLPPGLrGNRQPmndLzPmPmpcDcMHhcMhVHvczcpHHchcV
    zwqqvNDVggwqVfNQRlszFBsCCJFtFlFPsz
    MSrrGTZPGSSMSjPbTmtlHBBFrFHFsHlsJsct
    MnmMPMSZZGSZWmSjnWgfqdgVQDvnqvRDggDV
    SQCSBShsQnSsSJswsNpVppPPMVpGpnDVgg
    WWjHvmtWZrwvtzzjTTRPrRRrMVNVVGgVGpGR
    lTvWjWLfWwbJCQqBSlbB
    cjPChhswrNVtMZJjVM
    pfvTFvTzLBFndGTlJmVJZmNlCMGtCJ
    nfvFTfpbBFdSFpTLswsWDbchwHCWHrbw
    lNdNPLJJLHHHlpPJcvtVcsBBrrBvBqrVrC
    wDTbwTQRZTMWsVWtmWhhTr
    nzRMbSZtMQDnpzzJHLHNflHP
    HrwwmwcRbmwcbrrTbwwcrTJWLlPshllhLccqLhnnlljhZhjZ
    GMFMSNSpCBSFSdGpNFpBznLlzzhzshlGhhqPhGGL
    nFFSCCSSfttBdddDQNDBQpSSrbrmWJwrHfJHWJrbVwWHrgVr
    SdddNNCmpNNDhMswhsmbhvHM
    frtzqqqFjgrWfgfqtthsnvRHZRRvFlhnvRZb
    rtrgqzzrbWtqLGLLtBWzfGcTNCCVGpSNDppTJJVddNpPSG
    WWJvJvBgpHSHScQRQSVQLzqL
    ddZTlZGZVfQhZRLLMqsR
    rPfwrGGrFjjNTGNCCVBggDJHmNDvbmmpmNJJ
    bbGrJPRVPtfsVfFlMjBV
    WQzhQQQNZQCWNnQDhzWdNjFZggmlHjjmMmMFjFHpMs
    CzQCSWDTWhNhzWhTGJwtRRqTblwcclvP
    HLDvZgZldDTnLLsswMpVLn
    FNVQzQSPznCMmpBwCF
    SqfJPfttqffjJPVlhvhZZtvdVDRZ
    jVsLvHvvdrSjpJFsGzmnmltnml
    nTNTRCTBTmmmFPMJ
    CQnCggWQDgBrrSqHjDDfSS
    ZpNlrZNcmctZbcZlmcmZhhpPvPHvwBMHJPMTMHBTFJvJ
    zmdCnGzGRnLDjQnzPvMFVHMVMLTVwMJv
    GGjqssqgzCnCzQsshcffmrbNrrNZtW
    DNpTwhpLlWMDWNMhbJjGttJFHgDcjtjG
    wqQrdCdqbFtCtJtJ
    vffdrwfPrsmqVBBWRVlRRlTSTWSTlR
    ZqTCTQQTFvsDSsBDvWBd
    hfBLzRLtHHLDDWRRWWDNbd
    pHhhnPzLfJcJhzHLzZjcmwCTqTQgwBqqwg
    WJHgqgFqrVrqgqCHwsJHHVFZzppZFGGfTtpcfbdpzzpd
    RvNMQlMBhwMdMfcpbM
    LLRQNBDSSNSwmDDBQRBRBCHsgrgHLVnJVqLsJsnCPJ
    BFhGsDsDsBtsPGtQDrrMdbdrffrffbJbRt
    cVVqScVSWWvVWgVZjnrHJgLfdrLrnrLLLQ
    WmvqNZzzzZSvVzqvcccSzSmqFGCDTGBPQGDhwCDhNDCwPBQp
    RqTlHHTTrQqHlTqsrVDqHbrZFZwhpBhphZBFhZpDpLLLfB
    nSzGCGdvzdGNPBQQBfhLZfFwFN
    WPPPCJMtJSQMJQCCWMJslRrrRgrMRbRqVqqTRR
    BMtfLsLZfTPmCtGWZrZqJNJqvpZdWr
    bRwgHhhRhbbSRbjSglcgwHHJWPcJdPrnNWrnqnWVVqpdnq
    bgjlSgDljHhjgwMPCLPFDMFPGGBC
    zJWjczcWjSWghZgzgSSSZflTqwlfqTTbnQwhdTnMdl
    NrGVCmNpHFPsrJFbFQMJbJdQTn
    JvrtpHHmrCGJCJmNvNpVCsHVgzWgWDDcjjgjDRStWWDLSgzz
    HzdFsBBVsfnTfsPmPtDcZqtMhDDz
    wrjjRQLlwwwrJQLQbCrbwlJDSlcSDtPZmPSDclWDtcDqWh
    RwgprLbNLrLbCCpRCrJLRLFfsGTNNZHBZnnBvvfffnvd
    MlqqlWZclnPtZtDSSvwQQjgQpNQSRM
    rLJTsBrsJBhshTNNwSQBWNvNgNSg
    JbbbChCHsJzHzbWdGHThlFnnPqlPlGPPGncPtFlD
    WcMVvwNNvjRcwTQwVcpNRcspPCFtbPztbCTFmtPtCJtbCzmz
    grrgDhrnDLnLrdfdLZlLZhmCqzlCbtJlSSStFmttqsJJ
    GHHDdgnLDDhrrrgZrZgLNVVVVcRNvcwjWvpWGcRs
    qhGhPSJtGhGtJtvNjnJjnvmNQQmj
    sRBFlbZsrdBRRGbVGBDwDMDQwQwMNDjjjVNV
    CzCflffbBszdBCbdbrtLcfhhgHLGgPccLSPh
    zShhHFzgJWFVFFHFHhRPNjwqPLPtLbtrbwVjjr
    ssnvTmvCDfpCZTnsfCqwNLNPwbJqNJPwrjZw
    vDvpmcnmnBDnsnJTJmQWMHMWzScFggRFRFSW
    nnVHHPLrnpssLnrpLRnHtHrjJcCdzCjcDzMzdqwRdjdDcJ
    WWTGQQzSGWlTmBbDJJjwMJjcvvlDjw
    TGTWBTWmTbgzghZhgzBgpVNrZPPfntfNrVLNNnZP
    TqhQnqqLnnqddttNqQWdtqQmppSSFFClRmzmFZFLSmSlFF
    BcHjGclVPPBrVrcjrGGDrMgcmmRJbRCFzpZmSDRpRZJJmRzz
    ccGjMgvPvsHMgvBHWlhQdqwtllwNdThs
    WjddwRGgHRRdMbrwHRwWjHDtDZplslnJnZrsDvCprJPJ
    QSLLFqQBffCFststlFnn
    CSSmSqzmVVjWMdMjVWgT
    lTfQRhVpRzjThpRQTTTlvHrvBvHnPMHgHqHJvn
    cGDctCwCdDCGSFcJsFJsFBvgnMBrHvvrqngHgmgssg
    SCbSDSFScNpfbRVVJf
    RrwmdwMVjMjMTghDWNTJDpWfWG
    SbPvNbvbSsPbSvZsPJtJWhHpGGGgJWgJ
    lSFvsLNcqzqLrwnFQMnVdmnn
    FgCJFTWntWTFtPLmJmmQJmCMMpljWZBwlGMljjjlwvwBvZ
    SDSbVbdScSDzbLZMBrjlZpVrZp
    ccsSDhSDffzbLNscfcfDcgqhPgTntqmnQmCgtgQCTJ
    VnCnrHnPPrCwHmVWtqfMQQqzCqffCZ
    DDbDcJJJbpJDGppFpqGZRWfGfddzMWtfWM
    tTTglDcgFjwNPHPPwHlH
    bMGbqqgPqqVVMGnbVqSMmRfPcJmCTPDDLJDTCmDm
    FFjjZvFRsFCctmtvtJWD
    wwFhHjQjwQhZrFjQbngglGbRMnSzgbRH
    GPTTJSgTPrPPmcTPpdJsGGGjqbRvqlztqlRqMzGjRv
    LwnfWLNwwHHQwHnjbbMMjWttqtMmMj
    mhwfBDhnQTpJBcBJps
    HQQHwMfwlltzMlVljQhVjjHPPPFGPFcCGprPTPPfDrDcGf
    pRLdvRvJgqLRBSJCcvFnCDTPTcTGnT
    LBLSJbRSLSqbSdBdgJRRqRbwjHHblQttlwtwhzpjMlwhpw
    NWLNSNSDtgSgghgdcwccmwGntwclnT
    FRCQzJRsvfVVjvzFJfQnffwCcmdwmHmmHmTwmCmdGBcq
    sjfJvjfzPRPzvPPVFMssvSLhSSWrMZnSDNrDDhSLZZ
    FvLpSLtCfPCWhRSZZMZJSW
    jbbjwbHjQmHjHsQrQFMnwTnJznwRzhJRnNTM
    gVrjqGqjgrgsFGLDtDBLLfLB
    cgTvRWWLVScRWflNJJDfVJmVlG
    nPPnnmqjmZHCHBHFdfwNsDhzzfJznhsfhw
    bddmQqQjpdFCQWtLQMMSvMMQRS
    wjnmPwCgjPnRlwnmvmvvPnTwbSSLLvsLDWdbbWzvsLzWbzbz
    NqrGqFHqJlfhhJGbszdWQzzLNtQDzz
    FfHFpphrJqJrpGBffcTnBjCnVTMjMRCnVljT
    SrfSJGJpSgMprMHdhBGhsdsshdGsmm
    nRTRPvQllQlblwvCjTwLTnvBqdhmHDPVsmDmdqshDVhNsP
    lbRFHvRwlnlLbnbjLbLjLCzggSpWfMFzSZpzZFJJWpJr
    vNLlFldlvPtHFPHQRt
    jcpRsScDgshzjqzfVStntBTPMTnmWttntMpp
    fSssgVjDsbqSVbCJClLRJLCZRZZb
    wnHmCJccDDcrNnrNMRDtTzpTlMpTzpBp
    PjSPPGjWjLzTjjMtzzMj
    hWvLLFWvHvczVcVn
    jgtngnnhMthcnLjMgCZvChDsmdNCvNNZDN
    bWqFPbFbLzRFfZBNDNNPZsNd
    RbJzGpzVLLLWHHQgTMwcTptQ
    sJBhsMWQnhhrFBsFhlQQMfrDCDpLlVCddjTdDDpqDLTLdj
    tZHHSRmNHcgmNzpDPJtttqjLqdpL
    HbNbZmcHQJbsFWvs
    VgPNWGbgSjGjfhRRFfzThtmtzF
    qLCQJBqqcPPmLHhHFz
    CcJvplQswNgZlNPSbS
""".trimIndent()