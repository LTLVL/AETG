package org.zju.util;

import com.alibaba.fastjson2.JSON;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 用于初始化ucps
 * @author Tao
 * @date 2023/12/07
 */
public class Initializer {
    //true表示处理笔记本
    private final boolean flag;

    public Initializer(int totalArgs, boolean flag) {
        this.totalArgs = totalArgs;
        this.flag = flag;
    }

    public int totalArgs;
    public ArrayList<ArrayList<Integer>> tempIntegers = new ArrayList<>() {{
        add(new ArrayList<>());
    }};


    /**
     * M选t的排列组合
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

    /**
     * 根据排列组合初始化ucps
     * @param integers
     * @param arrayLists
     * @param count
     */
    public void initUncoveredPairs(ArrayList<Integer> integers, ArrayList<ArrayList<Integer>> arrayLists, int count) {
        if (count == integers.size()) {
            return;
        }
        if (count == 0) {
            int size = flag ? initLaptopList().get(integers.get(0)).size() : initTripList().get(integers.get(0)).size();
            for (int i = 0; i < size; i++) {
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
                int temp = flag ? initLaptopList().get(integers.get(count)).size() : initTripList().get(integers.get(count)).size();
                for (int j = 0; j < temp; j++) {
                    String jsonString = JSON.toJSONString(remove);
                    ArrayList newList = JSON.parseObject(jsonString, ArrayList.class);
                    newList.set(integers.get(count), j);
                    arrayLists.add(newList);
                }
            }
        }
        arrayLists.removeIf(ArrayList::isEmpty);
        if (!flag) {
            arrayLists.removeIf(integers1 -> integers1.get(0) != -1 && integers1.get(0).equals(integers1.get(1)));
        }
        initUncoveredPairs(integers, arrayLists, count + 1);
    }

    /**
     * 初始化笔记本List
     * @return {@link Map}<{@link String}, {@link List}<{@link String}>>
     */
    public static ArrayList<List<String>> initLaptopList() {
        ArrayList<List<String>> laptopList = new ArrayList<>();
        laptopList.add(getBrandMap());
        laptopList.add(getAspectRatioMap());
        laptopList.add(getEnergyEfficiencyClassMap());
        laptopList.add(getSSDMap());
        laptopList.add(getThicknessesMap());
        laptopList.add(getFuselageMaterialMap());
        laptopList.add(getMechanicalDriveMap());
        laptopList.add(getRAMMap());
        laptopList.add(getScreenSizeMap());
        laptopList.add(getScreenRefreshRateMap());
        return laptopList;
    }

    /**
     * 初始化旅程List
     *
     * @return {@link Map}<{@link String}, {@link List}<{@link String}>>
     */
    public static ArrayList<List> initTripList() {
        ArrayList<List> tripList = new ArrayList<>();
        tripList.add(getSource());
        tripList.add(getDestination());
        tripList.add(getDepartureDate());
        tripList.add(getAdult());
        tripList.add(getChild());
        tripList.add(getBaby());
        tripList.add(getAirClass());
        tripList.add(getDirectFlight());
        return tripList;
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

    private static List<String> getSource() {
        return new ArrayList<>() {{
            add("北京");
            add("上海");
            add("广州");
            add("深圳");
            add("成都");
            add("杭州");
            add("武汉");
            add("西安");
            add("重庆");
            add("长沙");
            add("南京");
            add("厦门");
            add("昆明");
            add("大连");
            add("天津");
            add("郑州");
            add("三亚");
            add("济南");
            add("福州");
        }};
    }

    private static List<String> getDestination() {
        return getSource();
    }

    private static List<LocalDate> getDepartureDate() {
        return new ArrayList<>() {{
            add(LocalDate.now());
            add(LocalDate.now().plusDays(1));
            add(LocalDate.now().plusDays(2));
            add(LocalDate.now().plusDays(3));
            add(LocalDate.now().plusDays(4));
            add(LocalDate.now().plusDays(5));
            add(LocalDate.now().plusDays(6));
            add(LocalDate.now().plusDays(7));
        }};
    }

    private static List<Integer> getAdult() {
        return new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
    }

    private static List<Integer> getChild() {
        return new ArrayList<>() {{
            add(0);
            add(1);
            add(2);
        }};
    }

    private static List<Integer> getBaby() {
        return new ArrayList<>() {{
            add(0);
            add(1);
            add(2);
            add(3);
        }};
    }

    private static List<String> getAirClass() {
        return new ArrayList<>() {{
            add("经济/超经舱");
            add("公务/头等舱");
            add("公务舱");
            add("头等舱");
        }};
    }

    private static List<Boolean> getDirectFlight() {
        return new ArrayList<>() {{
            add(true);
            add(false);
        }};
    }
}
