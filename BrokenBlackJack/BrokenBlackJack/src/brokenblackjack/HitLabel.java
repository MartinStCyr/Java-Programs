/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Martin
 */
public class HitLabel extends JLabel{
    JLabel imageLabel_;
    HitLabel()
    {
        imageLabel_ = new JLabel( new ImageIcon( ".//GUI//HitLabel.png"));
        add(imageLabel_);
    }
}
