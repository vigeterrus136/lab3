import characters.Character;
import characters.StoryTeller;
import core.Bus;
import core.Discussion;
import core.Location;
import core.Personality;
import data.Story;
import enums.*;
import exceptions.GameException;
import exceptions.WeaponException;
import items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random(System.nanoTime());

        Location start = new Location("Салон автобуса", LocationType.BUS);
        Location station = new Location("Станция", LocationType.STATION);
        Location park = new Location("Парк", LocationType.PARK);
        Location zoo = new Location("Зоопарк", LocationType.ZOO);

        Bus bus = new Bus("Автобус", List.of(start, station, park, zoo));

        Newspaper paper = new Newspaper("Газета");
        paper.addArticle("Исчезновение Листика", "Листик исчез, и это обсуждают везде.");

        Television tv = new Television("Телевизор");
        tv.turnOn();

        Game lotto = new Game("Лото", GameType.LOTTO);
        Game chess = new Game("Шахматы", GameType.CHESS);

        AirRifle rifle = new AirRifle("Пневматическое ружьё", 2, WeaponState.READY);

        Character neznayka = new Character("Незнайка", CharacterType.NEZNAIKA, EmotionState.CALM, new Personality(85));

        Character reader1 = new Character("Пассажир-читатель-1", CharacterType.PASSENGER, EmotionState.CALM, new Personality(30));
        Character reader2 = new Character("Пассажир-читатель-2", CharacterType.PASSENGER, EmotionState.CALM, new Personality(70));

        Character lottoGirl1 = new Character("Малышка-лото-1", CharacterType.KID, EmotionState.CALM, new Personality(60));
        Character lottoGirl2 = new Character("Малышка-лото-2", CharacterType.KID, EmotionState.CALM, new Personality(40));

        Character chessGirl1 = new Character("Малышка-шахматы-1", CharacterType.KID, EmotionState.CALM, new Personality(55));
        Character chessGirl2 = new Character("Малышка-шахматы-2", CharacterType.KID, EmotionState.CALM, new Personality(50));
        Character chessBoy1 = new Character("Малыш-шахматы-1", CharacterType.KID, EmotionState.CALM, new Personality(75));
        Character chessBoy2 = new Character("Малыш-шахматы-2", CharacterType.KID, EmotionState.CALM, new Personality(35));

        Character tvGirl1 = new Character("Малышка-ТВ-1", CharacterType.KID, EmotionState.CALM, new Personality(25));
        Character tvGirl2 = new Character("Малышка-ТВ-2", CharacterType.KID, EmotionState.CALM, new Personality(50));
        Character tvGirl3 = new Character("Малышка-ТВ-3", CharacterType.KID, EmotionState.CALM, new Personality(65));

        Character shooter1 = new Character("Стрелок-1", CharacterType.KID, EmotionState.CALM, new Personality(40));
        Character shooter2 = new Character("Стрелок-2", CharacterType.KID, EmotionState.CALM, new Personality(80));

        Character talker1 = new Character("Обсуждающий-1", CharacterType.KID, EmotionState.CALM, new Personality(75));
        Character talker2 = new Character("Обсуждающий-2", CharacterType.KID, EmotionState.CALM, new Personality(55));

        Story story = new Story("Про Бубенчика", "Бубенчик заблудился ночью и не мог найти дорогу домой.", "Бубенчик");
        StoryTeller storyteller = new StoryTeller("Рассказчик", EmotionState.CALM, story);

        ArrayList<Character> passengers = new ArrayList<>();
        passengers.addAll(List.of(
                neznayka,
                reader1, reader2,
                lottoGirl1, lottoGirl2,
                chessGirl1, chessGirl2, chessBoy1, chessBoy2,
                tvGirl1, tvGirl2, tvGirl3,
                shooter1, shooter2,
                talker1, talker2,
                storyteller
        ));

        System.out.println("Незнайка и его спутники вошли в автобус.");
        bus.board(passengers);

        // рассадка по зонам
        bus.placeToZone(reader1, ActivityType.NEWSPAPER);
        bus.placeToZone(reader2, ActivityType.NEWSPAPER);

        bus.placeToZone(lottoGirl1, ActivityType.LOTTO);
        bus.placeToZone(lottoGirl2, ActivityType.LOTTO);

        bus.placeToZone(chessGirl1, ActivityType.CHESS);
        bus.placeToZone(chessGirl2, ActivityType.CHESS);
        bus.placeToZone(chessBoy1, ActivityType.CHESS);
        bus.placeToZone(chessBoy2, ActivityType.CHESS);

        bus.placeToZone(tvGirl1, ActivityType.TV);
        bus.placeToZone(tvGirl2, ActivityType.TV);
        bus.placeToZone(tvGirl3, ActivityType.TV);

        bus.placeToZone(shooter1, ActivityType.RIFLE);
        bus.placeToZone(shooter2, ActivityType.RIFLE);

        System.out.println("\nВнутри автобуса пассажиры заняты делами:\n");

        reader1.read(paper);
        reader2.read(paper);

        try {
            lotto.play(lottoGirl1, lottoGirl2, rnd);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }

        try {
            chess.play(chessGirl1, chessGirl2, rnd);
            chess.play(chessBoy1, chessBoy2, rnd);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }

        tvGirl1.watch(tv);
        tvGirl2.watch(tv);
        tvGirl3.watch(tv);

        try {
            rifle.shoot(shooter1, rnd);
            rifle.shoot(shooter2, rnd);
        } catch (WeaponException e) {
            System.out.println(e.getMessage());
        }

        Discussion discussion = new Discussion("исчезновение Листика");
        discussion.discuss(talker1, talker2, paper);

        System.out.println("\nАвтобус продолжает путь по остановкам.\n");

        bus.goNextStop();
        bus.disembarkSome(rnd);

        bus.goNextStop();
        bus.disembarkSome(rnd);

        System.out.println("\nОдин пассажир начинает рассказывать историю...");
        if (neznayka.decide(rnd)) {
            neznayka.setState(EmotionState.INTERESTED);
            System.out.println("Незнайка внимательно слушает.");
        } else {
            neznayka.setState(EmotionState.BORED);
            System.out.println("Незнайка отвлекся и слушает вполуха.");
        }

        bus.goNextStop();

        try {
            storyteller.tell(true);
        } catch (GameException e) {
            System.out.println(e.getMessage());
            System.out.println("Пришлось выйти, не дослушав рассказ до конца.");
        }

        System.out.println("\nФинальная остановка. Все выходят.");
        bus.disembarkAll();

        System.out.println("\nСцена завершена.");
    }
}
