package org.zju;

import org.zju.domain.Laptop;
import org.zju.domain.Trip;
import org.zju.util.Algorithm;
import org.zju.util.ResultHandler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int t = check_t(args);
        if (t != -1) {
            Algorithm laotop = new Algorithm(t, true);
            ArrayList<Laptop> laptops = laotop.run();
            ResultHandler resultHandler = new ResultHandler(t);
            resultHandler.handleLaptop(laptops);
            long end = System.currentTimeMillis();
            System.out.println("生成laptop测试用例共耗时：" + (end - start) + "ms");
            Algorithm trip = new Algorithm(t, false);
            ArrayList<Trip> trips = trip.run();
            resultHandler.handleTrip(trips);
            System.out.println("生成trip测试用例共耗时：" + (System.currentTimeMillis() - end) + "ms");
        }

    }

    /**
     * 校验参数t是否合法，将其约束在2-10
     *
     * @param strings args
     * @return int 不合法则返回-1
     */
    private static int check_t(String[] strings) {
        if (strings.length != 1) {
            System.out.println("请输入正确的t参数！");
            return -1;
        }
        int t = 0;
        try {
            t = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            System.out.println("请输入正确的t参数！");
        }
        return t > 1 && t < 10 ? t : -1;
    }
}