package io.github.eswn.bakuhatsu;

public interface Game{

    MineMap getMap();

    GameHandler getHandler();

    void restart();

    void restart(int mineCount);

    void restart(int mineCount, int mapWidth, int mapHeight);
}
