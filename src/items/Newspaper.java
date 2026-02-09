package items;

import characters.Character;
import enums.EmotionState;
import exceptions.GameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Newspaper extends Item {
    private final Map<String, String> articles = new HashMap<>();
    private String newspaperTitle = "Пропажа Листика";

    public Newspaper(String name) {
        super(name);
    }

    public void setNewspaperTitle(String title) {
        this.newspaperTitle = Objects.requireNonNull(title);
    }

    public String fullTitle() {
        return getName() + " «" + newspaperTitle + "»";
    }

    public void addArticle(String title, String text) {
        articles.put(Objects.requireNonNull(title), Objects.requireNonNull(text));
    }

    public String getArticle(String title) {
        String t = articles.get(title);
        if (t == null) throw new GameException("в газете нет статьи: " + title);
        return t;
    }

    @Override
    public void use(Character user) {
        user.setState(EmotionState.CALM);
        System.out.println(user + " читает " + fullTitle() + ".");
    }
}