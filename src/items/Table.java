package items;

import java.util.Objects;

public class Table {
    private final String name;

    public Table(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
