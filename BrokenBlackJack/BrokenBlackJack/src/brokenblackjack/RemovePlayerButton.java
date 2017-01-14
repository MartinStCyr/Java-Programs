/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 *
 * @author Martin
 */
public class RemovePlayerButton extends JPanel{
    
    private int id_;
    public RemovePlayerButton(int id)
    {
        id_=id;
        this.setSize(5,5);
        setBackground(Color.blue);
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                GameFrame game=GameFrame.getInstance();
                game.removePlayer(id_);
            }
        });
    }
    public void setId(int id)
    {
        id_=id;
    }
}
