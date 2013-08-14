/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class Rook extends Piece {
    
    public Rook(String color) {
        
        super(color);
        
    }

    @Override
    public int move(int from_x, int from_y, int to_x, int to_y) {
        
        if(horrizontal(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
        else if(vertical(pieces, from_x, from_y, to_x, to_y) == MOVED) {
            movement++;
            return MOVED;
        }
        
        
        return NOT_MOVED;
    }
    
    private int horrizontal(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        

        int y;
        
        if(from_x == to_x) {
            if(to_y > from_y) {
                for(y = from_y + 1; y < to_y; y++) {
                    if(!isEmpty(pieces[from_x][y])) {
                        return NOT_MOVED;
                    }
                }
                
                if(!isEmpty(pieces[from_x][y]) && !isOccupiedByAnOpponent(pieces[from_x][y])) {
                    return NOT_MOVED;
                }
                
                 if(isOccupiedByAnOpponent(pieces[from_x][y])) {
                    last_move_info = "Rx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                else {
                    last_move_info = "R" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                    
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;

                return MOVED;
            }
             else if(to_y < from_y) {
                for(y = from_y - 1; y > to_y; y--) {
                    if(!isEmpty(pieces[from_x][y])) {
                        return NOT_MOVED;
                    }
                }
                
                if(!isEmpty(pieces[from_x][y]) && !isOccupiedByAnOpponent(pieces[from_x][y])) {
                    return NOT_MOVED;
                }
                
                if(isOccupiedByAnOpponent(pieces[from_x][y])) {
                    last_move_info = "Rx"  + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                else {
                    last_move_info = "R" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }

                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;

                return MOVED;

             }
        }
            
        return NOT_MOVED;
        
    }
    
    private int vertical(Piece[][] pieces, int from_x, int from_y, int to_x, int to_y) {
        
        int x;
        
        if(from_y == to_y) {
            if(to_x > from_x) {
                for(x = from_x + 1; x < to_x; x++) {
                    if(!isEmpty(pieces[x][from_y])) {
                        return NOT_MOVED;
                    }
                }
                    
                if(!isEmpty(pieces[x][from_y]) && !isOccupiedByAnOpponent(pieces[x][from_y])) {
                    return NOT_MOVED;
                }
                
                if(isOccupiedByAnOpponent(pieces[x][from_y])) {
                    last_move_info = "Rx"  + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                else {
                    last_move_info = "R" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                    
                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;

                return MOVED;
            }
            else if(to_x < from_x) {
                for(x = from_x - 1; x > to_x; x--) {
                    if(!isEmpty(pieces[x][from_y])) {
                        return NOT_MOVED;
                    }
                }
                    
                if(!isEmpty(pieces[x][from_y]) && !isOccupiedByAnOpponent(pieces[x][from_y])) {
                    return NOT_MOVED;
                }
                
                if(isOccupiedByAnOpponent(pieces[x][from_y])) {
                    last_move_info = "Rx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }
                else {
                    last_move_info = "R" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
                }

                pieces[to_x][to_y] = pieces[from_x][from_y];
                pieces[from_x][from_y] = null;

                return MOVED;
            }
        }
        
        return NOT_MOVED;
        
    }


    @Override
    public String getName() {
        return "rook";
    }
    
    @Override
    public void promote(int from_x, int from_y, int to_x, int to_y) {
        
        if(isOccupiedByAnOpponent(pieces[to_x][to_y])) {
            last_move_info = (char)(((int)'a') + from_y) + "x" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + "R ";
            //last_move_info = "Rx" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
        else {
            last_move_info = (char)(((int)'a') + to_y) + "" + (8 - to_x) + "R ";
            //last_move_info = "R" + (char)(((int)'a') + to_y) + "" + (8 - to_x) + " ";
        }
    
        pieces[to_x][to_y] = pieces[from_x][from_y];
        pieces[from_x][from_y] = null;
                
    }
    
}
