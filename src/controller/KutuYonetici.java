
package controller;

import action.KutuAction;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import model.Yerlesim;
import view.NewJFrame;

public class KutuYonetici{
    JPanel jPanel;
    KutuAction ka;
    OyunYonetici oy;
    public int adet, toplamAdet;
    public int kutular[];
    public JToggleButton jtbtn[];
    int count = 0;
    
    public KutuYonetici(String yerlesimText, int adet, JPanel jPanel, NewJFrame frame){
        this.jPanel = jPanel;
        this.adet = adet;
        Yerlesim ys = new Yerlesim(yerlesimText, adet, jPanel);
        
        if(adet % 2 != 0)
            this.adet++;
        
        toplamAdet = this.adet;
        jtbtn = new JToggleButton[this.adet];
        kutular = new int[this.adet];
        sayiUret();
        oy = new OyunYonetici(this, frame);
        ka = new KutuAction(this, oy);
        kutuUret(this.adet, jPanel);
    }
    
    private void kutuUret(int adet, JPanel jPanel){
        oy.altBar.enableBaslatButton();
        for(int i = 0; i < adet; i++){
            int random = randomUretici();
            kutuUretici(jPanel, random);
        }
        jPanel.revalidate();
    }
    
    private void kutuUretici(JPanel jPanel, int random){
        jtbtn[count] = new JToggleButton();
        jtbtn[count].setPreferredSize(new Dimension(50, 50));
        jtbtn[count].setText(Integer.toString(random));
        jtbtn[count].addActionListener(ka);
        jPanel.add(jtbtn[count++]);
    }
    
    private int randomUretici(){
        Random r = new Random();
        int random, donecekDeger;
        if(adet < 2){
            random = adet;
        }
        else{
            random = r.nextInt(adet - 1);
        }
        
        donecekDeger = kutular[random];
        
        for(int i = random; i < adet - 1; i++){
            kutular[random] = kutular[++random];
        }
        adet--;
        
        return donecekDeger;
    }
    
    private void sayiUret(){
        int count = 0;
        for(int j = 0; j < 2; j++){
            for(int i = 1; i <= adet / 2; i++){
                kutular[count++] = i;
            }
        }
    }
    
    public void kutuDuzenleyici(){
        for(int i = 0; i < toplamAdet; i++){
            kutular[i] = Integer.parseInt(jtbtn[i].getText());
            jtbtn[i].setText("");
        }
    }
    
    public void oyunuBaslat(){
        oy.oyunuBaslat();
    }
}
