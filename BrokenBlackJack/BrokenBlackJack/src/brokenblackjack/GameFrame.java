/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;

import javax.swing.JPanel;
import java.awt.Color;


/**
 *
 * @author Martin
 */
public class GameFrame extends javax.swing.JFrame {
    private static GameFrame singleton_=null;
    private Configuration config_=new Configuration();
    int nbCard[]=new int[10];
    double Odds[]=new double[10];
    int totalCards;
    DragPanel cardPanels[];
    Player player_[]=new Player[8];
    Player dealer_=new Player(-1,"Dealer");
    NextTurnButton nextTurnButton_=new NextTurnButton("Next Turn!");
    AddPlayerButton addPlayerButton_=new AddPlayerButton("+");
    ReshuffleDeckButton reshuffleDeckButton_=new ReshuffleDeckButton("Reshuffle");
    /**
     * Creates new form GameFrame
     */
    
    public static GameFrame getInstance(Configuration config)
    {
        if(singleton_==null)
        {
            singleton_=new GameFrame(config);
        }
        return singleton_;
    }
    
    public static GameFrame getInstance()
    {
        if(singleton_!=null)
        {
            return singleton_;
        }
        else
        {
            return null;
        }
    }
    
    public void setOnTop(int cardValue)
    {
        this.add(cardPanels[cardValue-1],0);
    }
    
    protected GameFrame(Configuration config)
    {
        initComponents();
        //Create the configuration
        config_=new Configuration(config);
        //Initialize cards amount and initial probabilities
        cardPanels=new DragPanel[10];
        //Set background green
        this.getContentPane().setBackground(Color.green);
        for(int i=0;i<=4;i++)
        {
            cardPanels[i]=new DragPanel(i+1);
            this.add(cardPanels[i],i);
            //cardPanels[i].setBackground(Color.yellow);
            cardPanels[i].setLocation(i*100+20,300);
            cardPanels[i].setSize(100,150);
        }
        for(int i=5;i<10;i++)
        {
            cardPanels[i]=new DragPanel(i+1);
            this.add(cardPanels[i],i);
            //cardPanels[i].setBackground(Color.yellow);
            cardPanels[i].setLocation((i-5)*100+20,470);
            cardPanels[i].setSize(100,150);
        }
        initializeCards(config_.getDeckNumbers());
        for(int i=0;i<config_.getPlayerNumber();i++)
        {
            player_[i]=new Player(i,"Player "+String.valueOf(i+1));
            this.add(player_[i]);
            player_[i].setLocation(90*i+45,110);
        }
        this.add(addPlayerButton_);
        addPlayerButton_.setLocation(60+config_.getPlayerNumber()*90,125);
        this.add(nextTurnButton_);
        nextTurnButton_.setLocation(600,50);
        
        this.add(reshuffleDeckButton_);
        reshuffleDeckButton_.setLocation(600,550);
        
        this.add(dealer_);
        dealer_.setLocation(350,0);
        
        //System.out.print(this.getComponentCount());
        this.show();
    }
    
    protected GameFrame()
    {
        
    }
    
    public void addPlayer()
    {
        int newPlayerAmount=config_.getPlayerNumber()+1;
        player_[newPlayerAmount-1]=new Player(newPlayerAmount-1,"Player "+String.valueOf(newPlayerAmount));
        this.add(player_[newPlayerAmount-1]);
        player_[newPlayerAmount-1].clearHand();
        player_[newPlayerAmount-1].setLocation(90*(newPlayerAmount-1)+45,110);
        config_.setPlayerNumber(newPlayerAmount);
        if(config_.getPlayerNumber()<8)
        {
            addPlayerButton_.setLocation(addPlayerButton_.getLocation().x+90,125);
        }
        else
        {
            addPlayerButton_.setVisible(false);
        }
    }
    
    public void removePlayer(int pos)
    {
        if(config_.getPlayerNumber()==8)
        {
            addPlayerButton_.setVisible(true);
            addPlayerButton_.setLocation(addPlayerButton_.getLocation().x+90,125);
        }
        if(pos==config_.getPlayerNumber()-1)
        {
            player_[pos].setVisible(false);
            this.remove(player_[pos]);
            player_[pos]=null;
            config_.setPlayerNumber(config_.getPlayerNumber()-1);
            addPlayerButton_.setLocation(addPlayerButton_.getLocation().x-90,125);
            return;
        }
        this.remove(player_[pos]);
        //System.out.print(pos);
        for(int i=pos;i<config_.getPlayerNumber()-1;i++)
        {
            //Replace them in table
            player_[i]=player_[i+1];
            player_[i].setName("Player "+(i+1));
            //Replace them on left
            player_[i].setLocation(player_[i].getLocation().x-90,player_[i].getLocation().y);
            player_[i].setId(i);
            player_[i].actualizeRemovePlayerReference();
        }
        config_.setPlayerNumber(config_.getPlayerNumber()-1);
        addPlayerButton_.setLocation(addPlayerButton_.getLocation().x-90,125);
    }
    private void initializeCards(int deckNumber)
    {
        totalCards=deckNumber*52;
        for(int i=0;i<9;i++)
        {
            nbCard[i]=deckNumber*4;
            Odds[i]=(double)nbCard[i]/(double)totalCards;
            cardPanels[i].actualizePercent(Odds[i]);
        }
        nbCard[9]=deckNumber*16;
        Odds[9]=(double)nbCard[9]/(double)totalCards;
        cardPanels[9].actualizePercent(Odds[9]);
    }
    
    public Player[] getPlayer()
    {
        return player_;
    }
    
    public Player getDealer()
    {
        return dealer_;
    }
    
    public double getOdds(int value)
    {
        return Odds[value-1];
    }
    public Configuration getConfig()
    {
        return config_;
    }
    
    public boolean cardGiven(int value)
    {
        if(nbCard[value-1]>=1 ){
            totalCards--;
            nbCard[value-1]--;
            for(int i=0;i<=9;i++)
            {
                Odds[i]=(double)nbCard[i]/(double)totalCards;
                cardPanels[i].actualizePercent(Odds[i]);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void resetDeck()
    {
        initializeCards(config_.getDeckNumbers());
        for(int i=0;i<getConfig().getPlayerNumber();i++)
        {
            player_[i].clearHand();
        }
        dealer_.clearHand();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
        //this.add(myDrag);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
