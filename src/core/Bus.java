package core;

import characters.Character;
import enums.ActivityType;
import items.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Bus {
    private final String name;

    private final ArrayList<Character> passengers = new ArrayList<>();
    private final List<Location> route;
    private int routeIndex = 0;

    private final List<Table> zones = new ArrayList<>();

    public Bus(String name, List<Location> route) {
        this.name = Objects.requireNonNull(name);
        this.route = List.copyOf(route);

        zones.add(new Table("Зона-Газета", ActivityType.NEWSPAPER));
        zones.add(new Table("Зона-Лото", ActivityType.LOTTO));
        zones.add(new Table("Зона-Шахматы", ActivityType.CHESS));
        zones.add(new Table("Зона-ТВ", ActivityType.TV));
        zones.add(new Table("Зона-Стрельба", ActivityType.RIFLE));
    }

    public List<Table> getZones() {
        return List.copyOf(zones);
    }

    public Table getZone(ActivityType type) {
        for (Table t : zones) {
            if (t.getActivityType() == type) return t;
        }
        return null;
    }

    public void placeToZone(Character c, ActivityType type) {
        Table z = getZone(type);
        if (z != null) z.addMember(c);
    }

    public void board(List<Character> group) {
        for (Character c : group) enter(c);
        System.out.println("Пассажиры зашли в " + name + ": " + group.size() + " человек.");
    }

    public void enter(Character c) {
        if (!passengers.contains(c)) passengers.add(c);
    }

    public void exit(Character c) {
        if (!passengers.contains(c)) return;

        if (c.isEngaged()) {
            c.getUpsetBecauseLeaving();
        }

        passengers.remove(c);
        for (Table z : zones) z.removeMember(c);

        System.out.println(c + " выходит из автобуса.");
    }

    public Location currentLocation() {
        return route.get(routeIndex);
    }

    public boolean isAtLastStop() {
        return routeIndex >= route.size() - 1;
    }

    public void goNextStop() {
        if (routeIndex < route.size() - 1) routeIndex++;
        System.out.println("Автобус остановился: " + currentLocation().getName() + ".");
    }

    public void disembarkSome(Random rnd) {
        if (isAtLastStop()) return;

        ArrayList<Character> leaving = new ArrayList<>();
        for (Character c : passengers) {

            if (rnd.nextInt(100) < 25) {
                leaving.add(c);
            }
        }

        if (leaving.isEmpty()) {
            System.out.println("Никто не выходит на этой остановке.");
            return;
        }

        System.out.println("Часть пассажиров выходит на остановке.");
        for (Character c : leaving) exit(c);
    }

    public void disembarkAll() {
        ArrayList<Character> leaving = new ArrayList<>(passengers);
        for (Character c : leaving) exit(c);
    }

    public ArrayList<Character> getPassengers() {
        return passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;
        return routeIndex == bus.routeIndex && name.equals(bus.name) && route.equals(bus.route) && zones.equals(bus.zones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, route, routeIndex, zones);
    }

    @Override
    public String toString() {
        return name + "@" + currentLocation().getName();
    }
}
