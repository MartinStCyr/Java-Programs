/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenblackjack;

/**
 *
 * @author Martin
 */
public class Configuration {
    int playerNumber_=1;
    int deckNumbers_=8;
    
    Configuration()
    {

    }
    
    Configuration(Configuration config)
    {
      deckNumbers_=config.getDeckNumbers();
    }
    
    Configuration(int deckNumbers)
    {
        deckNumbers_=deckNumbers;
    }
    
    public int getDeckNumbers()
    {
        return deckNumbers_;
    }
    
    public int getPlayerNumber()
    {
        return playerNumber_;
    }
    public void setPlayerNumber(int number)
    {
        playerNumber_=number;
    }
}
