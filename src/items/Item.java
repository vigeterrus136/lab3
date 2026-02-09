package items;

import characters.Character;

import java.util.Objects;

public abstract class Item implements Usable {
    private final String name;

    protected Item(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() { return name; }

    @Override
    public abstract void use(Character user);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

interface Usable {
    void use(Character user);
}
