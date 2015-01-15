package io.github.eswn.bakuhatsu;

import static io.github.eswn.bakuhatsu.MineMap.Cover.*;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MineMapImpl implements MineMap{

    private final int width;
    private final int height;
    private final Cell[][] map;
    private int mineCount;
    private boolean firstDig;

    protected MineMapImpl(int width, int height, int mineCount){
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        map = new Cell[width][height];
        build();
    }

    @Override
    public void rebuild(){
        build();
    }

    @Override
    public void rebuild(int mineCount){
        this.mineCount = mineCount;
        build();
    }

    private void build(){
        firstDig = true;
        //重建map
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++)
                map[i][j] = new Cell();
        }
        //随机分布地雷
        Random rand = new Random();
        int amount = width * height;
        int[] randbox = new int[amount];
        for (int i = 0; i < amount; i++)
            randbox[i] = i;
        for (int i = 0; i < mineCount; i++){
            int index = rand.nextInt(amount - i);
            int value = randbox[index];
            map[value % width][value / width].mine = true;
            randbox[index] = randbox[amount - i - 1];
        }
        //计算地雷环绕数字
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (map[i][j].mine){
                    for (int m = -1; m <= 1; m++){
                        if (i + m < 0 || i + m >= width)
                            continue;
                        for (int n = -1; n <= 1; n++){
                            if (j + n < 0 || j + n >= height)
                                continue;
                            map[i + m][j + n].mineArround++;
                        }
                    }
                }
            }
        }
    }

    public void flag(int x, int y){
        if (!map[x][y].covered)
            return;
        map[x][y].cover = (map[x][y].cover == NONE ? FLAG : NONE);
        MineSweeper.getInstance().getGame().getHandler().drawCell(map[x][y], x, y);
    }

    public boolean dig(int x, int y){
        //无视已清理的或已标棋的格子
        if (!map[x][y].covered || map[x][y].cover == FLAG)
            return false;
        //第一次挖掘的特殊处理
        if (firstDig){
            firstDig = false;
            //如果挖掘的地方是雷将其移走
            if (map[x][y].mine){
                for (int m = -1; m <= 1; m++){
                    if (x + m < 0 || x + m >= width)
                        continue;
                    for (int n = -1; n <= 1; n++){
                        if (y + n < 0 || y + n >= height)
                            continue;
                        map[x + m][y + n].mineArround--;
                    }
                }
                Random rand = new Random();
                int index = rand.nextInt(width * height);
                while (map[index % width][index / width].mine || (index % width == x && index / width == y))
                    index = (index + 1) % (width * height);
                int newX = index % width;
                int newY = index / width;
                map[newX][newY].mine = true;
                for (int m = -1; m <= 1; m++){
                    if (newX + m < 0 || newX + m >= width)
                        continue;
                    for (int n = -1; n <= 1; n++){
                        if (newY + n < 0 || newY + n >= height)
                            continue;
                        map[newX + m][newY + n].mineArround++;
                    }
                }
            }
        }

        map[x][y].covered = false;
        MineSweeper.getInstance().getGame().getHandler().drawCell(map[x][y], x, y);
        if (map[x][y].mineArround == 0)
            clearSurround(x, y);
        return map[x][y].mine;
    }

    private void clearSurround(int x, int y){
        Deque<Point> queue = new ArrayDeque<Point>(width * height / 4);
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()){
            Point p = queue.poll();
            for (int m = -1; m <= 1; m++){
                if (p.x + m < 0 || p.x + m >= width)
                    continue;
                for (int n = -1; n <= 1; n++){
                    if (p.y + n < 0 || p.y + n >= height)
                        continue;
                    if (map[p.x + m][p.y + n].covered && map[p.x + m][p.y + n].mineArround == 0)
                        queue.offer(new Point(p.x + m, p.y + n));
                    map[p.x + m][p.y + n].covered = false;
                    MineSweeper.getInstance().getGame().getHandler().drawCell(map[p.x + m][p.y + n], p.x + m, p.y + n);
                }
            }
        }
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public Cell getCell(int x, int y){
        return map[x][y];
    }

    @Override
    public int getMineCount(){
        return mineCount;
    }

    @Override
    public boolean isClear(){
        for (Cell[] cells : map){
            for (Cell cell : cells){
                if (cell.covered && !(cell.mine && cell.cover == FLAG))
                    return false;
            }
        }
        return true;
    }

    public static class Cell implements MineMap.Cell{

        private Cover cover = NONE;
        private boolean covered = true;
        private int mineArround = 0;
        private boolean mine = false;

        @Override
        public Cover getCover(){
            return cover;
        }

        @Override
        public boolean isCovered(){
            return covered;
        }

        @Override
        public int getMineArround(){
            return mineArround;
        }

        @Override
        public boolean isMine(){
            return mine;
        }
    }
}
