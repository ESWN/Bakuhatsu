package io.github.eswn.bakuhatsu;

public class Player{

    private int life = 1;

    protected Player(int life){
        this.life = life;
    }

    public int getLife(){
        return life;
    }

    public void setLife(int life){
        this.life = life;
    }

    public void decreaseLife(){
        life--;
    }

    public boolean isAlive(){
        return life > 0;
    }
}
