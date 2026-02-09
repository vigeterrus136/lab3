package core;

import characters.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bus {
    private final String name;
    private final ArrayList<Character> passengers = new ArrayList<>();
    private final List<Location> route;
    private int routeIndex = 0;

    public Bus(String name, List<Location> route) {
        this.name = Objects.requireNonNull(name);
        this.route = List.copyOf(route);
    }

    public void board(List<Character> group) {
        passengers.addAll(group);
        System.out.println("Пассажиры зашли в " + name + ": " + group.size() + " человек.");
    }

    public Location currentLocation() {
        return route.get(routeIndex);
    }

    public void goNextStop() {
        if (routeIndex < route.size() - 1) {
            routeIndex++;
        }
        System.out.println("Автобус остановился: " + currentLocation().getName() + ".");
    }

    public ArrayList<Character> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;
        return routeIndex == bus.routeIndex && name.equals(bus.name) && route.equals(bus.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, route, routeIndex);
    }

    @Override
    public String toString() {
        return name + "@" + currentLocation().getName();
    }
}
