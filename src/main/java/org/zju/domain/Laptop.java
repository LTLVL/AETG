package org.zju.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.JSON;
import lombok.Data;
import org.zju.util.Initializer;

/**
 * 笔记本：[18, 4, 2, 22, 4, 4, 4, 14, 8, 9]
 * 笔记本：[18, 4, 2, 22, 4, 4] 出于性能考虑，参数缩减为5个
 *
 * @author Tao
 * @date 2023/12/05
 */
@Data
public class Laptop {
    @ExcelProperty("case")
    private int index;
    @ExcelProperty("品牌")
    private String brand;
    @ExcelProperty("屏幕比例")
    private String aspectRatio;
    @ExcelProperty("能耗等级")
    private String energyEfficiencyClass;
    @ExcelProperty("固态硬盘")
    private String SSD;
    @ExcelProperty("厚度")
    private String thicknesses;
    @ExcelProperty("机身材质")
    private String fuselageMaterial;
    @ExcelProperty("机械硬盘")
    private String mechanicalDrive;
    @ExcelProperty("内存容量")
    private String RAM;
    @ExcelProperty("屏幕尺寸")
    private String screenSize;
    @ExcelProperty("屏幕刷新率")
    private String screenRefreshRate;
    @ExcelProperty("pairings")
    private Integer pairings;

    public Laptop(int index,Integer s0, Integer s1, Integer s2, Integer s3, Integer s4, Integer s5,
                  Integer s6, Integer s7, Integer s8, Integer s9, Integer s10) {
        this.index = index;
        this.brand = Initializer.initLaptopList().get(0).get(s0);
        this.aspectRatio = Initializer.initLaptopList().get(1).get(s1);
        this.energyEfficiencyClass = Initializer.initLaptopList().get(2).get(s2);
        this.SSD = Initializer.initLaptopList().get(3).get(s3);
        this.thicknesses = Initializer.initLaptopList().get(4).get(s4);
        this.fuselageMaterial = Initializer.initLaptopList().get(5).get(s5);
        this.mechanicalDrive = Initializer.initLaptopList().get(6).get(s6);
        this.RAM = Initializer.initLaptopList().get(7).get(s7);
        this.screenSize = Initializer.initLaptopList().get(8).get(s8);
        this.screenRefreshRate = Initializer.initLaptopList().get(9).get(s9);
        this.pairings = s10;
    }

}