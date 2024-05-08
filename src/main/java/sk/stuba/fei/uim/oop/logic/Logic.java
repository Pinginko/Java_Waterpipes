package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.board.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Logic extends UniversalAdapter{

    public static final int INITIAL_BOARD_SIZE = 8;

    private JFrame mainGame;

    private JLabel boardSizeLabel;

    private Board currentBoard;

    private int currentBoardSize;

    private JLabel levelLabel;
    private int level;

    private JComboBox combBox;

    private JButton jButtonRestart;

    private JButton jButtonCheck;
    public Logic(JFrame frame){
        this.mainGame = frame;
        this.currentBoardSize = INITIAL_BOARD_SIZE;
        this.initializeNewBoard(INITIAL_BOARD_SIZE);
        this.mainGame.add(currentBoard);
        this.boardSizeLabel = new JLabel();
        this.levelLabel = new JLabel();
        this.updateBoardSizeLabel();
        this.updateLevelLabel();
        jButtonRestart = new JButton();
        combBox = new JComboBox<>();
        jButtonCheck = new JButton();
        this.level = 0;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }
    public JLabel getLevelLabel() {
        return levelLabel;
    }
    public JLabel getBoardSizeLabel() {
        return boardSizeLabel;
    }

    public void setCombBox(JComboBox combBox) {
        this.combBox = combBox;
        this.combBox.addActionListener(this);

    }

    public void setjButtonRestart(JButton jButtonRestart) {
        this.jButtonRestart = jButtonRestart;
        this.jButtonRestart.addActionListener(this);
    }

    public void setjButtonCheck(JButton jButtonCheck) {
        this.jButtonCheck = jButtonCheck;
        this.jButtonCheck.addActionListener(this);
    }
    private void initializeNewBoard(int dimension) {
        this.currentBoard = new Board(dimension);
        this.currentBoard.addMouseMotionListener(this);
        this.currentBoard.addMouseListener(this);
    }

    private void gameRestart() {
        this.mainGame.remove(this.currentBoard);
        this.initializeNewBoard(this.currentBoardSize);
        this.mainGame.add(this.currentBoard);
        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();

    }

    private void checkPath(){
        if(currentBoard.checkWinState()) {
            this.gameRestart();
            this.level+=1;
        }
        this.updateLevelLabel();
        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }



    private void updateBoardSizeLabel() {
        this.boardSizeLabel.setText("BOARD SIZE: " + this.currentBoardSize);
        this.mainGame.revalidate();
        this.mainGame.repaint();
    }

    private void updateLevelLabel(){
        this.levelLabel.setText("LEVEL: " + this.level);
        this.mainGame.revalidate();
        this.mainGame.repaint();

    }


    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        currentBoard.disableCheckBoard(currentBoardSize);
        Component current =  this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (current instanceof Tile){
            ((Tile) current).setHighlight(true);
        }else {
            return;
        }
        this.currentBoard.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource()==combBox){

            if (combBox.getSelectedIndex()==0){
                this.currentBoardSize = 8;
                this.updateBoardSizeLabel();
                this.level = 0;
                this.updateLevelLabel();
                this.gameRestart();

            } else if (combBox.getSelectedIndex()==1) {
                this.currentBoardSize = 10;
                this.updateBoardSizeLabel();
                this.level = 0;
                this.updateLevelLabel();
                this.gameRestart();


            }else {
                this.currentBoardSize = 12;
                this.updateBoardSizeLabel();
                this.level = 0;
                this.updateLevelLabel();
                this.gameRestart();

            }

        } else if (e.getSource() == jButtonRestart ) {
            gameRestart();
            this.updateBoardSizeLabel();
            this.level = 0;
            this.updateLevelLabel();

        } else if (e.getSource() == jButtonCheck) {
            checkPath();
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        switch (e.getKeyCode()){

            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
                System.exit(0);
                break;

            case KeyEvent.VK_R:
                this.gameRestart();
                this.level = 0;
                this.updateLevelLabel();
                break;

            case KeyEvent.VK_ENTER:
                this.checkPath();
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Component current =  this.currentBoard.getComponentAt(e.getX(), e.getY());
        if (current instanceof Tile){

            ((Tile)current).getPipe().updateAngle();
            current.repaint();
        }
    }


   /*
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == crossButt) {
            Cross tree1 = new Cross(this.activeColor);
            this.shapesCross.add(tree1);
            this.drawingPanel.add(tree1);
            this.drawingPanel.revalidate();
            this.drawingPanel.repaint();

        else if (e.getComponent().getComponentAt(e.getX(), e.getY()) instanceof Shape) {
            System.out.println("Toto je kriz");
            for (Shape shape : this.shapesCross) {
                if( e.getX() >= shape.getX() && (e.getX()  <= shape.getX()+shape.getWidth()) &&
                        e.getY() >= shape.getY() && (e.getY() <= shape.getY()+shape.getHeight())) {
                    //this.drawingCanvas.remove(shape);
                    this.activeShape = shape;
                    shape.setColor(this.activeColor);
                   //REPAINT REVALIDATE
                    //this.action = true;
                    return;*/


}
