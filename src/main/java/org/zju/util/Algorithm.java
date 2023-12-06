package org.zju.util;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private static final ArrayList<Integer[]> laptopCoveredPairs = new ArrayList<>();
    private static final int M = 50;

    /**
     * 算法主流程
     *
     * @param t
     */
    public static void run(int t) {
        Initializer.initTemp(t, 0);
        Initializer.tempIntegers.removeIf(ArrayList::isEmpty);
        for (ArrayList<Integer> tempInteger : Initializer.tempIntegers) {
            Initializer.initLaptopUncoveredPairs(tempInteger, new ArrayList<>(), 0);
        }
        ArrayList<ArrayList<Integer>> laptopUncoveredPairs = Initializer.laptopUncoveredPairs;
        ArrayList<List<String>> lists = Initializer.initLaptopMap();
    }
}
