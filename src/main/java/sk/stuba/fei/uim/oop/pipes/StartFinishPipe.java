package sk.stuba.fei.uim.oop.pipes;

import java.awt.*;

public class StartFinishPipe extends Pipe {

    public StartFinishPipe(){
        super();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D rect = (Graphics2D)g.create();
        rect.rotate(Math.toRadians(this.angle), this.getWidth()*0.5,this.getHeight()*0.5);
        rect.fillRect((int) (0 + this.getWidth() * 0.35), (int) (0 + this.getHeight() * 0.35),
                (int) (this.getWidth() * 0.65), (int) (this.getHeight() * 0.3));
        rect.fillOval((int) (0 + this.getWidth() * 0.175), (int) (0 + this.getHeight() * 0.175),
                (int) (this.getWidth() * 0.65), (int) (this.getHeight() * 0.65));

    }
}
