package advent_2024.puzzles

import kotlin.math.absoluteValue

object Day1Part1 {

    operator fun invoke() {
        val lists = parseInput(input)

        val result = lists.fold(0) { acc, next ->
            acc + (next.first - next.second).absoluteValue
        }

        print(result)
    }

    private fun parseInput(input: String) : List<Pair<Int, Int>> {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.lines().forEach { line ->
            val values = line.split("\\s{3}".toRegex())

            firstList.add(values[0].toInt())
            secondList.add(values[1].toInt())
        }

        firstList.sort()
        secondList.sort()

        return firstList.zip(secondList)
    }

    private fun print(result: Int) {
        println("    Day 1 part 1")
        println("-----------------------")
        println("")
        println("Result is : $result")
        println("")
        println("-----------------------")
        println("")
    }
}

private val testInput = """
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
""".trimIndent()

private val input = """
    46669   36559
    54117   62675
    25659   15179
    18867   82784
    94354   78485
    28985   76228
    42358   34206
    59950   58682
    97799   24655
    52935   64167
    59303   53728
    85106   71005
    50792   91433
    13407   14222
    67886   61779
    16895   63137
    30061   23324
    52760   28081
    58542   49063
    90229   61487
    13000   11872
    75051   16072
    95703   59610
    71514   95236
    38533   15786
    96889   17973
    50570   62950
    22045   76693
    57193   91433
    14748   85022
    10514   38767
    29334   47840
    88964   45642
    48941   39483
    70592   24655
    93664   22449
    96355   69515
    81294   74800
    48811   85577
    45071   85562
    92092   82630
    84197   76933
    97582   13139
    87792   86408
    54977   27338
    99783   29891
    69620   61216
    13987   77826
    99852   96463
    91433   60831
    78088   46665
    72987   42777
    42868   69997
    68892   46927
    34108   66123
    43478   18458
    19787   42868
    94776   47041
    79484   42868
    51651   87993
    24205   29529
    44859   90283
    20200   73827
    87459   73406
    56046   83755
    91769   22449
    67206   27695
    12181   17936
    60868   83249
    38752   22334
    99845   94472
    41505   61216
    60128   24655
    29568   78485
    22811   63720
    58897   73583
    72379   79339
    37663   41578
    79732   61216
    73155   46563
    81753   43279
    51621   51888
    93416   86408
    47921   59610
    80800   23324
    96164   73406
    11851   98656
    23151   90283
    38856   89669
    68276   82552
    98955   32695
    54728   30695
    29338   75446
    62950   59047
    65523   19589
    37775   59752
    44035   55601
    88679   53778
    51799   86633
    88184   62950
    91162   78485
    93338   54646
    88930   17554
    86160   77826
    11460   56897
    25918   45594
    72148   91433
    92158   37114
    93731   49360
    87043   44224
    33307   76228
    28611   70786
    13314   61216
    65566   40058
    20245   63137
    37773   33408
    78163   30754
    63537   76662
    64165   11339
    80838   23324
    51282   29108
    25277   59610
    59407   75468
    67162   32283
    29618   22449
    38169   77435
    74644   65347
    15925   76228
    64235   87695
    14313   31523
    65574   70278
    94576   46927
    28941   76693
    56933   75648
    90283   20996
    15912   76693
    37371   11483
    42928   61216
    20043   15456
    79060   50351
    95841   86342
    90503   22449
    34098   55194
    59349   27339
    52463   49245
    44382   59610
    86377   39994
    77346   51349
    32396   82630
    89974   40626
    56849   67629
    62875   86860
    56067   34945
    92962   49059
    62901   46892
    25886   77783
    21697   96304
    38147   17270
    14550   90283
    13342   70663
    70611   68152
    20481   21488
    28904   58215
    26634   39518
    52203   30752
    66212   78485
    90182   98936
    20740   15786
    66065   65617
    61202   45164
    26862   99261
    58796   40132
    78079   70988
    81851   93597
    71297   91433
    92854   65509
    80295   27424
    63299   44867
    15813   58215
    16650   76933
    53617   23324
    26984   16067
    18903   22496
    45338   10448
    78888   63137
    45642   41937
    33541   24655
    48364   44977
    45753   86408
    38974   87966
    74367   95952
    30987   24768
    69198   61027
    79165   23324
    36565   49245
    93880   23324
    58577   22449
    88572   11153
    23891   35210
    57919   56916
    68618   29125
    69689   24742
    63792   67434
    42378   91433
    30886   85562
    68874   73316
    47080   76228
    17364   50012
    26523   95236
    91160   12505
    82072   63137
    85218   88792
    21179   45280
    83540   22449
    62790   75446
    99201   76966
    24655   91601
    15468   59236
    69939   40216
    26652   51063
    72139   22449
    38765   15786
    37220   73406
    46720   15786
    23324   12724
    28463   22865
    74495   12866
    81338   47719
    48576   67579
    57908   85562
    96483   75446
    12243   46927
    18922   30274
    96066   50932
    32506   57089
    63137   18017
    80079   34565
    78238   57507
    29990   85562
    63341   77826
    53097   89535
    77250   67346
    18752   22230
    84225   91433
    23056   22728
    72203   44957
    12182   95236
    54287   57853
    78417   76693
    79546   61111
    97938   90283
    28464   91433
    43864   61216
    25327   45949
    76917   18588
    88304   61216
    16599   91970
    90372   38723
    78721   47963
    61712   73406
    40506   20355
    80458   13289
    50334   49245
    10066   99664
    33077   90921
    27603   87695
    51307   62936
    18791   49524
    90086   98799
    61487   76228
    80381   77826
    30353   75446
    46686   63367
    67471   50064
    22404   67635
    73240   47665
    38824   85562
    52873   78957
    89294   79544
    55505   36605
    21363   73406
    54456   90283
    26147   51518
    51044   98236
    27948   22449
    28048   76228
    52852   87695
    33020   76933
    66414   51084
    29109   75446
    10430   72012
    53057   63137
    99531   27252
    77723   76933
    87307   24655
    81886   50012
    97967   90283
    24057   64661
    23205   63137
    18662   76693
    89627   25871
    70743   50012
    27182   88872
    75446   50012
    88535   38500
    83093   69995
    67214   88991
    79553   33819
    61690   50012
    76228   98865
    50955   95236
    93037   19499
    24196   49526
    59691   67094
    35278   90283
    61597   23324
    56941   37523
    20120   97355
    87535   87032
    41203   63891
    16392   87787
    72594   75446
    95236   76693
    59675   65584
    68845   90283
    78485   33285
    44280   62583
    98434   19636
    86408   22449
    49172   15344
    33704   41932
    57434   72228
    72242   16591
    55017   75193
    57538   97837
    66108   35681
    22194   32467
    74204   41085
    75528   56270
    55070   13932
    12362   69548
    80880   46460
    92437   86408
    62756   37768
    61713   33991
    65166   15786
    66265   57948
    59605   81427
    95292   61216
    53322   61216
    92343   77826
    57881   43673
    25694   79166
    50803   62933
    12557   76228
    30235   63137
    60145   59610
    69385   54371
    67591   95236
    75778   57891
    32402   72023
    85763   79371
    77826   58215
    54653   52717
    43427   73406
    87693   78923
    42560   99999
    56662   98907
    34358   15149
    72062   87084
    87457   25270
    79098   46927
    96786   63137
    12893   90634
    72153   22959
    79455   13986
    94850   28046
    73433   11290
    18596   58215
    82630   78485
    33604   14849
    15222   74035
    61085   23906
    36243   10054
    61417   39396
    97586   61216
    38303   13532
    46927   78485
    97158   28915
    47223   24655
    71928   16050
    48850   10274
    63968   59834
    74998   51096
    12237   16089
    15340   68204
    28588   86420
    27774   27427
    87417   76693
    68167   20436
    82772   87695
    16746   41792
    49969   76228
    68165   28891
    87687   73914
    31995   73403
    51948   57812
    92704   73961
    44851   23324
    81688   48766
    92752   24655
    87448   42868
    14258   13555
    97152   95236
    16893   58215
    49941   72107
    92908   95236
    30101   75629
    83485   19453
    70442   95236
    90285   24655
    55498   23539
    38791   77826
    51701   84708
    30330   59334
    59586   61909
    69360   16800
    96194   66338
    26813   94571
    16730   60469
    20452   61216
    88869   62950
    36471   85562
    48094   11994
    53210   58215
    25883   55787
    67454   16997
    28364   91433
    29596   18200
    39653   42554
    78247   98641
    68869   10213
    37243   61575
    78248   91234
    86264   24655
    42101   22449
    81992   77493
    65269   73406
    79512   88490
    23050   68216
    71745   32372
    12275   22311
    72704   57374
    40391   37266
    93996   81448
    60235   71232
    23986   45642
    31562   61893
    76700   68049
    95646   70208
    19567   23324
    91012   93539
    29318   15559
    25010   11836
    35563   51368
    54704   76228
    49819   40935
    33230   61216
    79086   14268
    51170   18475
    66485   81497
    18849   62950
    64392   24655
    95125   54268
    57789   27894
    59448   86408
    47257   86384
    15786   91433
    61948   42868
    86728   58933
    99498   59808
    52830   35210
    70990   70735
    67116   15019
    63821   73107
    27729   77826
    61964   32077
    36375   88334
    25299   59610
    86368   55921
    86423   71213
    73061   88241
    98588   25835
    13938   21656
    99826   95236
    37553   68730
    64667   62007
    93565   82405
    42768   29866
    42731   76693
    20204   30794
    71882   28231
    93933   73406
    32525   84373
    89842   64081
    31297   45009
    27264   75446
    93838   64694
    60747   78485
    13328   22449
    40592   95236
    91398   20009
    11574   90283
    96548   35894
    34268   49890
    86325   39672
    63884   15842
    30812   23567
    69993   24655
    22994   85977
    36141   24655
    17881   23324
    87053   12341
    85897   11252
    64282   31676
    83859   82810
    37811   70856
    30617   65639
    12124   82075
    25279   61216
    50876   76933
    93773   75446
    11066   73504
    75494   22449
    10852   22449
    75214   75446
    17183   55952
    40332   91433
    29117   20899
    30888   45919
    73512   32221
    78708   26250
    13367   76228
    92813   97383
    51110   47725
    25552   95236
    31096   38747
    83451   76754
    22497   49245
    42453   76693
    91372   53487
    91733   61487
    71459   79153
    45515   95236
    25529   81102
    21597   73394
    43122   82630
    63641   64171
    77303   61216
    37944   58215
    11386   77826
    77018   79070
    82992   96114
    51119   77798
    73221   23324
    18732   34798
    42279   46927
    89508   73730
    27791   80194
    28523   95308
    27555   12881
    29648   54618
    13577   98443
    56947   76693
    36475   87505
    38270   71142
    84688   28274
    19271   76693
    23825   22945
    95718   51459
    91764   51788
    27386   42182
    18677   55015
    67612   76693
    11810   44178
    87498   75376
    74359   45642
    35714   56252
    86955   70950
    28543   86408
    21253   57179
    81283   73406
    54208   83349
    87550   37110
    88194   76693
    68969   49473
    94869   80272
    18066   64411
    31310   15786
    57043   78434
    55440   89060
    88510   61216
    69221   64444
    45336   23324
    39240   51320
    86973   61853
    78101   71772
    24917   38491
    30454   84950
    95917   89580
    96914   78485
    77197   50552
    67382   31174
    73864   88832
    68112   77826
    34762   15786
    53167   36945
    58539   32505
    67660   41818
    89709   35248
    87156   49245
    98670   84396
    12340   44671
    51671   87514
    47948   18060
    69823   76228
    35233   73406
    52807   24655
    94057   26388
    63741   59610
    82004   66606
    93513   60231
    75084   35371
    10372   88851
    89866   46927
    24553   79589
    20556   15786
    46412   22449
    78846   24491
    98088   76693
    31170   92173
    94801   52961
    51058   13445
    70594   50037
    35346   50338
    78257   23324
    73406   21744
    68359   46927
    41213   55805
    45250   42131
    43279   18812
    69199   15786
    53725   76497
    69398   37768
    11742   73406
    65219   58877
    31780   38597
    46113   90283
    30112   76693
    37748   60472
    12596   58428
    22346   98357
    43346   53539
    89142   78485
    14544   38869
    31577   55185
    55937   87695
    41189   35667
    25078   73406
    59330   94542
    84733   16627
    82488   73406
    82948   58215
    16789   91433
    53671   27376
    70549   50516
    97581   54013
    91708   61216
    64783   37768
    89911   46097
    74663   76228
    70646   60061
    23944   31181
    30697   93934
    27561   95236
    52655   68084
    90627   38964
    27641   35708
    14184   91433
    42968   17857
    72204   30261
    77368   35970
    17048   89985
    20782   18407
    64749   78485
    41007   26752
    11880   97776
    44202   24368
    97301   23407
    61077   74465
    38804   77826
    13897   48877
    14508   37578
    61383   53091
    16885   29289
    37225   52081
    64685   35992
    31682   95236
    63631   10368
    45781   24466
    24092   65388
    31728   76228
    45777   22113
    51779   60657
    33859   29358
    53484   78827
    61319   49899
    36093   48789
    40584   77905
    56381   54295
    22662   38614
    24641   22449
    88324   59610
    78924   95236
    74820   61487
    77398   60846
    99595   63137
    65832   49245
    89471   11285
    90188   76593
    97763   71720
    98824   77826
    14418   20513
    39862   73406
    55906   78485
    86059   29127
    20742   97241
    88269   91433
    17962   86920
    32602   49245
    95412   59994
    83267   70431
    36825   24655
    89562   43279
    16860   72596
    53577   96790
    46665   76574
    71813   67270
    35210   15445
    33400   76693
    96069   65213
    46322   39179
    95285   91433
    95767   86269
    15236   76933
    27778   49357
    16170   30140
    57870   63137
    80794   63137
    84281   57089
    91022   26553
    55848   49245
    13825   49245
    91230   87028
    54860   73406
    66596   53306
    61216   64519
    50012   76693
    36465   83257
    87188   75446
    73588   21801
    94757   20402
    28170   60768
    24891   38174
    91787   76693
    63785   43279
    14566   90752
    55392   90283
    39790   67787
    40079   80845
    33827   34045
    79864   78485
    82775   43279
    67159   10612
    53001   37768
    52173   58727
    28322   76933
    84518   33044
    76693   27243
    49225   44682
    22449   75446
    80658   49373
    43255   22449
    59058   73490
    44951   49402
    71201   28356
    55944   42868
    33202   83266
    86699   66983
    88453   85814
    77034   58838
    85850   76228
    41409   88724
    36649   11112
    88428   93089
    59610   66639
    48137   90283
    86400   90487
    28104   46927
    43317   59610
    85510   67445
    99532   78485
    70359   15786
    42034   88131
    80957   68660
    43032   95236
    33582   40382
    58215   94486
    19221   36076
    91307   77826
    64821   76693
    89517   63137
    53579   18009
    82987   91433
    94727   51659
    19906   91433
    80106   43532
    28240   71843
    29670   23324
    39700   87695
    47283   78485
    39801   76933
    25980   96028
    21945   71497
    78407   78485
    85640   45318
    51978   50012
    11601   76575
    83651   31125
    95958   95956
    77749   23336
    13537   84975
    88065   52971
    38210   98657
    40101   36035
    32397   75288
    96618   16256
    42989   19324
    72932   43609
    18771   34539
    58557   86408
    92319   79573
    38295   94964
    10485   63913
    31358   76228
    38846   75446
    72221   22449
    66323   76933
    57089   60456
    88674   50047
    75813   15508
    22055   60704
    69047   96120
    73862   15909
    83222   67849
    76393   80920
    76522   69689
    71666   76228
    63583   12283
    19328   28164
    39355   77826
    85096   76933
    42056   31488
    92747   13579
    84937   47182
    17024   82132
    56871   90283
    97790   46927
    22993   68190
    68482   55096
    79976   85107
    90940   52882
    65919   63137
    62007   49245
    13415   59610
    26893   24655
    44653   95236
    33612   99036
    46478   42868
    70970   80135
    60481   95236
    15301   59360
    17212   37414
    80899   75446
    67495   21742
    18319   14479
    34445   58215
    39486   42282
    36028   44519
    92208   46483
    88362   90245
    53764   48959
    85604   74027
    96035   95893
    29561   30742
    74152   61216
    49507   46274
    87695   76693
    18100   15786
    21313   73406
    62234   12938
    25025   49466
    60890   74332
    11565   33629
    84299   76228
    34861   90283
    21353   73528
    99978   66897
    46529   22449
    13104   25219
    68752   88910
    26478   76933
    87668   43279
    49777   25060
    86763   75446
    73023   45052
    72004   51554
    88141   23926
    33130   82630
    82472   22449
    64754   80502
    57842   62007
    93088   72374
    45727   93735
    53996   19768
    49245   46046
    85299   19540
    56446   63137
    92445   82605
    67790   75139
    16758   26579
    66664   89655
    32710   80610
    75707   49245
    65814   95623
    58220   87669
    16612   76228
    24698   16590
    23184   73406
    91238   15786
    11843   91433
    86625   85562
    41307   77826
    26670   69594
    64855   63137
    63544   83733
    56574   24108
    79163   67158
    99311   95420
    17640   85562
    66865   91397
    31429   37768
    85562   49245
    93263   76716
    81248   45189
    91852   47062
    58324   52943
    63446   36264
    96407   87488
    46436   57089
    12385   26581
    91195   25257
    88835   32326
    21315   77826
    24216   97211
    37075   50012
    46375   49245
    68824   88237
    24882   44857
    60941   75157
    61864   64400
    41858   97465
    83877   61487
    26722   85343
    52455   55804
    22568   74046
    93290   83623
    76933   73008
    61124   76228
    32025   67436
    26293   77826
    46658   78485
    12043   52527
    47464   24655
    56275   41945
    57840   33284
    60040   82705
    10256   95591
    49049   90283
    37768   43279
""".trimIndent()