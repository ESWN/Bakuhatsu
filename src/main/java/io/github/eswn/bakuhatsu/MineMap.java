package io.github.eswn.bakuhatsu;

import static io.github.eswn.bakuhatsu.MineMap.Cover.*;

public interface MineMap{

    Cell getCell(int x, int y);

    int getHeight();

    int getMineCount();

    int getWidth();

    boolean isClear();

    void rebuild();

    void rebuild(int mineCount);

    void flag(int x, int y);

    boolean dig(int x, int y);

    interface Cell{

        Cover getCover();

        boolean isCovered();

        int getMineArround();

        boolean isMine();
    }

    public enum Cover{

        NONE, FLAG
    }
}
