/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class King extends Piece {
    
    public King(String color) {
        
        super(color);
        
    }

    @Override
    public int move(int from_x, int from_y, int to_x, int to_y) {
        
        if(square(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
        if(castling(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
 
        return NOT_MOVED;
    }
    
    
    private int square(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if((to_x == from_x && Math.abs(to_y - from_y) == 1 || to_y == from_y && Math.abs(to_x - from_x) == 1 || Math.abs(to_x - from_x) == 1 && Math.abs(to_y - from_y) == 1) && (isOccupiedByAnOpponent(pieces[to_x][to_y]) || isEmpty(pieces[to_x][to_y]))) {
            if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
                last_move_info = "Kx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            else {
                last_move_info = "K" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            
            pieces[to_x][to_y] = pieces[from_x][from_y];
            pieces[from_x][from_y] = null;

            return MOVED;
        }
        
        return NOT_MOVED;
        
    }
    
    private int castling(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if(movement == 0 && to_y - from_y == 2 && isEmpty(pieces[from_x][from_y + 1]) && isEmpty(pieces[from_x][from_y + 2]) && pieces[from_x][7].getName().equals("rook") && !isOccupiedByAnOpponent(pieces[from_x][7]) && pieces[from_x][7].getMovement() == 0) {
            last_move_info = "0-0" + " ";
            
            pieces[to_x][to_y] = pieces[from_x][from_y];
            pieces[from_x][from_y] = null;
            
            pieces[to_x][to_y - 1] = pieces[from_x][7];
            pieces[from_x][7] = null;

            return MOVED;
        }
        else if(movement == 0 && to_y - from_y == -2 && isEmpty(pieces[from_x][from_y - 1]) && isEmpty(pieces[from_x][from_y - 2]) && isEmpty(pieces[from_x][from_y - 3]) && pieces[from_x][0].getName().equals("rook") && !isOccupiedByAnOpponent(pieces[from_x][0]) && pieces[from_x][0].getMovement() == 0) {
            last_move_info = "0-0-0" + " ";
            
            pieces[to_x][to_y] = pieces[from_x][from_y];
            pieces[from_x][from_y] = null;
            
            pieces[to_x][to_y + 1] = pieces[from_x][0];
            pieces[from_x][0] = null;

            return MOVED;
        }
        
        return NOT_MOVED;
    }
    
    @Override
    public String getName() {
        return "king";
    }
    
    @Override
    public void promote(int from_x, int from_y, int to_x, int to_y) {}
    
}
