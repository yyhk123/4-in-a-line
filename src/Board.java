

import java.util.*;

public class Board {
	
	int n = 8;
	private char[][] boardMatrix; 
	Game game;
	
	public Board() {
		this.boardMatrix = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.boardMatrix[i][j] = '-';
			}
		}
	}
	
	// copies boardMatrix
	public Board(Board board) {
		char[][] matrixToCopy = board.getBoardMatrix();
		boardMatrix = new char[matrixToCopy.length][matrixToCopy.length];
		for(int i=0;i<matrixToCopy.length; i++) {
			for(int j=0; j<matrixToCopy.length; j++) {
				boardMatrix[i][j] = matrixToCopy[i][j];
			}
		}
	}
	public boolean addMove(int posX, int posY, boolean black) {
		
		// Check for empty cell
		if(boardMatrix[posY][posX] != '-') 
			return false;
		
		boardMatrix[posY][posX] = black ? 'O' : 'X';
		return true;
	}
	public char[][] getBoardMatrix() {
		return boardMatrix;
	}
	public boolean isLegalMove(int row, int col){
        return boardMatrix[row][col]=='-';
    }
	
	public void displayBoard(){
        System.out.println();
        int numY =65;
        System.out.print(" ");
        for(int i=1; i<=n; i++) {
        	System.out.print(" " + i);
        }
        System.out.println();
        for(int i=0;i<n;++i){
        	System.out.print((char)numY + " ");
            for(int j=0;j<n;++j){
                System.out.print(boardMatrix[i][j]+" ");
            }
            System.out.println();
            numY++;
        }
    }
	
	public int getBoardSize() {
		return n;
	}
	
	public ArrayList<int[]> generateMoves() {
		ArrayList<int[]> moveList = new ArrayList<int[]>();
		
		int boardSize = boardMatrix.length;
		
		// Look for any adjcent cell
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				if(boardMatrix[i][j] != '-') 
					continue;
				
				if(i > 0) {
					if(j > 0) {
						if(boardMatrix[i-1][j-1] != '-' ||
						   boardMatrix[i][j-1] != '-' ) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(j < boardSize-1) {
						if(boardMatrix[i-1][j+1] != '-'  ||
						   boardMatrix[i][j+1] != '-' ) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(boardMatrix[i-1][j] != '-' ) {
						int[] move = {i,j};
						moveList.add(move);
						continue;
					}
				}
				
				if( i < boardSize-1) {
					if(j > 0) {
						if(boardMatrix[i+1][j-1] != '-'  ||
						   boardMatrix[i][j-1] != '-' ) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(j < boardSize-1) {
						if(boardMatrix[i+1][j+1] != '-'  ||
						   boardMatrix[i][j+1] != '-' ) {
							int[] move = {i,j};
							moveList.add(move);
							continue;
						}
					}
					if(boardMatrix[i+1][j] != '-' ) {
						int[] move = {i,j};
						moveList.add(move);
						continue;
					}
				}
				
			}
		}
		return moveList;
	}
	

}
