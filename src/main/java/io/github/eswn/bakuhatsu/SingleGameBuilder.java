package io.github.eswn.bakuhatsu;

public class SingleGameBuilder{

    private boolean build = false;
    private int mapWidth;
    private int mapHeight;
    private int mapMineCount;
    private int maxLife;
    private GameHandler gameHandler;

    public SingleGameBuilder(){
        maxLife = Integer.parseInt(Util.getConfig("single.defaultMaxLife"));
    }

    public SingleGameBuilder setMapWidth(int mapWidth){
        if (mapWidth <= 0)
            throw new IllegalArgumentException();
        this.mapWidth = mapWidth;
        return this;
    }

    public SingleGameBuilder setMapHeight(int mapHeight){
        if (mapHeight <= 0)
            throw new IllegalArgumentException();
        this.mapHeight = mapHeight;
        return this;
    }

    public SingleGameBuilder setMapMineCount(int mapMineCount){
        if (mapMineCount <= 0)
            throw new IllegalArgumentException();
        this.mapMineCount = mapMineCount;
        return this;
    }

    public SingleGameBuilder setMaxLife(int maxLife){
        if (maxLife <= 0)
            throw new IllegalArgumentException();
        this.maxLife = maxLife;
        return this;
    }

    public SingleGameBuilder setGameHandler(GameHandler handler){
        this.gameHandler = handler;
        return this;
    }

    protected SingleGameImpl create(){
        if (build)
            throw new IllegalStateException();
        build = true;
        if (gameHandler == null)
            gameHandler = new DefaultGameHandler();
        return new SingleGameImpl(new MineMapImpl(mapWidth, mapHeight, mapMineCount), maxLife, gameHandler);
    }
}
