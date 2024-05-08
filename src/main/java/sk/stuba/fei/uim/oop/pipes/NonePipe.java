package sk.stuba.fei.uim.oop.pipes;


import java.awt.*;

public class NonePipe extends Pipe {

    public NonePipe(){
        super();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.RED);
    }

}
