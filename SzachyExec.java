/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jerzy Małyszko
 */
public class SzachyExec {

    /**
     * @param args the command line arguments
     */
    static Szachownica szachownica;
    static Game actualGame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame main = new JFrame("JurekSzachy");
                main.setLayout(new BorderLayout());
                JMenuBar menu = new JMenuBar();
                JMenu program = new JMenu("Program");
                JMenuItem graj = new JMenuItem("Graj!");
                graj.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent ae){
                        actualGame= new Game();
                        actualGame.turn(Team.Teams.Biali);
                   }
                   });
                program.add(graj);
                menu.add(program);
                main.add(menu, BorderLayout.PAGE_START);
                szachownica = new Szachownica();
                main.add(szachownica, BorderLayout.CENTER);
                main.setVisible(true);
                main.setResizable(false);
                main.pack();
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
   
}
