package com.chechin;

public class Game {

    private boolean [][] board;
    private final int rowSize;
    private final int columnSize;

    public Game(boolean [][] board){
        this.rowSize = board.length;
        this.columnSize = board[0].length;
        this.board = board;
    }

    public boolean[][] getBoard() {
        return board;
    }

    private int countNeighbors(int i, int j) {
        int count = 0;
        int minRow = i == 0 ? 0 : i - 1;
        int maxRow = i == (rowSize - 1) ? (rowSize - 1) : i + 1;
        int minCol = j == 0 ? 0 : j - 1;
        int maxCol = j == (columnSize - 1) ? (columnSize - 1) : j + 1;

        for (int rowIndex = minRow; rowIndex <= maxRow; rowIndex++) {
            for (int columnIndex = minCol; columnIndex <= maxCol; columnIndex++) {
                if (board[rowIndex][columnIndex] && !(rowIndex == i && columnIndex == j)) {
                    count++;
                }
            }
        }

        return count;
    }

    public Game getNextGeneration(){
        boolean nextGeneration[][] = new boolean[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                nextGeneration[i][j] = nextCellState(i, j);
            }
        }
        board = nextGeneration;
        return new Game(nextGeneration);
    }

    private boolean nextCellState(int i, int j) {
        int neighborsCount = countNeighbors(i, j);
        boolean currentState = board[i][j];
        if(currentState && (neighborsCount == 2 || neighborsCount == 3)){
            return true;
        } else if (!currentState && neighborsCount == 3){
            return true;
        }
        return false;
    }
}
