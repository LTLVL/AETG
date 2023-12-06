package org.zju;

import org.zju.domain.Laptop;
import org.zju.util.Algorithm;
import org.zju.util.ResultHandler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int t = check_t(args);
        if (t != -1) {
            Algorithm laotop = new Algorithm();
            ArrayList<Laptop> laptops = laotop.run(t, Laptop.class);
            ResultHandler.handleLaptop(laptops);
        }
    }

    /**
     * 校验参数t是否合法，将其约束在2-10
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