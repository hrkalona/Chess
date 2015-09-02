
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hrkalona2
 */
public class Clock extends Thread {
    private int seconds;
    private JLabel label;
    private boolean running;
    
    public Clock(JLabel label) {
        
        seconds = 5 * 60;
        this.label = label;
        label.setText("" + String.format("%02d", seconds / 60) + ":" + String.format("%02d",seconds % 60));
        running = true;
        
    }
    
    public void terminate() {
        
        running = false;
        
    }
    
    @Override
    public void run() {
        
        while(seconds > 0 && running) {
            
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ex) {}

            seconds--;

            label.setText("" + String.format("%02d", seconds / 60) + ":" + String.format("%02d",seconds % 60));
        }
        
    }
    
}
