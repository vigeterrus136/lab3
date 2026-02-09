package core;

import characters.Character;
import items.Newspaper;

import java.util.Objects;

public class Discussion {
    private final String topic;

    public Discussion(String topic) {
        this.topic = Objects.requireNonNull(topic);
    }

    public void discuss(Character a, Character b, Newspaper paper) {
        a.setState(enums.EmotionState.INTERESTED);
        b.setState(enums.EmotionState.INTERESTED);
        System.out.println(a + " и " + b + " обсуждают: " + topic + " (по газете: Пропажа Листика)");
    }

    @Override
    public String toString() {
        return "Discussion(" + topic + ")";
    }
}
