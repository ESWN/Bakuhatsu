package io.github.eswn.bakuhatsu;

import static io.github.eswn.bakuhatsu.AbstractGame.State.*;

public class SingleGameImpl extends AbstractGame implements SingleGame{

    private Player player;
    private GameHandler handler;

    protected SingleGameImpl(MineMapImpl map, int maxLife, GameHandler handler){
        super(map, maxLife);
        this.player = new Player(maxLife);
        this.handler = handler;
        handler.start();
        handler.drawMap(map);
    }

    @Override
    public Player getPlayer(){
        return player;
    }

    @Override
    public GameHandler getHandler(){
        return handler;
    }

    @Override
    public void setHandler(GameHandler handler){
        this.handler = handler;
    }

    @Override
    public void dig(int x, int y){
        if (getState() != PLAYING)
            throw new IllegalStateException();
        if (getMap().dig(x, y)){
            getPlayer().decreaseLife();
            if (!getPlayer().isAlive()){
                getHandler().lose();
                setState(OVER);
            }
        }else{
            if (getMap().isClear()){
                getHandler().win();
                setState(OVER);
            }
        }
    }

    @Override
    public void flag(int x, int y){
        if (getState() != PLAYING)
            throw new IllegalStateException();
        getMap().flag(x, y);
        if (getMap().isClear()){
            getHandler().win();
            setState(OVER);
        }
    }

    @Override
    protected void doRestart(){
        super.doRestart();
        getPlayer().setLife(getMaxLife());
    }
}
