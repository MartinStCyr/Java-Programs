/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
/**
 *
 * @author Martin
 */
public class ReshuffleDeckButton extends JButton{
    
    public ReshuffleDeckButton(String text)
    {
        this.setSize(100,50);
        this.setText(text);
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                GameFrame game=GameFrame.getInstance();
                game.resetDeck();
            }
        });
    }
}