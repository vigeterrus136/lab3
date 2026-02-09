package core;

import java.util.Objects;

public class Personality {
    private final int curiosity; // 0..100

    public Personality() {
        this.curiosity = 50;
    }

    public Personality(int curiosity) {
        this.curiosity = Math.max(0, Math.min(100, curiosity));
    }

    public int getCuriosity() {
        return curiosity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personality that)) return false;
        return curiosity == that.curiosity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(curiosity);
    }

    @Override
    public String toString() {
        return "Personality(curiosity=" + curiosity + ")";
    }
}
