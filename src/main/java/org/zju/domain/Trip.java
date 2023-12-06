package org.zju.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Trip {
    @ExcelProperty("case")
    private int index;

    @ExcelProperty("pairings")
    private Integer pairings;
}
