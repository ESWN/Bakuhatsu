package io.github.eswn.bakuhatsu;

public class MultiplayerGameBuilder{

    private boolean build = false;
    private MultiplayerGameBuilderHandler handler;
    private int mapWidth;
    private int mapHeight;
    private int mapMineCount;
    private int maxLife;
    private PlayerGroup playerGroup;
    private ListGameHandler listGameHandler = new ListGameHandler();

    public MultiplayerGameBuilder(){
        maxLife = Integer.parseInt(Util.getConfig("multiplayer.defaultMaxLife"));
    }

    public MultiplayerGameBuilder setMapWidth(int mapWidth){
        if (mapWidth <= 0)
            throw new IllegalArgumentException();
        this.mapWidth = mapWidth;
        return this;
    }

    public MultiplayerGameBuilder setMapHeight(int mapHeight){
        if (mapHeight <= 0)
            throw new IllegalArgumentException();
        this.mapHeight = mapHeight;
        return this;
    }

    public MultiplayerGameBuilder setMapMineCount(int mapMineCount){
        if (mapMineCount <= 0)
            throw new IllegalArgumentException();
        this.mapMineCount = mapMineCount;
        return this;
    }

    public MultiplayerGameBuilder setMaxLife(int maxLife){
        if (maxLife <= 0)
            throw new IllegalArgumentException();
        this.maxLife = maxLife;
        return this;
    }

    public MultiplayerGameBuilder addGameHandler(GameHandler handler){
        listGameHandler.addHandler(handler);
        return this;
    }

    public MultiplayerGameBuilder removeGameHandler(GameHandler handler){
        listGameHandler.removeHandler(handler);
        return this;
    }

    protected MultiplayerGameImpl create(){
        if (build)
            throw new IllegalStateException();
        build = true;
        return new MultiplayerGameImpl(new MineMapImpl(mapWidth, mapHeight, mapMineCount), maxLife, playerGroup, listGameHandler);
    }
}
