package org.zju.domain;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

/**
 * 笔记本：[18, 4, 2, 22, 4, 4, 4, 14, 8, 9]
 * 笔记本：[18, 4, 2, 22, 4] 出于性能考虑，参数缩减为5个
 * @author Tao
 * @date 2023/12/05
 */
@Data
public class Laptop {
    private String brand;
    private String aspectRatio;
    private String energyEfficiencyClass;
    private String SSD;
    private String thicknesses;
    private Integer pairings;
//    private String fuselageMaterial;
//    private String mechanicalDrive;
//    private String RAM;
//    private String screenSize;
//    private String screenRefreshRate;
//    private Integer pairings;

    public void setAttribute(String s, int count) {
        switch (count) {
            case 0 -> this.brand = s;
            case 1 -> this.aspectRatio = s;
            case 2 -> this.energyEfficiencyClass = s;
            case 3 -> this.SSD = s;
            case 4 -> this.thicknesses = s;
//            case 5 -> this.fuselageMaterial = s;
//            case 6 -> this.mechanicalDrive = s;
//            case 7 -> this.RAM = s;
//            case 8 -> this.screenSize = s;
//            case 9 -> this.screenRefreshRate = s;
        }
    }

}