package io.github.eswn.bakuhatsu;

public class MultiplayerGameImpl extends AbstractGame implements MultiplayerGame{

    private PlayerGroup playerGroup;
    private ListGameHandler handler;

    protected MultiplayerGameImpl(MineMapImpl map, int maxLife, PlayerGroup playerGroup, ListGameHandler handler){
        super(map, maxLife);
        this.playerGroup = playerGroup;
        this.handler = handler;
        handler.start();
        handler.drawMap(map);
    }

    @Override
    public GameHandler getHandler(){
        return handler;
    }

    @Override
    public void addHandler(GameHandler handler){
        this.handler.addHandler(handler);
    }

    @Override
    public void removeHandler(GameHandler handler){
        this.handler.removeHandler(handler);
    }

    @Override
    public void dig(Player player, int x, int y){
        throw new UnsupportedOperationException("Not supported yet."); // TODO: implements it.
    }

    @Override
    public void flag(Player player, int x, int y){
        throw new UnsupportedOperationException("Not supported yet."); // TODO: implements it.
    }

    @Override
    public PlayerGroup getPlayerGroup(){
        throw new UnsupportedOperationException("Not supported yet."); // TODO: implements it.
    }
}
