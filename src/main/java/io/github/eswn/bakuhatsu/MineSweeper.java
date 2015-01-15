package io.github.eswn.bakuhatsu;

public abstract class MineSweeper{

    private static MineSweeper instance = null;
    private Game game;

    protected MineSweeper(){
    }

    public Game getGame(){
        return game;
    }

    protected void setGame(Game game){
        this.game = game;
    }

    public static MineSweeper getInstance(){
        return instance;
    }

    protected static void setInstance(MineSweeper instance){
        MineSweeper.instance = instance;
    }
}
