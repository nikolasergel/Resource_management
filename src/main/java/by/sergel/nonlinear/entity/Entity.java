package by.sergel.nonlinear.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Entity {
    private double h;
    private int S;
    private int D;
    private int n;
    private List<Double> price;
    private List<Integer> quantity;

    public Entity() {
    }
}
