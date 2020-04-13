package Tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quent
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window  extends JFrame{
    
    public Window(String title){
        this.setTitle(title);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        
        
        //Instanciation d'un objet JPanel
        JPanel pan = new JPanel();
        //Définition de sa couleur de fond
        pan.setBackground(Color.ORANGE);        
        //On prévient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(pan);               
        this.setVisible(true);
    }
 
  


    
}
