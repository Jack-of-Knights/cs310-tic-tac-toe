package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        boolean marked = false;
        if(isValidSquare(row, col) == true && isSquareMarked(row, col) == false) {
            if(xTurn == true) {
                board[row][col] = Mark.X;
                xTurn = false;
                marked = true;
                
            }
            else {
                board[row][col] = Mark.O;
                xTurn = true;
                marked = true;
            }
        }
        return marked;

        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        // INSERT YOUR CODE HERE
        if( (row>=0 && row<width) && (col>=0 && col<width) ) 
            return true;
        else 
            return false;
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        String mark = board[row][col].toString();
        boolean x = false;
        switch(mark) {
            case "X": case "O":
                x = true;
                break;
            case "-":
                x = false;
                break;

        }
        return x;
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];

            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE
        if(isMarkWin(Mark.X)) 
            return Result.X;
        else if(isMarkWin(Mark.O))
            return Result.O;
        else if(isTie()) 
            return Result.TIE;
        else
            return Result.NONE;

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        int count = width - 1;
        int markCountRow = 0;
        int markCountCol = 0;
        int markCountDiagonal1 = 0;
        int markCountDiagonal2 = 0;
        boolean winner = false;
        
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < width; ++j) {
               if(board[i][j] == mark)
                   markCountRow++;
               if(board[j][i] == mark)
                   markCountCol++;
            }
            if(board[i][i] == mark)
                markCountDiagonal1++;
            if(board[i][count] == mark)
                markCountDiagonal2++;
            
            if(markCountRow == width || markCountCol == width || markCountDiagonal1 == width || markCountDiagonal2 == width)
                winner = true;
            
            markCountRow = 0;
            markCountCol = 0;
            count -= 1;
            
        }
        return winner;

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE

        boolean tie = false;
        int count = 0;
        
        for(int row = 0; row < width; ++row) {
            for(int col = 0; col < width; ++col) {
                if(board[row][col].toString().equals("X") || board[row][col].toString().equals("O")) {
                    count += 1;
                }
            }
        }
        
        if(count == (width * width) && isMarkWin(Mark.X) == false && isMarkWin(Mark.O)  == false) {
            tie = true;
        }
        return tie;
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        for(int i = 0; i < width; i++) {
            output.append(i);
        } 
        output.append("\n\n");
        for(int i = 0; i < width; i++) {
            output.append(i).append(" ");
            for(int j = 0; j < width; j++) {
                Mark x = board[i][j];
                String a = x.toString();
                output.append(a);
            }
            if(i < width-1)
                output.append("\n");
        }       
        
        return output.toString();
        
    }
    
}


