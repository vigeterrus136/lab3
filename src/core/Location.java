package core;

import enums.LocationType;

import java.util.Objects;

public class Location {
    private final String name;
    private final LocationType type;

    public Location(String name, LocationType type) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
    }

    public String getName() { return name; }
    public LocationType getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location that)) return false;
        return name.equals(that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name + "(" + type + ")";
    }
}
