/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author Martin
 */
public class DragPanel extends JPanel {
    private volatile int draggedFromX, draggedFromY;
    JLabel myValueLabel;
    JLabel percentPickLabel;
    int dragPanelValue_;
    JLabel imageLabel_;
    public DragPanel(int value)
    {
        dragPanelValue_=value;
        //setSize(150,200);
        //setPreferredSize(new Dimension(150,200));
        
        imageLabel_ = new JLabel( new ImageIcon( ".//GUI//Card"+value+".png"));
        add(imageLabel_);
        
        myValueLabel=new JLabel(String.valueOf(value));
        myValueLabel.setAlignmentX(CENTER_ALIGNMENT);
        myValueLabel.setPreferredSize(new Dimension(50, 20));
        add(myValueLabel);
        percentPickLabel=new JLabel("\n0");
        percentPickLabel.setAlignmentX(CENTER_ALIGNMENT);
        percentPickLabel.setPreferredSize(new Dimension(50, 20));
        add(percentPickLabel);
        
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                draggedFromX = e.getX();
                draggedFromY = e.getY();
                GameFrame.getInstance().setOnTop(Integer.parseInt(myValueLabel.getText()));
            }
            
            public void mouseReleased(MouseEvent e)
            {
                GameFrame game = GameFrame.getInstance();
                Player player[] = game.getPlayer();
                //System.out.print(player.length);
                for(int i=0;i<game.getConfig().getPlayerNumber();i++)
                {
                    
                    if(e.getXOnScreen()>=player[i].getLocation().x && e.getXOnScreen()<=player[i].getWidth()+player[i].getLocation().x 
                        && e.getYOnScreen()>=player[i].getLocation().y && e.getYOnScreen()<=player[i].getHeight()+25+player[i].getLocation().y)
                    {
                        //System.out.print("Card given to Player : "+i);
                        if(game.getDealer().getCardAmount()>0 && GameFrame.getInstance().cardGiven(dragPanelValue_))
                        {
                            player[i].addCard(dragPanelValue_);
                            player[i].calculateOddsToBustOnNextTurn();
                        }
                        else
                        {
                            System.out.print("Dealer must have at least 1 card");
                        }
                    }
                }
                Player dealer = game.getDealer();
                if(e.getXOnScreen()>=dealer.getLocation().x && e.getXOnScreen()<=dealer.getWidth()+dealer.getLocation().x 
                        && e.getYOnScreen()>=dealer.getLocation().y && e.getYOnScreen()<=dealer.getHeight()+25+dealer.getLocation().y)
                    {
                        //System.out.print("Card given to Dealer ");
                        if(GameFrame.getInstance().cardGiven(dragPanelValue_))
                        {
                            dealer.addCard(dragPanelValue_);
                            dealer.calculateOddsToBustOnNextTurn();
                        }
                    }
                
                setLocation(((dragPanelValue_-1)%5)*100+20,300+170*(int)(dragPanelValue_/6));
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                setLocation(e.getX() - draggedFromX +getLocation().x,
                        e.getY() - draggedFromY + getLocation().y);   
            }
        });
    }
    
    public void actualizePercent(double percent)
    {
        //System.out.print(percent+"\n");
        //Make sure the percentage%1000 is not going to give 0
        if(percent==1.0)
        {
            percentPickLabel.setText("100.0%");
        }
        else if(percent==0.1)
        {
            percentPickLabel.setText("10.0%");
        }
        else
        {
            percent=(double)((int)(percent*1000)%1000)/10;
            percentPickLabel.setText(String.valueOf(percent)+"%");
        }
        
    }
}
