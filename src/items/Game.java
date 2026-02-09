package items;

import characters.Character;
import enums.EmotionState;
import enums.GameType;
import exceptions.GameException;

import java.util.Objects;
import java.util.Random;

public class Game extends Item {
    private final GameType type;

    public Game(String name, GameType type) {
        super(name);
        this.type = Objects.requireNonNull(type);
    }

    public GameType getType() { return type; }

    public void play(Character a, Character b, Random rnd) {
        if (a.equals(b)) throw new GameException("нельзя играть самому с собой"); // unchecked
        int roll = rnd.nextInt(100);
        if (roll < 40) {
            a.setState(EmotionState.EXCITED);
            b.setState(EmotionState.BORED);
            System.out.println(a + " выигрывает в " + type + " у " + b + ".");
        } else {
            a.setState(EmotionState.INTERESTED);
            b.setState(EmotionState.INTERESTED);
            System.out.println(a + " и " + b + " играют в " + type + " (партия идёт).");
        }
    }

    @Override
    public void use(Character user) {
        user.setState(EmotionState.INTERESTED);
        System.out.println(user + " трогает игру " + type + ".");
    }
}
