package sk.stuba.fei.uim.oop.pipes;

import javax.swing.*;
import java.awt.*;

public abstract class Pipe extends JPanel {
    public Pipe() {
        this.angle = 0;
        this.check = false;
    }

    protected int angle;
    protected int entryAngle;
    protected boolean check;

    public int getAngle() {
        return this.angle;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setInitialAngle(int angle) {
        this.angle = angle;
    }
    public int getEntryAngle() {
        return this.entryAngle;
    }

    public void updateAngle(){
        this.angle += 90;
        this.angle = checkAngle(this.angle);
    }

    protected int checkAngle(int angleToCheck) {
        if (angleToCheck >= 360){
            angleToCheck= angleToCheck - 360;
        }
        return angleToCheck;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.RED);

        g.setColor(Color.GREEN);
        if(this.check) {
            g.setColor(Color.CYAN);
        }

    }
}
