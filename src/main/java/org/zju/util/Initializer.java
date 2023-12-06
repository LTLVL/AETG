package org.zju.util;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Initializer {
    public Initializer(int totalArgs) {
        this.totalArgs = totalArgs;
    }

    public int totalArgs;
    public ArrayList<ArrayList<Integer>> tempIntegers = new ArrayList<>() {{
        add(new ArrayList<>());
    }};


    /**
     * 排列组合
     *
     * @param t
     * @param count
     */
    public void initTemp(int t, int count) {
        if (count == t) {
            return;
        }
        int size = tempIntegers.size();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> remove = tempIntegers.remove(0);
            int max = 0;
            if (!remove.isEmpty()) {
                max = remove.stream().max((o1, o2) -> o1 - o2).get();
            }
            for (int j = max; j < totalArgs; j++) {
                if (!remove.contains(j)) {
                    String jsonString = JSON.toJSONString(remove);
                    ArrayList newList = JSON.parseObject(jsonString, ArrayList.class);
                    newList.add(j);
                    tempIntegers.add(newList);
                }
            }
        }
        initTemp(t, count + 1);
    }

    public void initUncoveredPairs(ArrayList<Integer> integers, ArrayList<ArrayList<Integer>> arrayLists, int count) {
        if (count == integers.size()) {
            return;
        }
        if (count == 0) {
            for (int i = 0; i < initLaptopMap().get(0).size(); i++) {
                ArrayList<Integer> list = new ArrayList<>() {{
                    for (int j = 0; j < totalArgs; j++) {
                        add(-1);
                    }
                }};
                list.set(integers.get(0), i);
                arrayLists.add(list);
            }
        } else {
            int size = arrayLists.size();
            for (int i = 0; i < size; i++) {
                ArrayList<Integer> remove = arrayLists.remove(0);
                for (int j = 0; j < initLaptopMap().get(integers.get(count)).size(); j++) {
                    String jsonString = JSON.toJSONString(remove);
                    ArrayList newList = JSON.parseObject(jsonString, ArrayList.class);
                    newList.set(integers.get(count), j);
                    arrayLists.add(newList);
                }
            }
        }
        initUncoveredPairs(integers, arrayLists, count + 1);
    }

    /**
     * 初始化笔记本Map
     *
     * @return {@link Map}<{@link String}, {@link List}<{@link String}>>
     */
    public static ArrayList<List<String>> initLaptopMap() {
        ArrayList<List<String>> laptopList = new ArrayList<>();
        laptopList.add(getBrandMap());
        laptopList.add(getAspectRatioMap());
        laptopList.add(getEnergyEfficiencyClassMap());
        laptopList.add(getSSDMap());
        laptopList.add(getThicknessesMap());
        laptopList.add(getFuselageMaterialMap());
//        laptopList.add(getMechanicalDriveMap());
//        laptopList.add(getRAMMap());
//        laptopList.add(getScreenSizeMap());
//        laptopList.add(getScreenRefreshRateMap());
        return laptopList;
    }

    private static List<String> getBrandMap() {
        return new ArrayList<>() {{
            add("ThinkPad");
            add("lenovo");
            add("HP");
            add("Dell");
            add("HUAWEI");
            add("Apple");
            add("ASUS");
            add("HONOR");
            add("MI");
            add("HUWI");
            add("acer");
            add("Microsoft");
            add("荣耀剑舞");
            add("MECHREVO");
            add("dere");
            add("XINE");
            add("Haier");
            add("IPASON");
        }};
    }

    private static List<String> getAspectRatioMap() {
        return new ArrayList<>() {{
            add("16:10");
            add("16:9");
            add("4:3");
            add("3:2");
        }};
    }

    private static List<String> getEnergyEfficiencyClassMap() {
        return new ArrayList<>() {{
            add("一级能效");
            add("二级能效");
        }};
    }

    private static List<String> getSSDMap() {
        return new ArrayList<>() {{
            add("3TB");
            add("128GB");
            add("256GB+1TB");
            add("512GB+2TB");
            add("3TB*2");
            add("2TB*3");
            add("64GB");
            add("240GB");
            add("4TB*2");
            add("256GB");
            add("4TB*3");
            add("512GB");
            add("512GB*2");
            add("1TB");
            add("2TB");
            add("2TB*2");
            add("4TB*2");
            add("512GB+1TB");
            add("4TB");
            add("8TB");
            add("2TB+4TB");
            add("无固态硬盘");
        }};
    }

    private static List<String> getThicknessesMap() {
        return new ArrayList<>() {{
            add("15.0mm及以下");
            add("15.1-18.0mm");
            add("18.1-20.0mm");
            add("20.0mm以上");
        }};
    }

    private static List<String> getFuselageMaterialMap() {
        return new ArrayList<>() {{
            add("金属");
            add("金属+复合材质");
            add("复合材质");
            add("含碳纤维");
        }};
    }

    private static List<String> getMechanicalDriveMap() {
        return new ArrayList<>() {{
            add("1TB");
            add("2TB*2");
            add("2TB");
            add("无机械硬盘");
        }};
    }

    private static List<String> getRAMMap() {
        return new ArrayList<>() {{
            add("4GB");
            add("6GB");
            add("48GB");
            add("18GB");
            add("192GB");
            add("8GB");
            add("12GB");
            add("16GB");
            add("20GB");
            add("36GB");
            add("40GB");
            add("64GB");
            add("96GB");
            add("128GB");
        }};
    }

    private static List<String> getScreenSizeMap() {
        return new ArrayList<>() {{
            add("13.0英寸及以下");
            add("13.0-13.9英寸");
            add("14.0-14.9英寸");
            add("15.0-15.9英寸");
            add("16.0-16.9英寸");
            add("17英寸");
            add("17.3英寸");
            add("18.4英寸");
        }};
    }

    private static List<String> getScreenRefreshRateMap() {
        return new ArrayList<>() {{
            add("60HZ");
            add("165HZ");
            add("360HZ");
            add("60-120HZ");
            add("60-120HZ");
            add("90HZ");
            add("120HZ");
            add("144HZ");
            add("240HZ");
        }};
    }

}
