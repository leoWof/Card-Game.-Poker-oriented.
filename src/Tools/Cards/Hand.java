/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Cards;

import Tools.Cards.DeckOfCards;
import Tools.Cards.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author quent
 */
public class Hand {
    
    protected Card[] hand; 
        
    
    public Hand(DeckOfCards deck, int length)
    {
        hand = new Card[length]; 
        for (int i = 0; i <5; i++) {
            this.hand[i] = deck.deal_card();

        }
    }
    
    public void dislayHand()
    {
        for (Card card : hand) {
            System.out.println(card);
        }
    }
    
    
    
    
    
}
