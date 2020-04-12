/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.Cards;

import java.awt.image.BufferedImage;

/**
 *
 * @author quent
 */
public class Card {

    private String face, suit; 
    private int faceValue; 
    private BufferedImage cardImage; 
    
    /**
     * 
     * @param face "clubs, spades, hearts, diamonds"
     * @param suit "2,3,4,5 ..., king, ace"
     * @param img "A buffered img that represents a card"
     * @param faceValue "2,3,4,5,...13,14"
     */
    
    public Card(String face, String suit, int faceValue,BufferedImage img){
        
        this.face = face; 
        this.suit = suit; 
        this.cardImage = img;
        this.faceValue = faceValue;
    }
    
    /**
     * This return a String representation of the Card Object
     * @return 
     */
    
    public String toString()
    {
        return this.face +" of " + this.suit +". The power value of the card is: "+this.faceValue; 
    }
    public String getFace()
    {
        return this.face; 
    }
    public int getFaceValue()
    {
        return this.faceValue; 
    }
    
    public String getSuit()
    {
        return this.suit; 
    }
    
    public BufferedImage getCardImg()
    {
        return this.cardImage; 
    }
     
    
}
