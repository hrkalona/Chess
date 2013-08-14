/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class Pawn extends Piece {
    
    public Pawn(String color) {
        
        super(color);
        
    }

    @Override
    public int move(int from_x, int from_y, int to_x, int to_y) {
  
        int res;
        
        if((res = nextRowSameColumn(pieces, from_x, from_y, to_x, to_y)) == MOVED || res == PROMOTION) {
            movement++;
            return res;
        }
        else if ((res =nextRowDiagonalColumn(pieces, from_x, from_y, to_x, to_y)) == MOVED || res == PROMOTION) {
            movement++;
            return res;
        }
        else if(secondNextRowSameColumn(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            special = true;
            return MOVED;
        }
        else if(enPassant(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
        
        return NOT_MOVED;
        
    }
    
    @Override
    public String getName() {
        
        return "pawn";
        
    }
    
    private int nextRowSameColumn(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if(color.equals("black")) {
            if(to_x - from_x == 1 && to_y == from_y && isEmpty(pieces[to_x][to_y])) {
                
                if(to_x == 7) {
                    return PROMOTION;
                }
                
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
                
                last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                
                return MOVED;
            }
        }
        else {
            if(to_x - from_x == -1 && to_y == from_y && isEmpty(pieces[to_x][to_y])) {
                
                if(to_x == 0) {
                    return PROMOTION;
                }
                
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
   
                last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
 
                return MOVED;
            }           
        }

        return NOT_MOVED;
        
    }
    
    
    private int secondNextRowSameColumn(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if(color.equals("black")) {
            if(movement == 0 && to_x - from_x == 2 && to_y == from_y  && isEmpty(pieces[from_x + 1][to_y]) && isEmpty(pieces[to_x][to_y])) {
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
                last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                return MOVED;
            }
        }
        else {
            if(movement == 0 && to_x - from_x == -2 && to_y == from_y && isEmpty(pieces[from_x - 1][to_y]) && isEmpty(pieces[to_x][to_y])) {
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
                last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                return MOVED;
            }           
        }

        return NOT_MOVED;
        
    }
    
    
     private int nextRowDiagonalColumn(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if(color.equals("black")) {
            if(to_x - from_x == 1 && Math.abs(to_y - from_y) == 1 && isOccupiedByAnOpponent(pieces[to_x][to_y])) {
                
                if(to_x == 7) {
                    return PROMOTION;
                }
                
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
                
                last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";

                return MOVED;
            }
        }
        else {
            if(to_x - from_x == -1 && Math.abs(to_y - from_y) == 1 && isOccupiedByAnOpponent(pieces[to_x][to_y])) {
                
                if(to_x == 0) {
                    return PROMOTION;
                }
                
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;
          
                last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";

                return MOVED;
            }           
        }

        return NOT_MOVED;
        
    }
     
     private int enPassant(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {

         if(color.equals("black")) {
             if(to_x - from_x == 1 && from_y - 1 > 0 && isOccupiedByAnOpponent(pieces[from_x][from_y - 1]) && isPawn(pieces[from_x][from_y - 1]) && pieces[from_x][from_y - 1].getMovement() == 1 && pieces[from_x][from_y - 1].getSpecialStartingMovement() && to_y == from_y - 1) {
                 pieces[to_x][to_y] = pieces[from_x][from_y];
                 pieces[from_x][from_y] = null;
                 pieces[from_x][from_y - 1] = null;
                 last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                 return MOVED;
             }
             else if(to_x - from_x == 1 && from_y + 1 < 8 && isOccupiedByAnOpponent(pieces[from_x][from_y + 1]) && isPawn(pieces[from_x][from_y + 1]) && pieces[from_x][from_y + 1].getMovement() == 1 && pieces[from_x][from_y + 1].getSpecialStartingMovement() && to_y == from_y + 1) {
                 pieces[to_x][to_y] = pieces[from_x][from_y];
                 pieces[from_x][from_y] = null;
                 pieces[from_x][from_y + 1] = null;
                 last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                 return MOVED;
             }
         }
         else {
             if(to_x - from_x == -1 && from_y - 1 > 0 && isOccupiedByAnOpponent(pieces[from_x][from_y - 1]) && isPawn(pieces[from_x][from_y - 1]) && pieces[from_x][from_y - 1].getMovement() == 1 && pieces[from_x][from_y - 1].getSpecialStartingMovement() && to_y == from_y - 1) {
                 pieces[to_x][to_y] = pieces[from_x][from_y];
                 pieces[from_x][from_y] = null;
                 pieces[from_x][from_y - 1] = null;
                 last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                 return MOVED;
             }
             else if(to_x - from_x == -1 && from_y + 1 < 8 && isOccupiedByAnOpponent(pieces[from_x][from_y + 1]) && isPawn(pieces[from_x][from_y + 1]) && pieces[from_x][from_y + 1].getMovement() == 1 && pieces[from_x][from_y + 1].getSpecialStartingMovement() && to_y == from_y + 1) {
                 pieces[to_x][to_y] = pieces[from_x][from_y];
                 pieces[from_x][from_y] = null;
                 pieces[from_x][from_y + 1] = null; 
                 last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                 return MOVED;
             }
         }

         return NOT_MOVED;

     }
     
     
     private boolean isPawn(Piece cell) {
         
         if(cell.getName().equals("pawn")) {
             return true;
         }
         
         return false;
         
     }

    @Override
    public void promote(int from_x, int from_y, int to_x, int to_y) {}
 
}
