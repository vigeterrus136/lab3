package items;

import characters.Character;
import enums.ActivityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {
    private final String name;
    private final ActivityType activityType;

    private final List<Character> members = new ArrayList<>();
    private int membersCount = 0;

    public Table(String name, ActivityType activityType) {
        this.name = Objects.requireNonNull(name);
        this.activityType = Objects.requireNonNull(activityType);
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public List<Character> getMembers() {
        return List.copyOf(members);
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void addMember(Character c) {
        if (!members.contains(c)) {
            members.add(c);
            membersCount = members.size();
        }
    }

    public void removeMember(Character c) {
        members.remove(c);
        membersCount = members.size();
    }

    public void clear() {
        members.clear();
        membersCount = 0;
    }

    @Override
    public String toString() {
        return name + "{" + activityType + ", members=" + membersCount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table table)) return false;
        return name.equals(table.name) && activityType == table.activityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, activityType);
    }
}
