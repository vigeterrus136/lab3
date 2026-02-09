package characters;

import data.Story;
import enums.CharacterType;
import enums.EmotionState;
import exceptions.GameException;

import java.util.Objects;

public class StoryTeller extends Character {
    private final Story story;
    private boolean finished = false;

    public StoryTeller(String name, EmotionState state, Story story) {
        super(name, CharacterType.PASSENGER, state);
        this.story = Objects.requireNonNull(story);
    }

    public void tell(boolean interrupted) {
        setState(EmotionState.INTERESTED);
        System.out.println(this + " рассказывает: " + story.title());

        if (interrupted) {
            throw new GameException("рассказ прерван остановкой, концовку не дослушали");
        }

        finished = true;
        System.out.println(this + " заканчивает рассказ.");
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof StoryTeller;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), "StoryTeller");
    }
}
