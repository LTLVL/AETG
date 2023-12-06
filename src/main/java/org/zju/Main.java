package org.zju;

import org.zju.util.Algorithm;

public class Main {
    public static void main(String[] args) {
        int t = check_t(args);
        if (t != -1) {
            Algorithm.run(t);
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