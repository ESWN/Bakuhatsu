package io.github.eswn.bakuhatsu.ui;

import io.github.eswn.bakuhatsu.MineMap;
import io.github.eswn.bakuhatsu.MineMapImpl;
import io.github.eswn.bakuhatsu.SingleMineSweeper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class MineMapViewer extends JComponent{

    private static final int CELL_SIZE = 25;
    private Image buffer;
    private Point position;

    public MineMapViewer(){
        initComponents();
        position = new Point();
        addMouseListener(new PrivateListener());
    }

    public MineMapViewer(int width, int height){
        this();
        buffer = createImage(width, height);
    }

    public void drawCell(MineMap.Cell cell, int x, int y){
        Graphics g = buffer.getGraphics();
        g.setColor(new Color(0x808080));
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        if (cell.isCovered()){
            if (cell.getCover() == MineMapImpl.Cover.NONE)
                g.drawImage(ImageResource.getImage("cell.cover.none"), x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, this);
            else if (cell.getCover() == MineMapImpl.Cover.FLAG)
                g.drawImage(ImageResource.getImage("cell.cover.flag"), x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, this);
        }else{
            if (cell.isMine())
                g.drawImage(ImageResource.getImage("cell.mine"), x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, this);
            else
                g.drawImage(ImageResource.getImage("cell.mineAround" + cell.getMineArround()), x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, this);
        }
    }

    public void drawMap(MineMap map){
        for (int i = 0; i < map.getWidth(); i++){
            for (int j = 0; j < map.getHeight(); j++){
                drawCell(map.getCell(i, j), i, j);
            }
        }
    }

    public void rebuild(int width, int height){
        buffer = createImage(width * CELL_SIZE, height * CELL_SIZE);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (buffer != null){
            g.drawImage(buffer, position.x, position.y, this);
        }
    }

    private class PrivateListener extends MouseAdapter{

        @Override
        public void mouseClicked(MouseEvent e){
            if (!e.getComponent().isEnabled())
                return;
            switch (e.getButton()){
                case MouseEvent.BUTTON1:
                    SingleMineSweeper.getInstance().getGame().dig(e.getX() / CELL_SIZE, e.getY() / CELL_SIZE);
                    break;
                case MouseEvent.BUTTON3:
                    SingleMineSweeper.getInstance().getGame().flag(e.getX() / CELL_SIZE, e.getY() / CELL_SIZE);
                    break;
            }
            repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(){
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}