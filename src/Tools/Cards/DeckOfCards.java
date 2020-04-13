/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Cards;

import Tools.Cards.Card;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author quent
 */
public class DeckOfCards {
    
     
    private Card[] deck;
    private final int deckLength = 52;
    private int cursor; 

    
    /**
     * Constructor
     */
    public DeckOfCards() throws IOException 
    {
       
        init_deck();
       
    }
    
    public void init_deck() throws IOException
    {
        String [] faces = {"2","3","4","5","6","7","8","9","10","J", "Q", "K","A"};
        String [] suits = {"Spades","Diamonds","Hearts","Clubs"}; 
        deck = new Card[deckLength];
        
        for (int suit = 0; suit < suits.length; suit++) {
            for (int face = 0; face < faces.length; face++) {
                int index = face + (suit *13);
                String current_face = faces[face]; 
                String current_suit = suits[suit];
                
                deck[index] = new Card(current_face, current_suit, face+2, ImageIO.read(new File("Cards_img/"+current_face+current_suit.substring(0,1)+".png")));
            }
            
        }
        
    }
    
    public void display_deck()
    {
        for(Card card : deck)
          System.out.println(card);
    }
    
    public Card[] getcards()
    {
        return this.deck; 
    }
    
    public Card getCardWithIndex(int x)
    {
        return deck[x];
    }
    
    public Card getCardWithFaceAndValue(String suit, String face)
    {
        Card target; 
        for (Card card:deck) {
            if (card.getFace() == face && card.getSuit() == suit) {
                target = card; 
                return target; 
            }
        }
        return null; 
    }
    
    public void shuffle()
    {
        List<Card> deckList = Arrays.asList(deck);
        Collections.shuffle(deckList);
        deckList.toArray(deck);
        
    }
    
    /**
     * return the top cards and increment the cursor variable. 
     * @return 
     */
    public Card deal_card()
    {
        int cards_left = deck.length - (cursor+1);
        if (cursor < deck.length) {
            //System.out.println("Il reste : "+ cards_left + " cartes dans le deck.");
            return deck[cursor++];
            
        }
        
        else{
            this.cursor = 0; 
            return deck[cursor++];
        }   
    }
    
    
}
