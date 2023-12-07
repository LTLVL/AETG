package org.zju.util;

import org.zju.domain.Case;
import org.zju.domain.Laptop;
import org.zju.domain.Trip;

import java.util.*;
import java.util.function.Predicate;

public class Algorithm {
    private final ArrayList<Integer[]> coveredPairs = new ArrayList<>();
    private final ArrayList<Integer> pairings = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> unCoveredPairs = new ArrayList<>();
    private final HashMap<String, Integer> countTable = new HashMap<>();
    private final int M = 50;
    private final int totalArgs;
    private int T = 0;
    private final boolean flag;

    public Algorithm(int t, boolean flag) {
        this.flag = flag;
        this.T = t;
        this.totalArgs = this.flag ? Initializer.initLaptopList().size() : Initializer.initTripList().size();
    }

    /**
     * 算法主流程
     */
    public ArrayList run() {
        Initializer lapTopinitializer = new Initializer(totalArgs, flag);
        lapTopinitializer.initTemp(T, 0);


        //所有 t 个变量的等价类组合的用例集
        for (ArrayList<Integer> tempInteger : lapTopinitializer.tempIntegers) {
            ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
            lapTopinitializer.initUncoveredPairs(tempInteger, arrayLists, 0);
            unCoveredPairs.addAll(arrayLists);
        }
        //第一个测试用例固定选取
        Integer[] integers = new Integer[totalArgs];
        Arrays.fill(integers, 0);


        int pairing = updateUncoveredPairs(integers); //返回该测试用例覆盖的pair
        coveredPairs.add(integers);
        pairings.add(pairing);

        //得到未覆盖集的次数统计
        ArrayList<Case> cases = statistic();
        while (cases.size() != 0) {
            cases = iterate(cases);
        }
        if (flag) {
            ArrayList<Laptop> laptops = new ArrayList<>();
            for (int i = 0; i < coveredPairs.size(); i++) {
                Integer[] pair = coveredPairs.get(i);
                laptops.add(new Laptop(i + 1, pair[0], pair[1], pair[2], pair[3], pair[4]
                        , pair[5], pair[6], pair[7], pair[8], pair[9], pairings.get(i)));
            }
            return laptops;
        } else {
            ArrayList<Trip> trips = new ArrayList<>();
            for (int i = 0; i < coveredPairs.size(); i++) {
                Integer[] pair = coveredPairs.get(i);
                trips.add(new Trip(i + 1, pair[0], pair[1], pair[2], pair[3], pair[4]
                        , pair[5], pair[6], pair[7], pairings.get(i)));
            }
            return trips;
        }
    }

    private ArrayList<Case> statistic() {
        countTable.clear();
        for (ArrayList<Integer> unCoveredPair : unCoveredPairs) {
            for (int j = 0; j < unCoveredPair.size(); j++) {
                if (unCoveredPair.get(j) != -1) {
                    StringBuilder key = new StringBuilder(j + ",");
                    key.append(unCoveredPair.get(j));
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

    private int updateUncoveredPairs(Integer[] integers) {
        int oldSize = unCoveredPairs.size();
        Iterator<ArrayList<Integer>> iterator = unCoveredPairs.iterator();
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
        return oldSize - unCoveredPairs.size();
    }

    private int getCoveredPairs(Integer[] integers) {
        int size = 0;
        Iterator<ArrayList<Integer>> iterator = unCoveredPairs.iterator();
        while (iterator.hasNext()) {
            ArrayList<Integer> next = iterator.next();
            int count = 0;
            for (int i = 0; i < next.size(); i++) {
                if (next.get(i) == -1 || Objects.equals(next.get(i), integers[i])) {
                    count++;
                }
            }
            if (count == next.size()) {
                size++;
            }
        }
        return size;
    }

    private ArrayList<Case> iterate(ArrayList<Case> cases) {
        ArrayList<Integer[]> candidates = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            candidates.add(new Integer[totalArgs]);
        }
        Case aCase = cases.stream().max(Comparator.comparingInt(Case::getCount)).get();
        List<Case> caseList = cases.stream().filter(new Predicate<Case>() {
            @Override
            public boolean test(Case case1) {
                return case1.getCount() == aCase.getCount();
            }
        }).toList();
        for (int i = 0; i < candidates.size(); i++) {
            candidates.get(i)[aCase.getPosition()] = caseList.get(i % caseList.size()).getValue();
        }

        //获取M个候选用例
        for (int i = 0; i < totalArgs; i++) {
            if (i != aCase.getPosition()) {
                int finalI = i;
//                List<Case> list = cases.stream().filter(aCase1 -> aCase1.getPosition() == finalI).toList();
//                List<Case> sortedList = list.stream().sorted((o1, o2) -> o2.getCount() - o1.getCount()).toList();

                for (int j = 0; j < 50; j++) {
                    if (flag) {
                        candidates.get(j)[i] = j % Initializer.initLaptopList().get(i).size();
                    } else {
                        candidates.get(j)[i] = j % Initializer.initTripList().get(i).size();
                    }

                }
            }
        }

        HashMap<Integer[], Integer> map = new HashMap<>();
        for (Integer[] candidate : candidates) {
            map.put(candidate, getCoveredPairs(candidate));
        }
        List<Map.Entry<Integer[], Integer>> list = new ArrayList<>(map.entrySet()); //转换为list
        //选出用例
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Integer[] chosenCase = list.get(0).getKey();
        int pairing = updateUncoveredPairs(chosenCase); //返回该测试用例覆盖的pair
        pairings.add(pairing);
        coveredPairs.add(chosenCase);
        //得到未覆盖集的次数统计
        return statistic();
    }

}
