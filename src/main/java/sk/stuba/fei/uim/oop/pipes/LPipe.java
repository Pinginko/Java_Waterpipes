package sk.stuba.fei.uim.oop.pipes;

import java.awt.*;
public class LPipe extends Pipe {
    public LPipe(){
        super();
        this.entryAngle = this.angle+270;
        this.entryAngle = checkAngle(this.entryAngle);
    }

    @Override
    public void setInitialAngle(int angle) {
        super.setInitialAngle(angle);
        this.entryAngle = angle+270;
        this.entryAngle = checkAngle(this.entryAngle);
    }

    @Override
    public void updateAngle(){
        super.updateAngle();
        this.entryAngle += 90;
        this.entryAngle = checkAngle(this.entryAngle);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D rect = (Graphics2D)g.create();
        rect.rotate(Math.toRadians(this.angle+180), this.getWidth()*0.5,this.getHeight()*0.5);
        rect.fillRect(0, (int) (this.getHeight() * 0.35),
                (int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.3));
        rect.fillRect((int) (this.getWidth() * 0.35), (int) (this.getHeight() * 0.35),
                (int) (this.getWidth() * 0.3), (int) (this.getHeight() * 0.65));
        rect.dispose();
    }
}
