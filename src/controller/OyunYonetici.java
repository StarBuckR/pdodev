
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.AltBar;
import view.NewJFrame;


public class OyunYonetici{
    KutuYonetici ky;
    NewJFrame frame;
    AltBar altBar;
    int onceki = -1;
    int puan = 0;
    private byte seconds = 0;
    private short minutes = 0;
    private short hours = 0;
    
    private DecimalFormat timeFormatter;
    private Timer timer;
    
    public OyunYonetici(KutuYonetici ky, NewJFrame frame){
        this.ky = ky;
        this.frame = frame;
        altBar = new AltBar(frame);
    }
    
    public void oyunuBaslat(){
        ky.kutuDuzenleyici();
        timeFormatter = new DecimalFormat("00");
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(seconds < 59){
                    seconds++;
                }
                else{
                   minutes++;
                   seconds = 0;
                   if(minutes > 59){
                       hours++;
                       minutes = 0;
                   }
                }
                altBar.setSure(timeFormatter.format(00) + ":" + timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds));
            }
        });
        timer.restart();
        altBar.setPuan(Integer.toString(0));
    }
    
    public void karsilastir(int index){
        if(!ky.jtbtn[index].getText().isEmpty()){
            hepsiniTemizle();
            onceki = -1;
            return;
        }
        
        if(onceki == -1){
            buttonAyarla(index);
            onceki = index;
            return;
        }
        
        if(ky.kutular[onceki] == ky.kutular[index]){
            buttonAyarla(index);
            puan += 50;
        }
        else{
            buttonTemizle(index);
            buttonTemizle(onceki);
            puan -= 20;
        }
        onceki = -1;
        oyunSonu();
    }
    
    public void buttonAyarla(int index){
        ky.jtbtn[index].setSelected(true);
        ky.jtbtn[index].setText(Integer.toString(ky.kutular[index]));
        ky.jtbtn[index].setEnabled(false);
    }
    
    public void buttonTemizle(int index){
        ky.jtbtn[index].setSelected(false);
        ky.jtbtn[index].setText("");
        ky.jtbtn[index].setEnabled(true);
    }
    
    public void hepsiniTemizle(){
        for(int i = 0; i < ky.jtbtn.length; i++){
            buttonTemizle(i);
        }
    }
    
    public void oyunSonu(){
        int count = 0;
        for(int i = 0; i < ky.jtbtn.length; i++){
            if(ky.jtbtn[i].isSelected()){
                count++;
            }
        }
        if(count == ky.jtbtn.length){
            puanHesapla();
            JOptionPane.showMessageDialog(null, "Oyun Bitti");
        }
    }
    
    public void puanHesapla(){
        puan *= ky.jtbtn.length;
        altBar.setPuan(Integer.toString(puan));
        sureHesapla();
    }
    
    public void sureHesapla(){
        timer.stop();
        altBar.enableBaslatButton();
    }
}