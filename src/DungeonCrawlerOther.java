/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sasha
 */


import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class DungeonCrawlerOther extends JFrame {

    public DungeonCrawlerOther() {       
        initUI();
    }
    
    private void initUI() {   
        try
        {
      GameScreen myScreen = new GameScreen();
            setSize(640, 340);
        add(myScreen);
            }
		catch(Exception e) {
			e.printStackTrace();
		}

        
        setResizable(true);
        
      
        setTitle("Dungeon Crawler Other");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DungeonCrawlerOther game = new DungeonCrawlerOther();
            game.setVisible(true);
        });
    }
}