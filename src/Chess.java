
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hrkalona2
 */
public class Chess extends JFrame {

    private JFrame promote_frame;
    private JPanel panel;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel[] row;
    private JLabel[] column;
    private JLabel[][] board;
    private int i, j;
    private boolean piece_selected;
    private int x[];
    private int y[];
    private boolean turn;
    private JLabel players_turn;
    private JLabel white_clock;
    private JLabel black_clock;
    private Clock white;
    private Clock black;
    private JTextArea textarea;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem start;
    private JMenuItem stop;
    private int play_counter;
    private String play;

    public Chess() throws UnsupportedLookAndFeelException {

        super();
        setSize(550, 450);
        setTitle("Chess");

        piece_selected = false;

        board = new JLabel[8][8];

        play_counter = 0;
        play = "" + (play_counter + 1) + ". ";

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException ex) {
        }
        catch(InstantiationException ex) {
        }
        catch(IllegalAccessException ex) {
        }
        catch(UnsupportedLookAndFeelException ex) {
        }

        setLayout(new FlowLayout());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Get the current screen size
        Dimension scrnsize = toolkit.getScreenSize();

        if(scrnsize.getHeight() > getHeight()) {
            setLocation((int)((scrnsize.getWidth() / 2) - (getWidth() / 2)), (int)((scrnsize.getHeight() / 2) - (getHeight() / 2)) - 23);
        }
        else {
            setLocation((int)((scrnsize.getWidth() / 2) - (getWidth() / 2)), 0);
        }

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));
        row = new JLabel[8];
        column = new JLabel[8];

        x = new int[2];
        y = new int[2];

        turn = false;

        panel.add(new JLabel(""));

        for(int i = 0; i < column.length; i++) {
            column[i] = new JLabel("" + (char)(((int)'a') + i), SwingConstants.CENTER);
            column[i].setPreferredSize(new Dimension(26, 26));
            column[i].setOpaque(true);
            column[i].setFont(new Font("bold", Font.BOLD, 13));
            column[i].setForeground(Color.black);
            panel.add(column[i]);
        }

        panel.add(new JLabel(""));

        int counter = 0;
        for(i = 0; i < board.length; i++) {
            row[i] = new JLabel("" + (board.length - i), SwingConstants.HORIZONTAL);
            row[i].setPreferredSize(new Dimension(26, 26));
            row[i].setFont(new Font("bold", Font.BOLD, 13));
            row[i].setForeground(Color.black);
            row[i].setOpaque(true);
            panel.add(row[i]);
            for(j = 0; j < board[0].length; j++) {
                board[i][j] = new JLabel("");
                board[i][j].setPreferredSize(new Dimension(26, 26));
                board[i][j].setOpaque(true);
                if(counter % 2 == 0) {
                    board[i][j].setBackground(new Color(255, 206, 158));

                }
                else {
                    board[i][j].setBackground(new Color(209, 139, 71));
                }
                panel.add(board[i][j]);

                counter++;
            }
            row[i] = new JLabel("" + (board.length - i), SwingConstants.HORIZONTAL);
            row[i].setPreferredSize(new Dimension(26, 26));
            row[i].setFont(new Font("bold", Font.BOLD, 13));
            row[i].setForeground(Color.black);
            row[i].setOpaque(true);
            panel.add(row[i]);
            counter++;
        }

        panel.add(new JLabel(""));

        for(int i = 0; i < column.length; i++) {
            column[i] = new JLabel("" + (char)(((int)'a') + i), SwingConstants.CENTER);
            column[i].setPreferredSize(new Dimension(26, 26));
            column[i].setOpaque(true);
            column[i].setFont(new Font("bold", Font.BOLD, 13));
            column[i].setForeground(Color.black);
            panel.add(column[i]);
        }

        panel.add(new JLabel(""));

        panel2.add(panel);

        textarea = new JTextArea(12, 11);
        textarea.setFont(new Font("bold", Font.BOLD, 13));
        textarea.setForeground(Color.black);
        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        JScrollPane textareapane = new JScrollPane(textarea);
        textareapane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel2.add(textareapane);

        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()), "Chess", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION));

        add(panel2);

        players_turn = new JLabel("White's turn");
       // add(players_turn);

        JLabel label1 = new JLabel("White's Clock: ");
        label1.setFont(new Font("bold", Font.BOLD, 13));
        label1.setForeground(Color.black);
        panel3.add(label1);

        white_clock = new JLabel();
        white_clock.setFont(new Font("bold", Font.BOLD, 13));
        white_clock.setForeground(Color.black);
        white_clock.setPreferredSize(new Dimension(50, 20));
        panel3.add(white_clock);

        JLabel label2 = new JLabel("Black's Clock: ");
        label2.setFont(new Font("bold", Font.BOLD, 13));
        label2.setForeground(Color.black);
        panel3.add(label2);

        black_clock = new JLabel();
        black_clock.setPreferredSize(new Dimension(50, 20));
        black_clock.setFont(new Font("bold", Font.BOLD, 13));
        black_clock.setForeground(Color.black);
        panel3.add(black_clock);

        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()), "Timers", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION));

        add(panel3);

        white = new Clock(white_clock);
        black = new Clock(black_clock);

        start = new JMenuItem("Start");

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                start();

            }

        });

        stop = new JMenuItem("End");

        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                end();

            }

        });

        stop.setEnabled(false);

        menu = new JMenu("File");

        menu.add(start);
        menu.add(stop);

        menubar = new JMenuBar();

        menubar.add(menu);

        setJMenuBar(menubar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }
        });

    }

    private String getIcon(Piece piece, int k, int l) {

        String temp = "/Icons/";

        temp += piece.getName() + "_" + piece.getColor() + "_";

        if(board[k][l].getBackground().getRGB() == new Color(255, 206, 158).getRGB()) {
            temp += "light";
        }
        else {
            temp += "dark";
        }

        temp += ".png";

        return temp;

    }

    public void pawn_promotion() {

        setEnabled(false);
        int promote_frame_window_width = 220;
        int promote_frame_window_height = 100;
        promote_frame = new JFrame("Pawn Promotion");
        promote_frame.setSize(promote_frame_window_width, promote_frame_window_height);

        promote_frame.setLocation((int)(getLocation().getX() + getSize().getWidth() / 2) - (promote_frame_window_width / 2), (int)(getLocation().getY() + getSize().getHeight() / 2) - (promote_frame_window_height / 2));
        promote_frame.setResizable(false);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 7));
        promote_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        promote_frame.setLayout(new FlowLayout());

        JLabel rook = new JLabel();
        rook.setPreferredSize(new Dimension(26, 26));
        rook.setOpaque(true);

        rook.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Piece.pieces[x[0]][y[0]] = new Rook(Piece.pieces[x[0]][y[0]].getColor());

                Piece.pieces[x[0]][y[0]].promote(x[0], y[0], x[1], y[1]);

                makeMove();

                setEnabled(true);
                promote_frame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        rook.setIcon(loadIcon("/Icons/rook_" + Piece.pieces[x[0]][y[0]].getColor() + "_" + (board[x[1]][y[1]].getBackground().getRGB() == new Color(255, 206, 158).getRGB() ? "light" : "dark") + ".png"));

        JLabel bishop = new JLabel();
        bishop.setPreferredSize(new Dimension(26, 26));
        bishop.setOpaque(true);

        bishop.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Piece.pieces[x[0]][y[0]] = new Bishop(Piece.pieces[x[0]][y[0]].getColor());

                Piece.pieces[x[0]][y[0]].promote(x[0], y[0], x[1], y[1]);

                makeMove();

                setEnabled(true);
                promote_frame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        bishop.setIcon(loadIcon("/Icons/bishop_" + Piece.pieces[x[0]][y[0]].getColor() + "_" + (board[x[1]][y[1]].getBackground().getRGB() == new Color(255, 206, 158).getRGB() ? "light" : "dark") + ".png"));

        JLabel queen = new JLabel();
        queen.setPreferredSize(new Dimension(26, 26));
        queen.setOpaque(true);

        queen.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Piece.pieces[x[0]][y[0]] = new Queen(Piece.pieces[x[0]][y[0]].getColor());

                Piece.pieces[x[0]][y[0]].promote(x[0], y[0], x[1], y[1]);

                makeMove();

                setEnabled(true);
                promote_frame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        queen.setIcon(loadIcon("/Icons/queen_" + Piece.pieces[x[0]][y[0]].getColor() + "_" + (board[x[1]][y[1]].getBackground().getRGB() == new Color(255, 206, 158).getRGB() ? "light" : "dark") + ".png"));

        JLabel knight = new JLabel();
        knight.setPreferredSize(new Dimension(26, 26));
        knight.setOpaque(true);

        knight.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Piece.pieces[x[0]][y[0]] = new Knight(Piece.pieces[x[0]][y[0]].getColor());

                Piece.pieces[x[0]][y[0]].promote(x[0], y[0], x[1], y[1]);

                makeMove();

                setEnabled(true);
                promote_frame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        knight.setIcon(loadIcon("/Icons/knight_" + Piece.pieces[x[0]][y[0]].getColor() + "_" + (board[x[1]][y[1]].getBackground().getRGB() == new Color(255, 206, 158).getRGB() ? "light" : "dark") + ".png"));

        panel4.add(rook);
        panel4.add(new JLabel());
        panel4.add(bishop);
        panel4.add(new JLabel());
        panel4.add(queen);
        panel4.add(new JLabel());
        panel4.add(knight);

        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()), "Pieces", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION));

        promote_frame.add(panel4);

        promote_frame.setVisible(true);

    }

    private void makeMove() {

        play_counter++;

        for(int k = 0; k < board.length; k++) {
            for(int l = 0; l < board[0].length; l++) {
                if(Piece.pieces[k][l] == null) {
                    board[k][l].setIcon(null);
                }
                else {
                    board[k][l].setIcon(loadIcon(getIcon(Piece.pieces[k][l], k, l)));
                }
            }
        }

        play += Piece.last_move_info;

        if(play_counter % 2 == 0) {
            textarea.setText(textarea.getText() + play + "\n");
            play = "" + (play_counter / 2 + 1) + ". ";
        }

        SwingUtilities.updateComponentTreeUI(panel);

        if(turn) {
            turn = false;
            players_turn.setText("White's turn");
            black.suspend();
            white.resume();
            black_clock.setForeground(Color.black);
            white_clock.setForeground(Color.green);
        }
        else {
            turn = true;
            players_turn.setText("Black's turn");
            white.suspend();
            black.resume();
            black_clock.setForeground(Color.green);
            white_clock.setForeground(Color.black);
        }

        board[x[0]][y[0]].setEnabled(true);
        board[x[1]][y[1]].setEnabled(true);

    }

    private void end() {

        stop.setEnabled(false);

        play_counter = 0;
        play = "" + (play_counter + 1) + ". ";

        turn = false;

        for(int k = 0; k < board.length; k++) {
            for(int l = 0; l < board[0].length; l++) {
                board[k][l].setIcon(null);
                board[k][l].setEnabled(true);
                board[k][l].removeMouseListener(board[k][l].getMouseListeners()[0]);
            }
        }

        white_clock.setForeground(Color.black);
        black_clock.setForeground(Color.black);
        
        black.resume();
        white.resume();

        white.terminate();

        try {
            white.join();
        }
        catch(InterruptedException ex) {
        }
        
        black.terminate();
        
        try {
            black.join();
        }
        catch(InterruptedException ex) {
        }

        white = new Clock(white_clock);
        black = new Clock(black_clock);

        piece_selected = false;

        start.setEnabled(true);

        textarea.setText("");

    }

    private void start() {

        start.setEnabled(false);

        Piece.InitBoard();

        for(int k = 0; k < board.length; k++) {
            for(int l = 0; l < board[0].length; l++) {
                if(Piece.pieces[k][l] == null) {
                    board[k][l].setIcon(null);
                }
                else {
                    board[k][l].setIcon(loadIcon(getIcon(Piece.pieces[k][l], k, l)));
                }
            }
        }

        for(i = 0; i < board.length; i++) {
            for(j = 0; j < board[0].length; j++) {
                board[i][j].addMouseListener(new MouseListener() {

                    int temp_i = i;
                    int temp_j = j;

                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                        if(!piece_selected && (Piece.pieces[temp_i][temp_j] == null || (turn == false && Piece.pieces[temp_i][temp_j].getColor().equals("black")) || (turn == true && Piece.pieces[temp_i][temp_j].getColor().equals("white")))) {
                            return;
                        }

                        if(!piece_selected) {
                            x[0] = temp_i;
                            y[0] = temp_j;
                            piece_selected = true;
                            board[x[0]][y[0]].setEnabled(false);
                        }
                        else {
                            x[1] = temp_i;
                            y[1] = temp_j;

                            int res;

                            if((res = Piece.pieces[x[0]][y[0]].move(x[0], y[0], x[1], y[1])) == Piece.MOVED) {
                                makeMove();
                            }
                            else if(res == Piece.PROMOTION) {
                                board[x[0]][y[0]].setEnabled(true);
                                pawn_promotion();
                            }
                            else {
                                board[x[0]][y[0]].setEnabled(true);
                            }

                            piece_selected = false;

                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }

                });
            }
        }

        white_clock.setForeground(Color.green);

        white.start();
        black.start();
        black.suspend();

        stop.setEnabled(true);

    }

    private ImageIcon loadIcon(String path) {

        return new ImageIcon(getClass().getResource(path));
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        Chess test = new Chess();

        test.setVisible(true);

    }

}
