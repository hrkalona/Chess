/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class Bishop extends Piece {
    
    public Bishop(String color) {
        
        super(color);
        
    }

    @Override
    public int move(int from_x, int from_y, int to_x, int to_y) {
        
        if(diagonal(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
 
        return NOT_MOVED;
    }
    
    private int diagonal(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        int x,y;
        
        if(Math.abs(to_x - from_x) == Math.abs(to_y - from_y)) {
            
            if(to_x > from_x) {
                if(to_y > from_y) {
                    for(x = from_x + 1, y = from_y + 1; x < to_x; x++, y++) {
                        if(!isEmpty(pieces[x][y])) {
                            return NOT_MOVED;
                        }
                    }
                    
                    if(!isEmpty(pieces[x][y]) && !isOccupiedByAnOpponent(pieces[x][y])) {
                        return NOT_MOVED;
                    }
                    
                    if(isOccupiedByAnOpponent(pieces[x][y])) {
                        last_move_info = "Bx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    else {
                        last_move_info = "B" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    
                    pieces[to_x][to_y] = pieces[from_x][from_y];
                    pieces[from_x][from_y] = null;
                    return MOVED;
                }
                else if(to_y < from_y) {
                    for(x = from_x + 1, y = from_y - 1; x < to_x; x++, y--) {
                        if(!isEmpty(pieces[x][y])) {
                            return NOT_MOVED;
                        }
                    }
                    
                    if(!isEmpty(pieces[x][y]) && !isOccupiedByAnOpponent(pieces[x][y])) {
                        return NOT_MOVED;
                    }
                    
                    if(isOccupiedByAnOpponent(pieces[x][y])) {
                        last_move_info = "Bx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    else {
                        last_move_info = "B" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    
                    pieces[to_x][to_y] = pieces[from_x][from_y];
                    pieces[from_x][from_y] = null;
                    return MOVED;
                }
            }
            else if(to_x < from_x) {
                if(to_y > from_y) {
                    for(x = from_x - 1, y = from_y + 1; x > to_x; x--, y++) {
                        if(!isEmpty(pieces[x][y])) {
                            return NOT_MOVED;
                        }
                    }
                    
                    if(!isEmpty(pieces[x][y]) && !isOccupiedByAnOpponent(pieces[x][y])) {
                        return NOT_MOVED;
                    }
                    
                    if(isOccupiedByAnOpponent(pieces[x][y])) {
                        last_move_info = "Bx"  + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    else {
                        last_move_info = "B" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    
                    pieces[to_x][to_y] = pieces[from_x][from_y];
                    pieces[from_x][from_y] = null;
                    return MOVED;
                }
                else if(to_y < from_y) {
                    for(x = from_x - 1, y = from_y - 1; x > to_x; x--, y--) {
                        if(!isEmpty(pieces[x][y])) {
                            return NOT_MOVED;
                        }
                    }
                    
                    if(!isEmpty(pieces[x][y]) && !isOccupiedByAnOpponent(pieces[x][y])) {
                        return NOT_MOVED;
                    }
                    
                    if(isOccupiedByAnOpponent(pieces[x][y])) {
                        last_move_info = "Bx"  + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    else {
                        last_move_info = "B" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                    }
                    
                    pieces[to_x][to_y] = pieces[from_x][from_y];
                    pieces[from_x][from_y] = null;
                    return MOVED;
                }
            }
            
            
        }
        
        return NOT_MOVED;
        
    }
    
    @Override
    public String getName() {
        return "bishop";
    }
    
    @Override
    public void promote(int from_x, int from_y, int to_x, int to_y) {
    
        if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
            last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + "B ";
            //last_move_info = "Bx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
        else {
            last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + "B ";
            //last_move_info = "B" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
    
        pieces[to_x][to_y] = pieces[from_x][from_y];
        pieces[from_x][from_y] = null;
        
    }
    
}
