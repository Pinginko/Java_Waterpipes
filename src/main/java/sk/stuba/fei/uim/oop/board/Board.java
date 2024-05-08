package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.pipes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Board extends JPanel {

    private Tile [][] board;

    private Random random;

    private Tile startingPoint;
    private Tile finishPoint;

    private ArrayList<Tile> neighbors;

    private Tile currentTile;

    private boolean finish;


    private ArrayList<Tile> visited;


    private Stack<Tile> stack;

    public Board(int dimension){
        this.stack = new Stack<>();
        this.neighbors = new ArrayList<>();
        this.board = new Tile[dimension][dimension];
        this.finish = false;
        this.visited = new ArrayList<>();
        this.random = new Random();
        initializeBoard(dimension);

    }

    private int generateRandomNumber(int number){
        return random.nextInt(number);
    }

    private void setBoard(int i, int j, Pipe pipe) {
        this.board[i][j].setPipe(pipe);
        pipe.setInitialAngle(90* generateRandomNumber(4));
        this.board[i][j].remove(0);
        this.board[i][j].add(pipe);
    }

    private void initializeBoard(int dimension){
        this.setLayout(new GridLayout(dimension, dimension));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = new Tile(new NonePipe());

            }
        }

        generateStartTiles(dimension);
        while (!finish){

            depthAlgorithm(this.currentTile, dimension);
        }

        givePipesToPath(this.stack);
        addToBoard(dimension);
    }


    private void addToBoard(int dimension){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                this.add(this.board[i][j]);
            }
        }
    }


    private void givePipesToPath(Stack stack){
        int x = 1;
        ArrayList<Tile>path = new ArrayList<>(stack);
        while (x != path.size()-1){

            generateCorrectPipes(x,path);

            x++;
        }
        generateCorrectPipesEnd(x,path);
    }


    private void generateCorrectPipes(int x, ArrayList<Tile> cesta){

        if ((cesta.get(x-1).getxCoord() == cesta.get(x+1).getxCoord()) ||
                (cesta.get(x-1).getyCoord() == cesta.get(x+1).getyCoord())){
            setBoard(cesta.get(x).getxCoord(), cesta.get(x).getyCoord(), new StraightPipe());


        } else {
            setBoard(cesta.get(x).getxCoord(), cesta.get(x).getyCoord(), new LPipe());
        }
    }


    private void generateCorrectPipesEnd(int x, ArrayList<Tile> cesta){

        if ((cesta.get(x-1).getxCoord() == finishPoint.getxCoord()) ||
                (cesta.get(x-1).getyCoord() == finishPoint.getyCoord())){
            setBoard(cesta.get(x).getxCoord(), cesta.get(x).getyCoord(), new StraightPipe());

        } else {
            setBoard(cesta.get(x).getxCoord(), cesta.get(x).getyCoord(), new LPipe());
        }
    }



    private boolean checkvisitedNeighbor(Tile current, ArrayList<Tile> visitedNeighbors){

        for (Tile tile : visitedNeighbors){

            if (tile.equals(current)){

                return false;
            }

        }
        return true;
    }


    private void generateStartTiles(int dimension){
        int poles = generateRandomNumber(2);
        int collumn1 = generateRandomNumber(dimension-1);
        int collumn2 = generateRandomNumber(dimension-1);

        if (poles == 1){

            this.startingPoint = this.board[collumn1][0];
            this.finishPoint = this.board[collumn2][dimension-1];
            setBoard(collumn1,0,new StartFinishPipe());
            setBoard(collumn2,dimension-1,new StartFinishPipe());
            this.currentTile = this.board[collumn1][0];
            this.finishPoint.setxCoord(collumn2);
            this.finishPoint.setyCoord(dimension-1);
        }
        else {

            this.startingPoint = this.board[0][collumn1];
            this.finishPoint = this.board[dimension-1][collumn2];
            setBoard(0,collumn1,new StartFinishPipe());
            setBoard(dimension-1,collumn2,new StartFinishPipe());
            this.currentTile = this.board[0][collumn1];
            this.finishPoint.setxCoord(dimension-1);
            this.finishPoint.setyCoord(collumn2);
        }

    }


    private void checkNeighbors(int dimensionX, int dimensionY, int dimension ){

        if (dimensionY-1 >= 0) {
            Tile leftNeighbor = this.board[dimensionX][dimensionY-1];
            if (checkvisitedNeighbor(leftNeighbor, this.visited)){
                this.neighbors.add(0,leftNeighbor);
            }
        }


        if (dimensionY+1 < dimension) {
            Tile rightNeighbor = this.board[dimensionX][dimensionY+1];
            if (checkvisitedNeighbor(rightNeighbor,this.visited)) {
                this.neighbors.add(0, rightNeighbor);
            }
        }

        if (dimensionX-1 >= 0) {
            Tile upNeighbor = this.board[dimensionX-1][dimensionY];
            if (checkvisitedNeighbor(upNeighbor,this.visited)) {
                this.neighbors.add(0, upNeighbor);
            }
        }

        if (dimensionX+1 < dimension) {
            Tile downNeighbor = this.board[dimensionX+1][dimensionY];
            if (checkvisitedNeighbor(downNeighbor,this.visited)) {
                this.neighbors.add(0, downNeighbor);
            }
        }

    }

    private void pickNeighbor(){

        if (!this.neighbors.isEmpty()) {
            Collections.shuffle(this.neighbors);
            this.visited.add(0, this.currentTile);
            this.currentTile = this.neighbors.get(0);
            neighbors.clear();
            if (currentTile == finishPoint){
                this.finish = true;
            }
        }
        else {
            this.visited.add(this.currentTile);
            this.stack.pop();
            this.currentTile = this.stack.pop();
        }

    }


    private void depthAlgorithm(Tile currentTile, int dimension){

        getTileIndex(currentTile,dimension);
        checkNeighbors(currentTile.getxCoord(), currentTile.getyCoord(), dimension);
        pickNeighbor();

    }

    private void getTileIndex(Tile square, int dimension){

        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){

                if (this.board[i][j] == square){
                    square.setxCoord(i);
                    square.setyCoord(j);
                    this.stack.push(square);
                    return;
                }
            }

        }
    }


    private Tile getNextTile(Tile current, int angleToCheck) {
        Tile nextTile = null;
        switch (angleToCheck) {
            case 0:
                try {
                    nextTile = this.board[current.getxCoord()][current.getyCoord()+1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                break;
            case 90:
                try {
                    nextTile = this.board[current.getxCoord()+1][current.getyCoord()];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                break;
            case 180:
                try {
                    nextTile = this.board[current.getxCoord()][current.getyCoord()-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                break;
            case 270:
                try {
                    nextTile = this.board[current.getxCoord()-1][current.getyCoord()];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                break;

        }
        return nextTile;
    }

    private int reverseAngle(int angle) {
        angle = angle + 180;
        if(angle >= 360) {
            angle = angle - 360;
        }
        return angle;
    }

    public void disableCheckBoard(int dimension) {
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                board[i][j].getPipe().setCheck(false);
            }
        }
    }

    public boolean checkWinState() {
        boolean won = false;
        Tile currentTile = startingPoint;
        int inAngle = startingPoint.getPipe().getAngle();
        while (!won) {
            currentTile.getPipe().setCheck(true);
            Tile nextTile = getNextTile(currentTile, inAngle);
            if (nextTile == null) {
                break;
            }
            inAngle = reverseAngle(inAngle);
            if (nextTile.getPipe() instanceof StartFinishPipe) {
                if (nextTile.getPipe().getAngle()== inAngle) {
                    won = true;
                }

                break;
            }

            if (nextTile.getPipe().getAngle() == inAngle) {
                inAngle = nextTile.getPipe().getEntryAngle();
                currentTile = nextTile;
            } else if (nextTile.getPipe().getEntryAngle() == inAngle) {
                inAngle = nextTile.getPipe().getAngle();
                currentTile = nextTile;
            } else {
                break;
            }
        }

        return won;
    }

}
