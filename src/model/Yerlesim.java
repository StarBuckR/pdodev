
package model;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Yerlesim extends JToggleButton{
    JPanel jPanel;
    
    public Yerlesim(String yerlesimText, int adet, JPanel jPanel) {
        this.jPanel = jPanel;
        jPanel.removeAll();
        jPanel.updateUI();
        
        if(adet % 2 != 0)
            adet++;
        
        if(yerlesimText.equalsIgnoreCase("gridlayout")){
            int row;
            if(adet < 3)
                row = 1;
            else
                row = adet / 3;
            jPanel.setLayout(new GridLayout(row, 0));
        }
        else if(yerlesimText.equalsIgnoreCase("flowlayout")){
            FlowLayout fl = new FlowLayout();
            jPanel.setLayout(fl);
        }
    }
}
