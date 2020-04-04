/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Cards.DeckOfCards;
import Cards.Poker_hand;
import Gestion.Rounds;
import java.util.Scanner;

/**
 *
 * @author quent
 */
public class Poker_player {
    
    private int id;
    private Poker_hand pkr_hand; 
    private int token; 
    
    private boolean allowed_to_check = true; 
    private boolean allowed_to_continue = true; 
    private boolean playing; 
    
    
    
    public Poker_player(int id, Poker_hand pk_h){
        this.id = id; 

        this.pkr_hand = pk_h; 
        this.token = 150; 
    }
    
    
    
    public Poker_hand getHand()
    {
        return this.pkr_hand; 
    }
    
    public void setNewHand(DeckOfCards deck)
    {
        this.pkr_hand = new Poker_hand(deck, 5);
    }
    
    public int getPlayerId()
    {
        return this.id; 
    }
    /**
     * Get an input. 
     * Check the validity of the input. 
     * Change the raise if the input is Valid. 
     */
    public void raise()
    {
      
        Scanner scanner = new Scanner(System.in);
        int minimumBet = Rounds.getMinimumCave()+1; 
        int number; 
        
        do {
            System.out.println("---> Enter the amount you want to raise the bet to.\n---> The actual cave is fixed at: "+ Rounds.getMinimumCave());
            System.out.println("---> The minimum needed for a raise is " + minimumBet +"$.");
            while (!scanner.hasNextInt()){
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number.\n", input);
            }
            number = scanner.nextInt(); 
            if (number > this.token) {
                System.out.println("You don't have enough token to raise the stake that much.\n Your actual amount of token is "+this.token);
                number = 0;               
            }
        } while (number < Rounds.getMinimumCave()+1);
        
        
        this.token -= number; 
        Rounds.setMinimumCave(number);
        Rounds.increaseTotalStake(number);
       
        System.out.println("La nouvelle valeur de la cave est de: "+Rounds.getMinimumCave() +" $");
        System.out.println("---> Votre argent: "+ this.token+" $");
        
        
    }
    
    /**
     * 
     */
    public void follow()
    {
        if (this.token >= Rounds.getMinimumCave()) {
            this.token -= Rounds.getMinimumCave();
            Rounds.increaseTotalStake(Rounds.getMinimumCave());
            System.out.println("---> Votre argent: "+ this.token+" $");
        } 
        
        
        
    }
    
    public void fold()
    {
        this.allowed_to_continue = false; 
    }
    
    public boolean getRightToContinue()
    {
        return this.allowed_to_continue; 
    }
    
    public void check()
    {
        
    }
    
    public void describe_player_state()
    {
        System.out.println("\n\nPlayer: " +this.id + "  Money: "+ this.token +"$  \nCURRENT MINIMUM BET ----> "+Rounds.getMinimumCave()+"\n");
        getHand().dislayHand();
        getHand().checkHandPossibilities();
        getHand().displayTypeOfHand();
       
    }
    
    public int ChoseMove()
    {
        
        Scanner scanner = new Scanner(System.in);
        int[] possible_answer = {1,2,3,4};
        int number = 0; 
        
        System.out.println("\nChoose an option:\n1-Raise\n2-Follow\n3-Fold\n4-check");
        while (!scanner.hasNextInt()){
            String input = scanner.next();
            System.out.printf("\"%s\" is not a valid number.\n", input);
        }
        
        number = scanner.nextInt(); 
        
        for (int elem:possible_answer) {
            if (number == elem) {
                return number;
            }
            
        }
        System.out.println(number + " is not a valid choice. Please enter a value between 1 & 4.");
        return ChoseMove();
           
    }
    
    
     public void LaunchAction(int x)
    {
        switch(x) {
            case 1:
              this.raise();
              break;
            case 2:
              this.follow();
              break;
            case 3: 
                this.fold();
                break; 
            case 4: 
                this.check();

        }
  
    }
     
     
     public void getMoney()
     {
         this.token += Rounds.getStake();
     }
     
     public void getMoneyEquality()
     {
         this.token += Rounds.getStake()/2;
     }
    
     public void stopOrNot()
     {
         String input; 
         do{
            System.out.println("Player "+this.id+ " do you want to continue?\n (y) for yes (n) for no. ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.next();
        }
        while(input != "y" || input != "n");
         
         if(input == "n")
         {
             this.playing = false; 
         }
         
     }
     
     public boolean getState()
     {
         return this.playing; 
     }
    
   
    
}
