package io.github.eswn.bakuhatsu;

public interface MultiplayerGame extends Game{

    void dig(Player player, int x, int y);

    void flag(Player player, int x, int y);

    PlayerGroup getPlayerGroup();

    void addHandler(GameHandler handler);

    void removeHandler(GameHandler handler);
}
