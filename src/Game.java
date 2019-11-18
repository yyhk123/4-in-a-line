

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Board board;
	private boolean gameFinished = false;
	private int minimaxDepth = 6; // set minimax depth.
	private Minimax ai;
	private int winner; 
	private boolean isOpponentFirst = true;
	
	List<String> list = new ArrayList<>();

	
	public Game(Board board) {
		this.board = board;
		ai = new Minimax(board);
		
	}
	
	public void displayMove() {
		System.out.println();
		if(getIsOpponentFirst())
			System.out.println("Opponent vs. Agent");
		else
			System.out.println("Agent vs. Opponent");
		for(int i=1; i<= list.size(); i++ ) {
			System.out.print(list.get(i-1) + "\t     ");
			if(i%2 == 0)
				System.out.println();
		}
	}
	
	public void start() {
		
		Scanner scan = new Scanner(System.in);
		
		char ans;
		System.out.println("Who goes first, A for agent, O for opponent:");
		ans = scan.next().toUpperCase().charAt(0);
		while(ans!='A' && ans != 'O') {
			System.out.println("Wrong Input. Who goes first, A for agent, O for opponent:");
			ans = scan.next().toUpperCase().charAt(0);
		}
		
		if(ans == 'A') { // if AI play first
			isOpponentFirst = false;
			makeMove(board.getBoardSize()/2 - 1, board.getBoardSize()/2 - 1, false);
			board.displayBoard();
			list.add("D4");
			displayMove();
			System.out.println();
		}
		
		while(!gameFinished) {
			System.out.println("Choose Oponent's Next Move");
			String userMove = scan.next().toUpperCase();
			char fmtemp = userMove.charAt(0);
	        char smtemp = userMove.charAt(1);
	        int fmove = (int)fmtemp;
	        int smove = Integer.parseInt(String.valueOf(smtemp));
	        
	        // check for valid movement
	        while(( (smove<1 && smove > 8)  || (fmove<65  && fmove >72))  || !board.isLegalMove(fmove-65, smove-1)){
	            System.out.println("Invalid move.\n\nPlace your move again: "); 
	            userMove = scan.next().toUpperCase();
	            fmtemp = userMove.charAt(0);
	            smtemp = userMove.charAt(1);
	            
	            fmove = (int)fmtemp;
	            smove = Integer.parseInt(String.valueOf(smtemp));
	        }
	        
	        list.add(userMove); //******************************
	        
	        makeMove(smove-1, fmove-65, true);
	        board.displayBoard();
	        System.out.println("Opponent's Move is: "+fmtemp+smtemp);
	        System.out.println();
	        
	        displayMove(); //******************************
	        
	        winner = checkWinner();
	        
	        if(winner == 2) {
	        	System.out.println();
	        	System.out.println("Opponent WON!");
	        	gameFinished = true;
	        }
	        
	        if(gameFinished == false) { // AI's turn if game still not finished
	        	int[] aiMove = ai.getNextMove(minimaxDepth);
	        	if(aiMove == null) {
	        		System.out.println("No Possible moves Left. Game over");
	        		gameFinished = true;
	        	}
	        	else {
	        		makeMove(aiMove[1], aiMove[0], false);
	        		board.displayBoard();
	        		
	        		char fm = (char)aiMove[0];
	        		fm += 65;
	        		System.out.println("Agent's Move is: " + fm + (aiMove[1]+1));
	        		
	        		String tmp = Integer.toString(aiMove[1]+1);//******************************
	        		String temp = fm + tmp;//******************************
	        		list.add(temp);//******************************
	        		displayMove(); //******************************
	        		
	        		
	        		System.out.println();
	        		winner = checkWinner();
	        		
	        		if(winner == 1) {
	        			System.out.println();
	        			System.out.println("Agent WON!");
	        			gameFinished = true;
	        		}
	        		
	        		if(board.generateMoves().size() == 0) {
	        			System.out.println("No Possible moves Left. Game Over");
	        			gameFinished = true;
	        		}
	        	}
	        	
	        	
	        }
	        
	        
		}
		
	}
	
	public boolean getIsOpponentFirst() {//******************************
		return isOpponentFirst;
	}
	
	private boolean makeMove(int posX, int posY, boolean black) {
		return board.addMove(posX, posY, black);
	}
	
	private int checkWinner() {
		if(Minimax.getScore(board, true, false) >= Minimax.getWinningScore()) 
			return 2;
		if(Minimax.getScore(board, false, true) >= Minimax.getWinningScore()) 
			return 1;
		return 0;
	}
	
	
}
