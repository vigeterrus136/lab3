package characters;

import core.Personality;
import enums.CharacterType;
import enums.EmotionState;
import items.Newspaper;
import items.Television;

import java.util.Objects;
import java.util.Random;

public class Character {
    private final String name;
    private final CharacterType type;
    private EmotionState state;
    private final Personality personality;

    public Character(String name, CharacterType type, EmotionState state, Personality personality) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.state = Objects.requireNonNull(state);
        this.personality = Objects.requireNonNull(personality);
    }

    public Character(String name, CharacterType type, EmotionState state) {
        this(name, type, state, new Personality());
    }

    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public EmotionState getState() { return state; }
    public void setState(EmotionState s) { state = Objects.requireNonNull(s); }
    public Personality getPersonality() { return personality; }

    public boolean isEngaged() {
        return state == EmotionState.INTERESTED || state == EmotionState.EXCITED;
    }

    public void getUpsetBecauseLeaving() {
        setState(EmotionState.SAD);
        System.out.println(name + " расстраивается: пришлось прервать занятие и выходить.");
    }

    public void read(Newspaper paper) {
        paper.use(this);
        if (personality.getCuriosity() >= 70) {
            setState(EmotionState.INTERESTED);
            System.out.println(name + " увлекается прочитанным.");
        }
    }

    public void watch(Television tv) {
        tv.use(this);
        if (personality.getCuriosity() < 35) {
            setState(EmotionState.BORED);
            System.out.println(name + " быстро устает от передачи.");
        }
    }

    public void talkAbout(String topic) {
        setState(EmotionState.INTERESTED);
        System.out.println(name + " обсуждает: " + topic + ".");
    }

    public boolean decide(Random rnd) {
        return rnd.nextInt(100) < personality.getCuriosity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character that)) return false;
        return name.equals(that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return name;
    }
}
