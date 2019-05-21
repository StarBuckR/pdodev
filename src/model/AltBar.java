
package model;

import view.NewJFrame;

public class AltBar {
    NewJFrame frame;
    
    public AltBar(NewJFrame frame){
        this.frame = frame;
    }
    public AltBar(){}
    
    public void setPuan(String puan){
        frame.puanLabel.setText(puan);
    }
    
    public void setSure(String sure){
        frame.sureLabel.setText(sure);
    }
    
    public void enableBaslatButton(){
        frame.baslatButton.setEnabled(true);
    }
    
}
