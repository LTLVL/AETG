package org.zju.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.zju.util.Initializer;

import java.time.LocalDate;

@Data
public class Trip {
    @ExcelProperty("case")
    private int index;
    @ExcelProperty("出发地")
    private String source;
    @ExcelProperty("目的地")
    private String destination;
    @ExcelProperty("出发日期")
    private LocalDate departureDate;
    @ExcelProperty("成人")
    private Integer adult;
    @ExcelProperty("儿童")
    private Integer child;
    @ExcelProperty("婴儿")
    private Integer baby;
    @ExcelProperty("舱")
    private String airClass;
    @ExcelProperty("直飞")
    private Boolean directFlight;
    @ExcelProperty("pairings")
    private Integer pairings;

    public Trip(int index,Integer s0, Integer s1, Integer s2, Integer s3, Integer s4, Integer s5,
                  Integer s6, Integer s7, Integer s8) {
        this.index = index;
        this.source = (String) Initializer.initTripList().get(0).get(s0);
        this.destination = (String) Initializer.initTripList().get(1).get(s1);
        this.departureDate = (LocalDate) Initializer.initTripList().get(2).get(s2);
        this.adult = (Integer) Initializer.initTripList().get(3).get(s3);
        this.child = (Integer) Initializer.initTripList().get(4).get(s4);
        this.baby = (Integer) Initializer.initTripList().get(5).get(s5);
        this.airClass = (String) Initializer.initTripList().get(6).get(s6);
        this.directFlight = (Boolean) Initializer.initTripList().get(7).get(s7);
        this.pairings = s8;
    }
}
