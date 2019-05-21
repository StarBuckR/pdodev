
package action;

import controller.KutuYonetici;
import controller.OyunYonetici;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KutuAction implements ActionListener {
    private KutuYonetici ky;
    private OyunYonetici oy;
    
    public KutuAction(KutuYonetici ky, OyunYonetici oy) {
        this.ky = ky;
        this.oy = oy;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        for(int i = 0; i < ky.jtbtn.length; i++){
            if(ae.getSource() == ky.jtbtn[i]){
                oy.karsilastir(i);
            }
        }
    }
    
}
