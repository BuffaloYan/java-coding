package com.company.dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LongestIncSeq {
    public static class NumTrack {
        int num;
        int seqLen; // increasing sequencing length ends with the number
        public NumTrack(int n, int l) {
            num = n;
            seqLen = l;
        }
    }

    public int lengthOfLIS(int[] nums) {
        Map<Integer, NumTrack> map = new TreeMap<>();

        int globalMaxLen = 0;
        List<Integer> toRemove = new ArrayList();
        for (int i=0; i<nums.length; i++) {
            // insert in ascending order
            Iterator<NumTrack> iterator = map.values().iterator();
            int maxLen = 1; // minimum is 1
            NumTrack track;

            toRemove.clear();
            while (iterator.hasNext()) {
                track = iterator.next();
                if (track.num < nums[i]) {
                    maxLen = Math.max(maxLen, track.seqLen+1);
                } else if (track.seqLen <= maxLen) {
                    toRemove.add(track.num);
                    break;
                }

            }

            // remove first, otherwise will remove newly inserted for duplicates
            for(Integer n: toRemove) {
                map.remove(n);
            }

            track = new NumTrack(nums[i], maxLen);
            map.put(nums[i], track);



            globalMaxLen = Math.max(globalMaxLen, maxLen);

        }

        String str = String.join(", ", map.values().stream().map(n -> n.num+"").toList());
        System.out.printf("%d = %s\n", globalMaxLen, str);

        return globalMaxLen;
    }

    public int lengthOfLIS2(int[] nums) {
        List<NumTrack> list = new ArrayList<>();
        int globalMaxLen = 0;
        List<Integer> toRemove = new ArrayList();
        int maxLenEndNum = 0;
        for (int i=0; i<nums.length; i++) {
            // insert in ascending order
            Iterator<NumTrack> iterator = list.iterator();
            int maxLen = 1; // minimum is 1
            NumTrack track = null;
            toRemove.clear();
            while (iterator.hasNext()) {
                track = iterator.next();
                if (track.num < nums[i]) {
                    maxLen = Math.max(maxLen, track.seqLen+1);
                }
            }

            track = new NumTrack(nums[i], maxLen);
            list.add(track);

            //globalMaxLen = Math.max(globalMaxLen, maxLen);

            if (maxLen > globalMaxLen) {
                globalMaxLen = maxLen;
                maxLenEndNum = nums[i];
            }

        }

        System.out.println("maxLenEndNum = " + maxLenEndNum);
        return globalMaxLen;
    }

    @Test
    public void testRun() {
        int[] data = new int[] {561,-434,785,779,310,-528,-813,-994,-941,-286,-909,-22,-201,-65,196,188,389,461,755,839,-780,-891,274,-491,179,-134,198,508,405,-551,403,695,567,214,111,-335,827,600,-389,-857,958,320,-221,415,45,213,172,-363,-979,-534,-214,-294,556,-918,-135,722,-780,123,-164,986,741,-845,939,817,908,-110,753,-694,308,469,-991,-59,893,-326,-911,-495,891,487,178,797,551,48,-218,703,-152,191,-831,-4,754,572,680,-620,-796,-949,696,299,-176,360,427,828,253,-448,702,835,360,819,-361,638,-735,-402,815,-68,517,-462,864,-782,-495,846,636,-465,101,-49,-69,-755,550,-379,272,245,734,-694,-227,24,381,697,-736,-854,-974,-137,908,-511,-929,581,523,-253,-769,152,-25,-284,466,-812,552,-149,-633,-865,-293,-342,624,-135,554,-368,-786,-837,-348,738,37,736,706,-397,426,-942,-896,679,103,438,147,-792,103,837,-371,-319,652,102,247,767,326,315,400,-539,999,-458,-208,534,-537,-947,349,836,-705,549,-943,-846,262,437,133,-31,866,75,-623,-22,212,-193,-529,258,-271,-383,531,130,-909,-671,112,102,-782,-764,-562,-403,-1,-751,-438,438,-472,36,-533,-151,865,-322,-425,-352,-50,460,-194,-249,172,962,589,145,369,792,1000,980,709,-775,750,-664,390,-419,854,-176,-885,168,807,-69,-189,-44,881,193,-600,410,249,442,-653,62,36,-249,-560,-466,485,365,-494,408,-41,-416,-573,804,-21,838,-989,-348,-976,-576,-341,-833,600,372,-642,149,800,146,-785,979,-674,963,500,-778,429,-775,917,845,-147,261,989,-510,351,36,372,-859,785,-476,97,-809,-910,-511,-358,-547,-418,765,227,-676,491,-842,412,825,-757,118,637,-812,70,309,-777,502,-850,95,-480,841,838,-551,216,141,-22,475,-901,221,973,-769,893,984,-57,98,902,83,-225,-104,72,-649,-439,-174,135,-576,-821,-136,557,-165,-399,-631,-72,-945,-191,-538,889,-491,-225,-715,-857,-366,585,-723,440,-996,510,527,278,-953,-829,-649,-554,660,-981,841,-874,-923,281,213,841,561,339,-643,344,-58,-644,-768,38,121,624,-37,529,-237,-820,-73,-286,-29,-192,904,972,-213,777,-745,-614,-440,96,-78,850,-560,-295,342,591,-823,336,-431,-845,373,548,-162,818,-152,-533,298,-152,56,-578,589,572,-482,550,-900,145,-681,-746,-518,-663,-819,591,-810,198,-844,489,-991,757,185,-560,-757,-525,-295,347,276,639,-753,-944,661,863,505,-510,761,-296,539,-483,669,-107,-88,-810,447,-742,-342,-84,-937,-620,-242,803,-482,684,-765,-593,-776,571,286,-220,-395,157,692,-254,-226,679,148,-223,648,-530,-829,164,-144,887,-464,396,-596,16,845,560,-560,-965,-349,365,58,-970,-469,-42,-918,691,135,-757,229,603,246,-18,-844,-190,103,899,507,-953,-153,-528,-820,932,-195,1000,728,-267,551,-312,320,161,-65,728,237,-391,-275,299,805,-464,-195,274,622,-200,855,-436,927,775,743,-617,208,999,-780,-3,729,169,-296,-54,-248,-839,-837,-346,835,-515,829,21,-827,949,871,-685,526,-584,809,-841,838,-522,-447,952,-26,-593,-446,869,-298,-745,434,645,593,-692,323,897,-985,171,-311,451,926,-75,883,4,771,-231,-736,30,-691,603,-407,-252,386,-664,-530,324,-924,616,-230,812,151,20,-618,682,-603,-456,-49,-475,128,729,-486,-276,639,991,282,474,480,-885,73,421,865,16,-406,669,-410,-924,-780,-265,-648,-900,410,910,-71,501,191,-145,-217,538,-838,648,-802,-823,-837,944,658,-310,458,445,220,-860,-854,356,-848,-332,-718,812,-96,595,175,-832,-107,-891,-274,963,159,407,-233,779,133,132,-883,-476,84,770,409,331,892,-807,630,229,-893,815,766,170,-363,375,-867,943,-697,253,599,992,455,-669,-379,-969,-433,557,-542,669,220,-602,669,43,722,706,-106,-834,156,-871,-451,976,-353,-791,-230,349,-529,-953,-934,541,-599,909,-829,-361,-972,-754,699,71,-663,513,-597,-790,-427,315,-719,-375,-919,104,492,20,896,-477,-301,-678,955,-237,123,-254,-478,997,-547,-10,306,841,551,815,752,935,643,-459,627,297,-578,-38,224,816,460,209,557,-171,-320,-866};
/*
52 = -996, -989, -985, -972, -969, -953, -934, -919, -883, -871, -866, -837, -834, -829, -791, -790, -736, -719, -697, -678, -663, -602, -599, -597, -578, -459, -451, -433, -427, -379, -375, -320, -254, -171, 20, 71, 104, 123, 209, 297, 455, 460, 551, 557, 752, 816, 892, 896, 935, 976, 997

 */
        LongestIncSeq longestIncSeq = new LongestIncSeq();
        int result = longestIncSeq.lengthOfLIS(data);

        Assert.assertEquals(53, result);
    }

    @Test
    public void testRun2() {
        int[] data = new int[] {561,-434,785,779,310,-528,-813,-994,-941,-286,-909,-22,-201,-65,196,188,389,461,755,839,-780,-891,274,-491,179,-134,198,508,405,-551,403,695,567,214,111,-335,827,600,-389,-857,958,320,-221,415,45,213,172,-363,-979,-534,-214,-294,556,-918,-135,722,-780,123,-164,986,741,-845,939,817,908,-110,753,-694,308,469,-991,-59,893,-326,-911,-495,891,487,178,797,551,48,-218,703,-152,191,-831,-4,754,572,680,-620,-796,-949,696,299,-176,360,427,828,253,-448,702,835,360,819,-361,638,-735,-402,815,-68,517,-462,864,-782,-495,846,636,-465,101,-49,-69,-755,550,-379,272,245,734,-694,-227,24,381,697,-736,-854,-974,-137,908,-511,-929,581,523,-253,-769,152,-25,-284,466,-812,552,-149,-633,-865,-293,-342,624,-135,554,-368,-786,-837,-348,738,37,736,706,-397,426,-942,-896,679,103,438,147,-792,103,837,-371,-319,652,102,247,767,326,315,400,-539,999,-458,-208,534,-537,-947,349,836,-705,549,-943,-846,262,437,133,-31,866,75,-623,-22,212,-193,-529,258,-271,-383,531,130,-909,-671,112,102,-782,-764,-562,-403,-1,-751,-438,438,-472,36,-533,-151,865,-322,-425,-352,-50,460,-194,-249,172,962,589,145,369,792,1000,980,709,-775,750,-664,390,-419,854,-176,-885,168,807,-69,-189,-44,881,193,-600,410,249,442,-653,62,36,-249,-560,-466,485,365,-494,408,-41,-416,-573,804,-21,838,-989,-348,-976,-576,-341,-833,600,372,-642,149,800,146,-785,979,-674,963,500,-778,429,-775,917,845,-147,261,989,-510,351,36,372,-859,785,-476,97,-809,-910,-511,-358,-547,-418,765,227,-676,491,-842,412,825,-757,118,637,-812,70,309,-777,502,-850,95,-480,841,838,-551,216,141,-22,475,-901,221,973,-769,893,984,-57,98,902,83,-225,-104,72,-649,-439,-174,135,-576,-821,-136,557,-165,-399,-631,-72,-945,-191,-538,889,-491,-225,-715,-857,-366,585,-723,440,-996,510,527,278,-953,-829,-649,-554,660,-981,841,-874,-923,281,213,841,561,339,-643,344,-58,-644,-768,38,121,624,-37,529,-237,-820,-73,-286,-29,-192,904,972,-213,777,-745,-614,-440,96,-78,850,-560,-295,342,591,-823,336,-431,-845,373,548,-162,818,-152,-533,298,-152,56,-578,589,572,-482,550,-900,145,-681,-746,-518,-663,-819,591,-810,198,-844,489,-991,757,185,-560,-757,-525,-295,347,276,639,-753,-944,661,863,505,-510,761,-296,539,-483,669,-107,-88,-810,447,-742,-342,-84,-937,-620,-242,803,-482,684,-765,-593,-776,571,286,-220,-395,157,692,-254,-226,679,148,-223,648,-530,-829,164,-144,887,-464,396,-596,16,845,560,-560,-965,-349,365,58,-970,-469,-42,-918,691,135,-757,229,603,246,-18,-844,-190,103,899,507,-953,-153,-528,-820,932,-195,1000,728,-267,551,-312,320,161,-65,728,237,-391,-275,299,805,-464,-195,274,622,-200,855,-436,927,775,743,-617,208,999,-780,-3,729,169,-296,-54,-248,-839,-837,-346,835,-515,829,21,-827,949,871,-685,526,-584,809,-841,838,-522,-447,952,-26,-593,-446,869,-298,-745,434,645,593,-692,323,897,-985,171,-311,451,926,-75,883,4,771,-231,-736,30,-691,603,-407,-252,386,-664,-530,324,-924,616,-230,812,151,20,-618,682,-603,-456,-49,-475,128,729,-486,-276,639,991,282,474,480,-885,73,421,865,16,-406,669,-410,-924,-780,-265,-648,-900,410,910,-71,501,191,-145,-217,538,-838,648,-802,-823,-837,944,658,-310,458,445,220,-860,-854,356,-848,-332,-718,812,-96,595,175,-832,-107,-891,-274,963,159,407,-233,779,133,132,-883,-476,84,770,409,331,892,-807,630,229,-893,815,766,170,-363,375,-867,943,-697,253,599,992,455,-669,-379,-969,-433,557,-542,669,220,-602,669,43,722,706,-106,-834,156,-871,-451,976,-353,-791,-230,349,-529,-953,-934,541,-599,909,-829,-361,-972,-754,699,71,-663,513,-597,-790,-427,315,-719,-375,-919,104,492,20,896,-477,-301,-678,955,-237,123,-254,-478,997,-547,-10,306,841,551,815,752,935,643,-459,627,297,-578,-38,224,816,460,209,557,-171,-320,-866};

        LongestIncSeq longestIncSeq = new LongestIncSeq();
        int result = longestIncSeq.lengthOfLIS2(data);

        Assert.assertEquals(53, result);
    }
}
