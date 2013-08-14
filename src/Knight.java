/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class Knight extends Piece {
    
    public Knight(String color) {
        
        super(color);
        
    }

    @Override
    public int move(int from_x, int from_y, int to_x, int to_y) {
        
        if(l_shape(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
 
        return NOT_MOVED;
    }
    
    private int l_shape(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        if(Math.abs(to_x - from_x) == 2 && Math.abs(to_y - from_y) == 1 && (isOccupiedByAnOpponent(pieces[to_x][to_y]) || isEmpty(pieces[to_x][to_y]))) {
            if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
                last_move_info = "Nx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            else {
                last_move_info = "N" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            
            pieces[to_x][to_y] = pieces[from_x][from_y];
            pieces[from_x][from_y] = null;
     
            return MOVED;
        }
        else if(Math.abs(to_y - from_y) == 2 && Math.abs(to_x - from_x) == 1 && (isOccupiedByAnOpponent(pieces[to_x][to_y]) || isEmpty(pieces[to_x][to_y]))) {
            if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
                last_move_info = "Nx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            else {
                last_move_info = "N" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
            }
            
            pieces[to_x][to_y] = pieces[from_x][from_y];
            pieces[from_x][from_y] = null;
 
            return MOVED;
        }
        
        return NOT_MOVED;
    }
    
    @Override
    public String getName() {
        return "knight";
    }
    
    @Override
    public void promote(int from_x, int from_y, int to_x, int to_y) {
        
        if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
            last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + "N ";
            //last_move_info = "Nx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
        else {
            last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + "N ";
            //last_move_info = "N" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
    
        pieces[to_x][to_y] = pieces[from_x][from_y];
        pieces[from_x][from_y] = null;
        
    }
    
}
