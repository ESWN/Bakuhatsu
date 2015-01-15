package io.github.eswn.bakuhatsu;

import java.util.HashSet;
import java.util.Set;

public class ListGameHandler implements GameHandler{

    private Set<GameHandler> handlers = new HashSet<GameHandler>();

    public void addHandler(GameHandler handler){
        handlers.add(handler);
    }

    public void removeHandler(GameHandler handler){
        handlers.remove(handler);
    }

    @Override
    public void start(){
        for (GameHandler handler : handlers)
            handler.start();
    }

    @Override
    public void playerDie(Player player){
        for (GameHandler handler : handlers)
            handler.playerDie(player);
    }

    @Override
    public void drawCell(MineMap.Cell cell, int x, int y){
        for (GameHandler handler : handlers)
            handler.drawCell(cell, x, y);
    }

    @Override
    public void drawMap(MineMap map){
        for (GameHandler handler : handlers)
            handler.drawMap(map);
    }

    @Override
    public void win(){
        for (GameHandler handler : handlers)
            handler.win();
    }

    @Override
    public void lose(){
        for (GameHandler handler : handlers)
            handler.lose();
    }
}
