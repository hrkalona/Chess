

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public abstract class Piece {
  public static int MOVED = 0;
  public static int NOT_MOVED = 1;
  public static int PROMOTION = 2;
  protected static Piece[][] pieces;
  protected String color;
  protected int movement;
  protected boolean special;
  protected static String last_move_info;


  
    public Piece(String color) {
        
        this.color = color;
        movement = 0;
        special = false;
        
    }
    
    public abstract int move(int from_x, int from_y, int to_x, int to_y);
    
    public abstract void promote(int from_x, int from_y, int to_x, int to_y);
    
    public abstract String getName();
    
    
    public String getColor() {
        
        return color;
        
    }
    
    public int getMovement() {
        
        return movement;
        
    }
    
    public boolean getSpecialStartingMovement() {
        
        return special;
        
    }
    
    public boolean isEmpty(Piece cell) {
        
        if(cell == null) {
            return true;
        }
        
        return false;
        
    }
    
    public boolean isOccupiedByAnOpponent(Piece cell) {
        
        if(!isEmpty(cell) && (color.equals("white") && cell.getColor().equals("black") || color.equals("black") && cell.getColor().equals("white"))) {
            return true;
        }
        
        return false;
        
    }
    
    public static void InitBoard() {
        
        
        pieces = new Piece[8][8];
        
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[0].length; j++) {
                pieces[i][j] = null;
            }
         }

       
        pieces[6][1] = new Pawn("white");
        pieces[6][3] = new Pawn("white");
        pieces[6][5] = new Pawn("white");
        pieces[6][7] = new Pawn("white");
        pieces[6][0] = new Pawn("white");
        pieces[6][2] = new Pawn("white");
        pieces[6][4] = new Pawn("white");
        pieces[6][6] = new Pawn("white");
        pieces[7][1] = new Knight("white");
        pieces[7][6] = new Knight("white");
        pieces[7][7] = new Rook("white");
        pieces[7][0] = new Rook("white");
        pieces[7][5] = new Bishop("white");        
        pieces[7][2] = new Bishop("white");        
        pieces[7][3] = new Queen("white");       
        pieces[7][4] = new King("white");
        
        
        
        //Black
       
        pieces[1][1] = new Pawn("black");        
        pieces[1][3] = new Pawn("black");
        pieces[1][5] = new Pawn("black");
        pieces[1][7] = new Pawn("black");
        pieces[1][0] = new Pawn("black");
        pieces[1][2] = new Pawn("black");
        pieces[1][4] = new Pawn("black");
        pieces[1][6] = new Pawn("black");
        pieces[0][6] = new Knight("black");       
        pieces[0][1] = new Knight("black");       
        pieces[0][0] = new Rook("black");        
        pieces[0][7] = new Rook("black");       
        pieces[0][2] = new Bishop("black");        
        pieces[0][5] = new Bishop("black");        
        pieces[0][3] = new Queen("black");
        pieces[0][4] = new King("black");
        
        
    }
 
  
}
