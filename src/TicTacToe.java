import java.util.*;

public class TicTacToe {
	
	public static final int SIZE = 3;
	public static final char[] X_ROW = {'X','X','X'};
	public static final char[] O_ROW = {'O','O','O'};
    
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);     
        char[][] board = new char[SIZE][SIZE];
        int[] cell = new int[2];
        boolean winner = false;
        char currentChar = 'X';
        int nrOfPossibleMoves = 9;
        board = buildBoard(board, scanner);
       
        while(!winner && nrOfPossibleMoves != 0) {
        	currentChar = startBoardGame(currentChar, board, cell, scanner);
        	winner = checkForWinner(board);
        	nrOfPossibleMoves--;
        }
        
        if(nrOfPossibleMoves == 0 && !winner){
			System.out.println("Draw");
		}
        
        scanner.close();                
    }
    
	private static char[][] buildBoard(char[][] board, Scanner scanner) {
		// Fill 2d-array with whitespace
        for (int i = 0; i < SIZE; i++) { 
            for (int j = 0; j < SIZE; j++){
               board[i][j] = ' ';
            }
        }
        printBoard(board, SIZE);
        return board;
	}
	
	public static void printBoard(char[][] board, int m) {
        System.out.println("---------");
        for (int i = 0; i < m; i++) {
            System.out.print("| "); 
            System.out.print(board[i][0] + " " + board[i][1]  + " " + board[i][2]);
            System.out.println(" |");
        }
        System.out.println("---------");
    }
	
	private static char startBoardGame(char currentChar, char[][] board, int[] cell, Scanner scanner) {
		System.out.print("Enter the coordinates: ");
		boolean valid = false;
		while(!valid) {
			cell = getCellCoordinates(cell, scanner);
			if(checkIfValidCoordinates(board, cell)) {
				valid = true;
			}
		}
	    board = addCellCoordinatesToBoard(board, cell, currentChar);
	    
	    return currentChar == 'O' ? 'X' : 'O';
	}
		
	 private static int[] getCellCoordinates(int[] cord, Scanner scanner) {
	    	int row = 0;
	    	int coll = 0;
	    	boolean done = false;
	    	while(!done) {	    		
	    		try{
	    			row = scanner.nextInt();
	    			coll = scanner.nextInt();
	    			if (row < 1 || row > 3 || coll < 1 || coll > 3)
		            {
		                System.out.println("Coordinates should be from 1 to 3!");
		                System.out.print("Enter the coordinates: "); 
		                scanner.nextLine();
		            } else {
		            	cord[0] = row;
		            	cord[1] = coll;
		            	done = true;
		            }	    			
	            } catch(InputMismatchException exception) {	            		            	
	            	System.out.println("You should enter numbers!");
	            	System.out.print("Enter the coordinates: ");
	            	scanner.nextLine();
	            }	    		
	    	}
	    	
			return cord;
		}
	 
	 private static boolean checkIfValidCoordinates(char[][] board, int[] cell) {
		 	boolean valid = false;
		 	
		 	if(board[Math.abs(cell[1] - 3)][cell[0] - 1] == ' ') {
		 		valid = true;
		 	} else {
		 		System.out.println("This cell is occupied! Choose another one!");
		 	}
			return valid;
		}

	private static char[][] addCellCoordinatesToBoard(char[][] board, int[] cell, char currentChar) {
			board[Math.abs(cell[1] - 3)][cell[0] - 1] = currentChar;
			printBoard(board, SIZE);
			return board;
	} 
	 
    public static boolean checkForWinner(char[][] board) {
    	char[] rowArr = new char[SIZE];
        char[] colArr = new char[SIZE];      
        boolean xGotThree = false;
        boolean oGotThree = false;
        boolean done = false;
		
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++){
                rowArr[j] = board[i][j];
                colArr[j] = board[j][i];
            }     
            // Check if there are three in a row or column
            if(Arrays.equals(X_ROW, rowArr) || Arrays.equals(X_ROW, colArr)) {                	
            	xGotThree = true;
           	} else if(Arrays.equals(O_ROW, rowArr) || Arrays.equals(O_ROW, colArr)) { 
           		oGotThree = true;
           	}                        
        }
        // If no winner check for diagonal win 
        if(!xGotThree && !oGotThree) {
        	if(checkForDiagonalAscendingWin(board) == 'X' || 
        			checkForDiagonalDescentWin(board) == 'X') {
        		xGotThree = true;
        	} else if(checkForDiagonalAscendingWin(board) == 'O' || 
        			checkForDiagonalDescentWin(board) == 'O') {
        		oGotThree = true;
        	}
        }
        
        
        if(xGotThree) {
        	System.out.println("X wins"); 
        	done = true;
        } else if(oGotThree) {
        	System.out.println("O wins"); 
        	done = true;
       } 		
		return done;
    }
    	
    public static char checkForDiagonalAscendingWin(char[][] board) {
    	char winner = ' ';
    	
    	if(board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X') {
    		winner = 'X'; 
    	} else if(board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O') {
    		winner = 'O'; 
    	}
    	return winner;
    }
    
    public static char checkForDiagonalDescentWin(char[][] board) {
    	char winner = ' ';
    	
    	if(board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
    		winner = 'X';
    	} else if(board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
    		winner = 'O'; 
    	}
    	return winner;
    }    
}
