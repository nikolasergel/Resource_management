package by.sergel.nonlinear.entity;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component

public class Entity {
    private int h;
    private int S;
    private int D;
    private int n;
    private List<Integer> price;
    private List<Integer> quantity;

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public void setPrice(List<Integer> price) {
        this.price = price;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return h == entity.h &&
                S == entity.S &&
                D == entity.D &&
                n == entity.n &&
                Objects.equals(price, entity.price) &&
                Objects.equals(quantity, entity.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, S, D, n, price, quantity);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "h=" + h +
                ", S=" + S +
                ", D=" + D +
                ", n=" + n +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
