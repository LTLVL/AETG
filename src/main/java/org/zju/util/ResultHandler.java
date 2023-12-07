package org.zju.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.zju.domain.Laptop;
import org.zju.domain.Trip;

import java.util.ArrayList;

/**
 * 产出xlsx文件
 * @author Tao
 * @date 2023/12/07
 */
public class ResultHandler {
    private final String LaptopFileName;
    private final String tripFileName;

    /**
     * 确定文件保存路径
     * @param t
     */
    public ResultHandler(int t) {
        LaptopFileName = "D:\\laptop_" + t + ".xlsx";
        tripFileName = "D:\\trip_" + t + ".xlsx";
    }

    /**
     * 处理laptop
     * @param laptops
     */
    public void handleLaptop(ArrayList<Laptop> laptops) {
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(LaptopFileName, Laptop.class).sheet("模板").doWrite(laptops);

    }

    /**
     * 处理trip
     * @param trips
     */
    public void handleTrip(ArrayList<Trip> trips) {
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(tripFileName, Trip.class).sheet("模板").doWrite(trips);

    }
}
