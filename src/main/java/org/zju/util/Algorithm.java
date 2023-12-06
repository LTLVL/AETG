package org.zju.util;

import com.alibaba.fastjson2.JSON;
import org.zju.domain.Case;

import java.util.*;
import java.util.function.Predicate;

public class Algorithm {
    private static final ArrayList<Integer[]> laptopCoveredPairs = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> laptopUnCoveredPairs = new ArrayList<>();
    private static final HashMap<String, Integer> countTable = new HashMap<>();
    private static final int M = 50;
    private static final int totalArgs = 6;
    private static int T = 0;

    /**
     * 算法主流程
     *
     * @param t
     */
    public static void run(int t) {
        T = t;
        Initializer lapTopinitializer = new Initializer(totalArgs);
        lapTopinitializer.initTemp(t, 0);
        lapTopinitializer.tempIntegers.removeIf(ArrayList::isEmpty);

        //所有 t 个变量的等价类组合的用例集
        for (ArrayList<Integer> tempInteger : lapTopinitializer.tempIntegers) {
            ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
            lapTopinitializer.initUncoveredPairs(tempInteger, arrayLists, 0);
            laptopUnCoveredPairs.addAll(arrayLists);
        }
        //第一个测试用例固定选取
        Integer[] integers = new Integer[totalArgs];
        for (int i = 0; i < totalArgs; i++) {
            integers[i] = 0;
        }

        laptopCoveredPairs.add(integers);
        int pairings = updateUncoveredPairs(integers); //返回该测试用例覆盖的pair

        //得到未覆盖集的次数统计
        ArrayList<Case> cases = statistic();
        iterate(cases);


        System.out.println("!!");

    }

    private static ArrayList<Case> statistic() {
        for (ArrayList<Integer> laptopUnCoveredPair : laptopUnCoveredPairs) {
            for (int j = 0; j < laptopUnCoveredPair.size(); j++) {
                if (laptopUnCoveredPair.get(j) != -1) {
                    StringBuilder key = new StringBuilder(j + ",");
                    key.append(laptopUnCoveredPair.get(j));
                    if (!countTable.containsKey(key.toString())) {
                        countTable.put(key.toString(), 1);
                    } else {
                        countTable.replace(key.toString(), countTable.get(key.toString()) + 1);
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(countTable.entrySet()); //转换为list
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        ArrayList<Case> cases = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            String[] split = entry.getKey().split(",");
            cases.add(new Case(Integer.parseInt(split[0]), Integer.parseInt(split[1]), entry.getValue()));

        }
        return cases;
    }

    private static int updateUncoveredPairs(Integer[] integers) {
        int oldSize = laptopUnCoveredPairs.size();
        Iterator<ArrayList<Integer>> iterator = laptopUnCoveredPairs.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> next = iterator.next();
            int count = 0;
            for (int i = 0; i < next.size(); i++) {
                if (next.get(i) == -1 || Objects.equals(next.get(i), integers[i])) {
                    count++;
                }
            }
            if (count == next.size()) {
                iterator.remove();
            }
        }
        return oldSize - laptopUnCoveredPairs.size();
    }

    private static int getCoveredPairs(Integer[] integers) {
        int size = 0;
        Iterator<ArrayList<Integer>> iterator = laptopUnCoveredPairs.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> next = iterator.next();
            int count = 0;
            for (int i = 0; i < next.size(); i++) {
                if (next.get(i) == -1 || integers[i] == null || Objects.equals(next.get(i), integers[i])) {
                    count++;
                }
            }
            if (count == next.size()) {
                size++;
            }
        }
        return size;
    }

    private static void iterate(ArrayList<Case> cases) {
        ArrayList<Integer[]> candidates = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            candidates.add(new Integer[T]);
        }
        List<Case> caseList = cases.stream().filter(new Predicate<Case>() {
            @Override
            public boolean test(Case aCase) {
                return aCase.getPosition() == 0;
            }
        }).toList();
        Case aCase = caseList.stream().max(new Comparator<Case>() {
            @Override
            public int compare(Case o1, Case o2) {
                return o2.getValue() - o1.getValue();
            }
        }).get();
        for (Integer[] candidate : candidates) {
            candidate[0] = aCase.getValue();
        }

        for (int i = 1; i < totalArgs; i++) {
            int finalI = i;
            List<Case> list = cases.stream().filter(aCase1 -> aCase1.getPosition() == finalI).toList();
            List<Case> sortedList = list.stream().sorted((o1, o2) -> o2.getCount() - o1.getCount()).toList();
            for (int j = 0; j < 50; j++) {
                    candidates.get(j)[i] = sortedList.get(j % sortedList.size()).getValue();
            }
        }


        System.out.println("!");

    }

}
