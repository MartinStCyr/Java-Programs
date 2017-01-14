/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brokenblackjack;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
/**
 *
 * @author Martin
 */

public class Player extends JPanel{
    static Player dealer_=null;
    int id_;
    int aceAmount_=0;
    int hand_[];
    int numberOfCards_=0;
    int totalValue_[];
    double bustChances_=0;
    double blackJackChances_=0;
    JLabel playerName_;
    JLabel sumLabel_[];
    JLabel blackJackLabel_;
    JLabel choiceLabel_;
    double oddsToBlackJack_=0;
    HitLabel hitLabel_=new HitLabel();
    RemovePlayerButton removePlayerButton_;
    //choiceChart comes from : https://www.google.ca/search?q=blackjack+chart&tbm=isch&imgil=Hw_SX2RDqcYB1M%253A%253BgoSkeeaoF6TW8M%253Bhttp%25253A%25252F%25252Fwww.onlinecasinohound.com%25252Fcasino-games%25252Fblackjack%25252Fblackjack-strategy-chart%25252F&source=iu&pf=m&fir=Hw_SX2RDqcYB1M%253A%252CgoSkeeaoF6TW8M%252C_&usg=__HKfOKa6C6JkxJ0wX5OtpKMgj-hY%3D&biw=1280&bih=911&ved=0ahUKEwj7ucyx0trLAhVG1BoKHbC7BMsQyjcIKw&ei=r470VrumFcaoa7D3ktgM#imgrc=Hw_SX2RDqcYB1M%3A
    static char choiceChart[][]={
    /*DEALER 0*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S'},
    /*DEALER 1*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S'},
    /*DEALER 2*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S'},
    /*DEALER 3*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S'},
    /*DEALER 4*/{'H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S','S'},
    /*DEALER 5*/{'H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S','S'},
    /*DEALER 6*/{'H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S','S','S','S','S','S'},
    /*DEALER 7*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S'},
    /*DEALER 8*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S'},
    /*DEALER 9*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S'},
    /*DEALER 10*/{'H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','H','S','S','S','S','S'},
    };

    public static Player getDealer()
    {
        if(dealer_==null)
        {
            dealer_=new Player(-1,"Dealer");
        }
        return dealer_;
    }
    
    Player()
    {
        sumLabel_=new JLabel[3];
        totalValue_=new int[3];
        setBackground(new Color(0xe7bda2));
        setSize(80,80);
        hand_=new int[10];
        //setPreferredSize(new Dimension(50,50));
        sumLabel_[0]=new JLabel();
        sumLabel_[1]=new JLabel();
        sumLabel_[2]=new JLabel();
        sumLabel_[0].setPreferredSize(new Dimension(50,15));
        sumLabel_[0].setLocation(50,10);
        sumLabel_[0].setText(String.valueOf(totalValue_[0]));
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(sumLabel_[0]);
        totalValue_[0]=0;
        blackJackLabel_=new JLabel("BLACKJACK!!!");
        blackJackLabel_.setPreferredSize(new Dimension(50,15));
        blackJackLabel_.setLocation(50,10);

        choiceLabel_=new JLabel("");
        
        choiceLabel_.setPreferredSize(new Dimension(50,15));
        choiceLabel_.setLocation(50,25);
        add(choiceLabel_);
    }
    
    Player(int number,String name)
    {
        id_=number;
        playerName_=new JLabel(name);
        playerName_.setPreferredSize(new Dimension(50,15));
        add(playerName_,0);
        sumLabel_=new JLabel[3];
        totalValue_=new int[3];
        setBackground(new Color(0xe7bda2));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setSize(80,100);
        hand_=new int[10];
        //setPreferredSize(new Dimension(50,50));
        sumLabel_[0]=new JLabel();
        sumLabel_[1]=new JLabel();
        sumLabel_[2]=new JLabel();
        sumLabel_[0].setPreferredSize(new Dimension(50,15));
        sumLabel_[0].setLocation(50,10);
        sumLabel_[0].setText(String.valueOf(totalValue_[0]));
        add(sumLabel_[0]);
        totalValue_[0]=0;
        blackJackLabel_=new JLabel("BLACKJACK!!!");
        blackJackLabel_.setPreferredSize(new Dimension(50,15));
        blackJackLabel_.setLocation(50,10);
        if(id_>=0)
        {
            removePlayerButton_=new RemovePlayerButton(id_);
            add(removePlayerButton_);
            removePlayerButton_.setLocation(this.getWidth()-removePlayerButton_.getWidth(),0);
        }
        choiceLabel_=new JLabel("");
        
        choiceLabel_.setPreferredSize(new Dimension(50,15));
        choiceLabel_.setLocation(50,25);
        add(choiceLabel_);
    }
    
    public void setPlayerName(String name)
    {
        playerName_.setText(name);
    }
    
    public void addCard(int cardValue)
    {
            if(totalValue_[aceAmount_]==21)
            {
                clearHand();
            }
            if(numberOfCards_==hand_.length) //If your hand is full of cards, shouldnt happen, but memory!
            {
                enlargeHand();
            }
            //Place the card in the hand
            hand_[numberOfCards_]=cardValue;
            //Tells that your hand has one more card
            numberOfCards_++;
            int maxLoop=aceAmount_;
            if(cardValue==1) //If you get an Ace, you want to have a new score where it's worth 11
            {
                aceAmount_++;
                if(aceAmount_>0)totalValue_[aceAmount_]=totalValue_[aceAmount_-1]+11; //Just to make sure that it added 10+1=11
                else
                    totalValue_[aceAmount_]=11;
                if(totalValue_[aceAmount_]<=21)
                {
                    sumLabel_[aceAmount_].setText(String.valueOf(totalValue_[aceAmount_]));
                    sumLabel_[aceAmount_].setPreferredSize(new Dimension(60,15));
                    //sumLabel_[aceAmount_].setAlignmentX(CENTER_ALIGNMENT);
                    sumLabel_[aceAmount_].setLocation(50,aceAmount_*10);
                    remove(choiceLabel_);
                    add(sumLabel_[aceAmount_]);
                    add(choiceLabel_);
                }
                else
                {
                    totalValue_[aceAmount_]=0;
                    //remove(sumLabel_[aceAmount_]);
                    aceAmount_--;
                }
            }       
            for(int i=0;i<=maxLoop;i++)
            {
                totalValue_[i]+=cardValue;
                sumLabel_[i].setText(String.valueOf(totalValue_[i]));
                if(totalValue_[i]>21)
                {
                    totalValue_[i]=0;
                    remove(sumLabel_[aceAmount_]);
                    if(aceAmount_>0)
                    {
                        aceAmount_--;
                    }
                    else
                    {
                        add(sumLabel_[0]);
                        sumLabel_[0].setText("BUSTED");
                        totalValue_[0]=0;
                        oddsToBlackJack_=0;
                    }
                }
            }
            for(int i=0;i<=aceAmount_;i++)
            {
                if(totalValue_[i]==21)
                {
                    add(blackJackLabel_);
                    blackJackLabel_.setText("BLACKJACK!!!");
                    return;
                }
            }
            remove(blackJackLabel_);
            if(GameFrame.getInstance().getDealer().getId() != id_)
            {
                if(choiceChart[GameFrame.getInstance().getDealer().getCardAt(0)][totalValue_[0]] == 'H')
                {
                    add(hitLabel_,0);
                }
                else if(choiceChart[GameFrame.getInstance().getDealer().getCardAt(0)][totalValue_[0]] == 'S')
                {
                    
                }
                choiceLabel_.setText(String.valueOf(choiceChart[GameFrame.getInstance().getDealer().getCardAt(0)][totalValue_[0]]));
            
            }
    }
    
    public boolean retireCard(int cardValue)
    {
        if(hand_[numberOfCards_-1]==cardValue)
        {
            hand_[numberOfCards_-1]=0;
            numberOfCards_--;
            //totalValue_=sumHand();
            return true;
        }
        return false;
    }
    
    private int sumHand()
    {
        int total=0;
        for(int i=0;i<numberOfCards_;i++)
        {
            total+=hand_[i];
        }
        return total;
    }
    
    public void clearHand()
    {
        //Reset every cards to 0
        for(int i=0;i<numberOfCards_;i++)
        {
            hand_[i]=0;
        }
        //Reset gui aspect
        for(int i=0;i<=aceAmount_;i++)
        {
            sumLabel_[i].setText("");
            totalValue_[i]=0;
        }
        sumLabel_[0].setText("0");
        //Reset the number of cards
        numberOfCards_=0;
        blackJackLabel_.setText("");
        choiceLabel_.setText("");
        aceAmount_=0;
    }
    
    public void calculateOddsToBustOnNextTurn()
    {
        double oddsToBustOnNextTurn=0;
        GameFrame myGame=GameFrame.getInstance();
        int numberToBlackJack=21-totalValue_[0]; //15 => 6 => 7,8,9,10
        for(int i=numberToBlackJack+1;i<=10;i++)
        {
            /*Probability to bust is the probability to pick something that 
            would make your lowest value [0] goes over 21
            Which also means sum of all odds that would bump the 21 limit*/
            oddsToBustOnNextTurn+=myGame.getOdds(i);
        }
        //System.out.print("Bust Chance : "+oddsToBustOnNextTurn+"\n");
    }
    
    public int getAceAmount()
    {
        return aceAmount_;
    }
    
    public int getCardAmount()
    {
        return numberOfCards_;
    }
    
    public int getCardAt(int position)
    {
        return hand_[position];
    }
    
    private void enlargeHand()
    {
        int tempArray[]=new int[hand_.length];
        for(int i=0;i<hand_.length;i++)
        {
            tempArray[i]=hand_[i];
        }
        hand_=new int[hand_.length*2+1];
        for(int i=0;i<tempArray.length;i++)
        {
            hand_[i]=tempArray[i];
        }
    }
    
    public void setName(String name)
    {
        playerName_.setText(name);
    }
    
    public void setId(int id)
    {
        id_=id;
    }
    public int getId()
    {
        return id_;
    }
    public void actualizeRemovePlayerReference()
    {
        removePlayerButton_.setId(id_);
    }
    
    public void chooseNextAction()
    {
        GameFrame myGame=GameFrame.getInstance();
        //myGame.getDealer().
        //System.out.print("Player "+id_+);
    }       
}
