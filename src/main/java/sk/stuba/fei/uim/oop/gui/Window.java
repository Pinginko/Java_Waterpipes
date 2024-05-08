package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.Logic;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final String[] LEVEL_OPTIONS = {"8x8","10x10","12x12"};

    public Window(){
        JFrame window = new JFrame("WaterPipes");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640,640);
        window.setResizable(false);
        window.setFocusable(true);

        Logic logic = new Logic(window);
        window.addKeyListener(logic);

        JPanel menu = new JPanel(new GridLayout(1,5));

        JComboBox levelSize = new JComboBox<>(LEVEL_OPTIONS);
        levelSize.addActionListener(logic);
        logic.setCombBox(levelSize);

        menu.add(logic.getBoardSizeLabel());
        menu.add(logic.getLevelLabel());
        menu.add(levelSize);

        JButton restartButton = new JButton("Restart");
        logic.setjButtonRestart(restartButton);
        menu.add(restartButton);

        JButton checkButton = new JButton("Check");
        logic.setjButtonCheck(checkButton);
        menu.add(checkButton);

        window.add(menu,BorderLayout.PAGE_START);
        window.add(logic.getCurrentBoard());

        window.setVisible(true);
    }

}
