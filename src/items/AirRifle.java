package items;

import characters.Character;
import enums.EmotionState;
import enums.WeaponState;
import exceptions.WeaponException;

import java.util.Objects;
import java.util.Random;

public class AirRifle extends Item {
    private int ammo;
    private WeaponState state;

    public AirRifle(String name, int ammo, WeaponState state) {
        super(name);
        this.ammo = ammo;
        this.state = Objects.requireNonNull(state);
    }

    public void setState(WeaponState state) {
        this.state = Objects.requireNonNull(state);
    }

    public void shoot(Character shooter, Random rnd) throws WeaponException {
        if (state != WeaponState.READY) throw new WeaponException("ружьё не готово");
        if (ammo <= 0) throw new WeaponException("пульки закончились");

        ammo--;
        boolean hit = rnd.nextBoolean();
        if (hit) {
            shooter.setState(EmotionState.EXCITED);
            System.out.println(shooter + " стреляет и попадает в цель! (ammo=" + ammo + ")");
        } else {
            shooter.setState(EmotionState.CALM);
            System.out.println(shooter + " стреляет, но мажет. (ammo=" + ammo + ")");
        }
    }

    @Override
    public void use(Character user) {
        System.out.println(user + " держит ружьё.");
    }
}
