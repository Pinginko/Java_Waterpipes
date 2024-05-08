package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.pipes.Pipe;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel{

    private boolean highlight;
    private int xCoord;
    private int yCoord;
    private Pipe pipe;

    public Tile(Pipe pipe){
        this.setBackground(Color.RED);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
        this.setLayout(new GridLayout(1,1));
        setPipe(pipe);
        this.add(pipe,BorderLayout.CENTER);
        this.highlight = false;
        this.xCoord = 0;
        this.yCoord = 0;
    }
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }
    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.highlight){
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
            this.highlight = false;

        }

        else {
            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,3));
        }

    }
}

