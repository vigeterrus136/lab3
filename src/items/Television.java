package items;

import characters.Character;
import enums.EmotionState;

public class Television extends Item {
    private boolean on = false;

    public Television(String name) {
        super(name);
    }

    public void turnOn() {
        on = true;
    }

    @Override
    public void use(Character user) {
        if (!on) turnOn();
        user.setState(EmotionState.CALM);
        System.out.println(user + " смотрит телевизор.");
    }
}
