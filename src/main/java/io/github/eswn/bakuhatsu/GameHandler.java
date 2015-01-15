package io.github.eswn.bakuhatsu;

public interface GameHandler{

    void start();

    void playerDie(Player player);

    void drawCell(MineMap.Cell cell, int x, int y);

    void drawMap(MineMap map);

    void win();

    void lose();
}
