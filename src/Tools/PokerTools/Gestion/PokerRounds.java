/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools.PokerTools.Gestion;


import Tools.PokerTools.Player.Poker_player;
import Tools.Cards.DeckOfCards;
import Tools.Cards.Poker_hand;
import com.sun.istack.internal.NotNull;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author quent
 */
public class PokerRounds {
    
    private static int total_stake = 0;  
    private static int minimum_cave = 20; 
    
    private Poker_player winner ; 
    private boolean equality = false; 


 
    
    public PokerRounds(Poker_player[] players , DeckOfCards deck)
    {
        
        for (Poker_player player:players) {
            System.out.println("Current stake: "+ total_stake);
            player.describe_player_state();
            int action = player.ChoseMove();
            player.LaunchAction(action);
        }
        
        if (getPlayingPLayersAmount(players) > 0 ) {

            players = setPlayingPlayers(players);
            getRoundWinner(players);


            if (!this.equality) {
                this.winner.receiveMoney();
                System.out.println("\n\nLe vaincqueur du round est: Player"+winner.getPlayerId());
                System.out.println("\n--> Argent du vaincqueur:" +winner.getToken() +"$");
            }
            else{
                System.out.println("\n\n This round had two players equal, the money is split between them.");
            }

        this.total_stake = 0; 
        this.minimum_cave = 0; 
        
        }
        else
        {
            System.out.println("\n\nNo winner detected for this round.");

        }
    }
    
    
    
    
    public static void setMinimumCave(int x)
    {
        PokerRounds.minimum_cave = x; 
    }
    
    public static int getMinimumCave()
    {
        return PokerRounds.minimum_cave; 
    }
    
    public static void increaseTotalStake(int x)
    {
        PokerRounds.total_stake += x; 
    }
    
     public static int getStake()
    {
        return PokerRounds.total_stake; 
    }
    

     /**
      * 
      * @param players
      * @return the amount of players who didn't fold on this round.
      */
    public int getPlayingPLayersAmount(Poker_player[] players)
    {
        int counter = 0; 
        for (int i = 0; i <players.length; i++) {
            if (players[i].getRightToContinue()) {
                counter ++;
            }
        }
        return counter;
    }
    
    /**
     * 
     * @param players
     * @return a list of the players who didn't Fold on this round. 
     */
    public Poker_player[] setPlayingPlayers(Poker_player[] players){
        
        Poker_player[] playingPlayers = new Poker_player[getPlayingPLayersAmount(players)]; 

        int index = 0; 
        for(Poker_player player:players)
        {
            if (player.getRightToContinue()) {
                playingPlayers[index] = player; 
                index ++; 
            }
        }
        
        return playingPlayers; 
        
    }
    
    /**
     * Verify who got the best hand in the current round. 
     * It first verifies who got the best basics hand. 
     * If two types of hand are similar, the one with the greatest value will be the winner.
     * If the two hands have the same card, the high card will determine the winner. 
     * Finally, if the high card is similar for both player, this round ends up with equality.
     * @param players 
     */
    public void getRoundWinner(Poker_player[] players)
    {
        
       Poker_player[] playing = setPlayingPlayers(players);
        this.winner = players[0]; 
        for(Poker_player player : players)
        {
            if (player.getHand().getHandStrength()[0] > winner.getHand().getHandStrength()[0]) {
                
                this.winner = player; 
            }
            
        } 
        
        for(Poker_player player : players)
        {
            if (player.getHand().getHandStrength()[0] == winner.getHand().getHandStrength()[0]) {
                
                if (player.getHand().getHandStrength()[1] > winner.getHand().getHandStrength()[1]) {
                
                    this.winner = player; 
                }
                
                else if(player.getHand().getHandStrength()[1] == winner.getHand().getHandStrength()[1]){
                     if (player.getHand().getStrongest() > winner.getHand().getStrongest()) {
                        
                         this.winner = player; 
                         System.out.println("Check of the high card.");
                    }
                }
                //If finally, all in all, both hands are as strong as the other. 
                else{
                    this.equality = true; 
                    winner.receiveMoneyEquality();
                    player.receiveMoneyEquality();
                }
                
            }
            
        } 
        
        
        
    }
    
    public boolean getEquality()
    {
        return this.equality; 
    }
    
    
   
    
}
