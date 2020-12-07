package by.sergel.nonlinear.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Calculations {
    private List<Double> price;
    private List<Integer> EOQ;
    private List<Integer> TH;
    private List<Integer> TS;
    private List<Double> orderCount;
    private List<Integer> realQ;
    private List<Integer> T;
    private List<Integer> finalT;


    public Calculations() {
        this.EOQ = new ArrayList<>();
        this.TH = new ArrayList<>();
        this.TS = new ArrayList<>();
        this.orderCount = new ArrayList<>();
        this.realQ = new ArrayList<>();
        this.price = new ArrayList<>();
        this.T = new ArrayList<>();
        this.finalT = new ArrayList<>();
    }
}
