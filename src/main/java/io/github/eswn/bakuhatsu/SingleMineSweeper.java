package io.github.eswn.bakuhatsu;

public class SingleMineSweeper extends MineSweeper{

    private static final SingleMineSweeper instance;

    static{
        instance = new SingleMineSweeper();
    }

    protected SingleMineSweeper(){
    }

    @Override
    public SingleGameImpl getGame(){
        return (SingleGameImpl)super.getGame();
    }

    public void createGame(SingleGameBuilder builder){
        setGame(builder.create());
        MineSweeper.setInstance(this);
    }

    public static SingleMineSweeper getInstance(){
        return instance;
    }
}
