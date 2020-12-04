package by.sergel.nonlinear.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component

public class Entity {
    private int h;
    private int S;
    private int D;
    private int n;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return h == entity.h &&
                S == entity.S &&
                D == entity.D &&
                n == entity.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, S, D, n);
    }
}
