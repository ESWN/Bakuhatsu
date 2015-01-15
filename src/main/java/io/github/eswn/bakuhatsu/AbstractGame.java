package io.github.eswn.bakuhatsu;

import static io.github.eswn.bakuhatsu.AbstractGame.State.*;

public abstract class AbstractGame implements Game{

    private MineMap map;
    private int maxLife;
    private State state;

    protected AbstractGame(MineMapImpl map, int maxLife){
        this.map = map;
        this.maxLife = maxLife;
        state = PLAYING;
    }

    @Override
    public MineMap getMap(){
        return map;
    }

    protected void setMap(MineMapImpl map){
        this.map = map;
    }

    protected int getMaxLife(){
        return maxLife;
    }

    protected void setMaxLife(int maxLife){
        this.maxLife = maxLife;
    }

    protected State getState(){
        return state;
    }

    protected void setState(State state){
        this.state = state;
    }

    @Override
    public void restart(){
        getMap().rebuild();
        doRestart();
    }

    @Override
    public void restart(int mineCount){
        getMap().rebuild(mineCount);
        doRestart();
    }

    @Override
    public void restart(int mineCount, int mapWidth, int mapHeight){
        setMap(new MineMapImpl(mapWidth, mapHeight, mineCount));
        doRestart();
    }

    protected void doRestart(){
        getHandler().start();
        getHandler().drawMap(getMap());
        setState(PLAYING);
    }

    protected enum State{

        PLAYING, OVER
    }
}
