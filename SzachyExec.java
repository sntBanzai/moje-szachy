/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author X870
 */
public class SzachyExec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame main = new JFrame("JurekSzachy");
                main.add(new Szachownica());
                main.setVisible(true);
                main.setResizable(false);
                main.pack();
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
    
}
