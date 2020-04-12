/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Cards;

import Tools.Cards.Hand;
import Tools.Cards.DeckOfCards;
import Tools.Cards.Card;
import java.util.Arrays;

/**
 *
 * @author quent
 */
public class Poker_hand extends Hand {
    
    private boolean quinteFlushRoyal = false;
    private boolean quinteFlush = false;
    private boolean square = false;
    private boolean full = false;
    private boolean color = false;
    private boolean quinte = false;
    private boolean brelan = false;
    private boolean doublePair = false; 
    private boolean pair = false; 
    private boolean highCard = true; 
    
    private int strongestCardValue = 0;
    private int weakestCardValue = 0;
    private int[] handStrength = {0,0}; 
    private String[] handName = {"High Card","Pair", "Double Pair","Brelan","Quinte", "Couleur", "Full", "Square", "Quinte Flush", "Quinte Flush Royale!"};
    private boolean[] possibleHands = new boolean[]{quinteFlushRoyal,quinteFlush,square,full,color, quinte, brelan, doublePair,pair};
   
    

    
    public Poker_hand(DeckOfCards deck,int x) {
        super(deck,x);
        setStrongesAndSmallest();
    }
    
    
    public Card getCardByFaceValue(int x)
    {
        for(Card card : this.hand)
        {
            if(card.getFaceValue() == x)
            {
                return card; 
            }
        }
        return null ;
        
    }
    
    
    public void setStrongesAndSmallest()
    {
        Card strongest = this.hand[0];
        Card weakest = this.hand[0];
        for(Card card : this.hand)
        {
            if(card.getFaceValue() > strongest.getFaceValue())
            {
                strongest = card;  
            }
            
            if(card.getFaceValue() < weakest.getFaceValue())
            {
                weakest = card;  
            }
        }
        this.strongestCardValue = strongest.getFaceValue();
        this.weakestCardValue = weakest.getFaceValue();
        
    }
    
    public int getStrongest()
    {
        return this.strongestCardValue;
    }
    
  /**
     * Create an array of int and increment the value of its member based upon the occurency of a given card in 
     * the hand of a player.
     * 
     * @return an Array of int
     */
    public int[] getOccurenceOfFace()
    {
        int [] rank = new int[15];
        
        for (int i = 0; i < rank.length; i++) {
            rank[i] = 0; 
        }
        
           for (int i = 0; i < hand.length; i++) {
            rank[hand[i].getFaceValue()]++; 
        }

        return rank; 
    }
    
    public void setHandStrength(int x, int y)
    {
        this.handStrength[0] = x;
        this.handStrength[1] = y; 
    }
    
    public void treatOcurrenceOfFace()
    {

        int[] toTreat = getOccurenceOfFace(); 
        int amountOfPair = 0; 
         
      
        for(int index = 0; index < toTreat.length; index ++){
            switch (toTreat[index]){
                case 2:
                    amountOfPair ++; 
                    this.pair = true; 
                    setHandStrength(amountOfPair,getCardByFaceValue(index).getFaceValue());
                    //amountOfPair will only have two potential values: 1 or 2. Wich correspond to the hand Strength of a pair or a double pair.
                    //The faceValue of the pair is updated each time a new stronger pair is discovered, wich is what we want. 
                    break;
                    
                case 3: 
                    this.brelan = true; 
                    setHandStrength(3,getCardByFaceValue(index).getFaceValue());
                    break;
                    
                case 4: 
                    this.square = true; 
                    setHandStrength(7,getCardByFaceValue(index).getFaceValue());
                    break;    
               
            }
            
            
        }
        
    }
    
    
    
    public void setColorHand()
    {
        String famille = hand[0].getSuit();
        this.color = true; 
        for(Card card : hand)
        {
            if(card.getSuit()!= famille)
            {
                this.color = false; 
            }
        }
        if (this.color) {
            setHandStrength(5,this.strongestCardValue);

        }
      
    }
    
    /**
     * Verifiy if the hand contains a quinte. 
     * It find the smallest value in the hand, create a suit based upon this card, 
     * and finally compare it to the actual potential 'suit' in the hand.
     */
    public void setQuinte()
    {
        int[] neededKey = new int[5]; 
        int[] collectedKey = new int[5];     
       
        
        neededKey[0] = this.weakestCardValue;
        for (int i = 1; i < 5; i++) {
            neededKey[i] = neededKey[i-1]+1;
        }
    
        //Create an array containing the actual suit and sort it
        for (int i = 0; i < hand.length; i++) {
            collectedKey[i] = hand[i].getFaceValue();
            
        }
        Arrays.sort(collectedKey);
        
        //Compare the actual suit to the needed suit.
        if (Arrays.equals(collectedKey, neededKey)) {
            this.quinte = true; 
            setHandStrength(4, this.strongestCardValue);
        }     
        
    }
    
    
    
    /**
     * Check if the hand contains both a brelan and a pair.
     * Those conditions create a Full and cancel the brelan and the pair. 
     */
    public void setFull()
    {
        if(this.pair && this.brelan){
            this.full = true; 
            handStrength[0] = 6;
            this.brelan = false;
            this.pair = false; 
        }
        //Since the strength of the full is based upon the strength of the brelan, 
        //There is no need to modify the handStrength[1] here.
        
    }
    
    
    
    
    
    /**
     * Check if the hand containes a color and a quinte. 
     * Those conditions create a Quinte Flush and cancel the color and the quinte
     */
    public void setQuinteFlush()
    {
        if (this.color && this.quinte) {
            this.quinteFlush = true; 
            handStrength[0] = 8;
            this.color = false; 
            this.quinte = false; 
        }
        //Same as for the rules on the full, here the strength of a quinteFlush
        // is determined by the strenth of the quinte.
       
    }
    
    /**
     * Check if an Ace is contained within a Quinte Flush. 
     * If so, the Quinte Flush becomes a Royal Quinte Flush. 
     */
    public void setQuinteFlushRoyale()
    {
        if (this.quinteFlush) {
            for(Card card:hand)
            {
                if (card.getFaceValue() == 14) {
                    this.quinteFlushRoyal = true; 
                    handStrength[0] = 9;
                    this.quinteFlush = false; 
                }
            }
            
        }
        
    }
    
    
    public void checkHandPossibilities()
    {
        treatOcurrenceOfFace();
        setColorHand();
        setQuinte();
        setFull();
        setQuinteFlush();
        setQuinteFlushRoyale();
        if (handStrength[0] == 0) {
            handStrength[1] = this.strongestCardValue;
        }
    }
    
    public void displayTypeOfHand()
    {
        System.out.println("\nType of hand: "+ handName[handStrength[0]]);
        System.out.println("Strength of the "+handName[handStrength[0]]+":"+handStrength[1]);
        System.out.println("Overrall strength of the hand: "+handStrength[0]);
    }
    
    public int[] getHandStrength()
    {
        return this.handStrength;
    }
    
    
}
