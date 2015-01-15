package io.github.eswn.bakuhatsu;

public interface SingleGame extends Game{

    void dig(int x, int y);

    void flag(int x, int y);

    Player getPlayer();

    void setHandler(GameHandler handler);
}
