package io.github.eswn.bakuhatsu;

import java.util.List;

public class PlayerGroup{

    private Player localPlayer;
    private List<Player> players;

    protected PlayerGroup(Player localPlayer, List<Player> players){
        this.localPlayer = localPlayer;
        this.players = players;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }

    public Player getLocalPlayer(){
        return localPlayer;
    }

    public boolean hasAlivePlayer(){
        for (Player player: players){
            if (player.isAlive()) return true;
        }
        return false;
    }
}
