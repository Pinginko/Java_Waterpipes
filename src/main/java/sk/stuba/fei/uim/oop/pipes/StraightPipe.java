package sk.stuba.fei.uim.oop.pipes;

import java.awt.*;

public class StraightPipe extends Pipe {

    public StraightPipe(){
        super();
        this.entryAngle = this.angle+180;
    }

    @Override
    public void updateAngle(){
        super.updateAngle();
        this.entryAngle = this.angle + 180;
        this.entryAngle = checkAngle(this.entryAngle);
    }

    @Override
    public void setInitialAngle(int angle) {
        super.setInitialAngle(angle);
        this.entryAngle = this.angle + 180;
        this.entryAngle = checkAngle(this.entryAngle);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D rect = (Graphics2D)g.create();
        rect.rotate(Math.toRadians(this.angle), this.getWidth()*0.5,this.getHeight()*0.5);
        rect.fillRect(0, (int) (0 + this.getHeight() * 0.35),
                (this.getWidth()), (int) (this.getHeight() * 0.3));

        rect.dispose();

    }
}
