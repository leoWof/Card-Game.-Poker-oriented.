/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Gestion.Rounds;
import Player.Poker_player;
import Cards.Poker_hand;
import Cards.DeckOfCards;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author quent
 */
public class Main {
    
    private static Poker_player players[];
    private static boolean gameOver = false; 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Creation of the deck
        DeckOfCards deck = new DeckOfCards(); 
        //Shuffle
        deck.shuffle();
        //Creation of a set of player
        players = init_player(3, deck);
        while(!gameOver)
        {

            Rounds round = new Rounds(players, deck);
            for(Poker_player player: players)
            {
                player.stopOrNot();

            }
            
            players = setRemainingPlayers();
            if (players.length<2) {
                System.out.println("Only one player left. \nEnd of the game.");
                System.out.println("Player "+players[0].getPlayerId()+"finish the game with "+players[0].getToken()+"$");
                gameOver = true; 
            }
            

        }
        
     
   
        
        

        
        
        
        //System.out.println(deck.getcards().length); 
        
        
        
        //Creation of a JFrame to display the card
        /**JFrame window = new JFrame("POKER GAME"); 
        window.setSize(500, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        
        //Creation of a Panel.
        JPanel contentPane = new JPanel(new BorderLayout());
        
        
        
        //Creation of a JLabel, kind of a sticky note to write on. 
        
        JLabel cardLabel = new JLabel();
        cardLabel.setHorizontalAlignment(JLabel.CENTER);
        cardLabel.setSize(300,400);
        
        ImageIcon CardImg = new ImageIcon(deck.getCardWithIndex(25).getCardImg().getScaledInstance(150, 200, Image.SCALE_DEFAULT)); // Change the index of 0<=deck.getCardWithIndex()<=51 to get the corresponding image.
        cardLabel.setIcon(CardImg);
        
        contentPane.add(cardLabel); 
        window.add(contentPane);
        window.setVisible(true);**/
        
        
        
        
    }
    
    public static Poker_player[] init_player(int x,DeckOfCards deck)
    {
        Poker_player players[] = new Poker_player[x];
        for (int i = 0; i < x ; i++) {
            players[i] = new Poker_player(i+1, new Poker_hand(deck, 5));
        }
        
        return players;
    }
    
    public static Poker_player[] setRemainingPlayers()
    {
       List<Poker_player> remainingPlayersList = new ArrayList<>();
       
       for(Poker_player player: players)
       {
           if (player.getState()) {
               remainingPlayersList.add(player);
           }
       }
       
       Poker_player[] remainingPlayer = new Poker_player[remainingPlayersList.size()]; 
        for (int i = 0; i < remainingPlayer.length; i++) {
            remainingPlayer[i] = remainingPlayersList.get(i);
        }
        
        return remainingPlayer; 
    }
}
