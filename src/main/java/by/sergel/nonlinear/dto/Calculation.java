package by.sergel.nonlinear.dto;

import lombok.Data;

@Data
public class Calculation implements Comparable{
    private Integer min;
    private Integer max;
    private Double price;
    private Integer EOQ;
    private Integer TH;
    private Integer TS;
    private Double orderCount;
    private Integer realQ;
    private Integer T;
    private Integer finalT;

    @Override
    public int compareTo(Object obj) {
        Calculation calc = (Calculation) obj;
        return finalT.compareTo(calc.finalT);
    }
}
