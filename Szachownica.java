/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author X870
 */
public class Szachownica extends JPanel {
    
    static Color[] kolory = {Color.LIGHT_GRAY, Color.GRAY};
    static char[] litery = {'a','b','c','d','e','f','g','h'};
    static char[] liczby = {'1','2','3','4','5','6','7','8'};
    final Pole[][] fieldSet = new Pole[8][8];
    boolean flaga= true;
    
    public Szachownica(){
        setLayout(new BorderLayout());
        JPanel inner = new JPanel();
        JPanel outerN = new JPanel();
        JPanel outerE = new JPanel();
        JPanel outerS = new JPanel();
        JPanel outerW = new JPanel();
        final ArrayList<JPanel> sides = new ArrayList<>();
        sides.add(outerE); sides.add(outerW); sides.add(outerN); sides.add(outerS);
        for(JPanel bok:sides){
            Container aux = new Container();
            for (int i = 0; i < 8; i++) {
                JLabel inCreation = new JLabel();
                if(bok==outerE||bok==outerW){
                    inCreation.setPreferredSize(new Dimension(50, 110));
                    inCreation.setText("       "+String.valueOf(liczby[i]));
                    bok.add(inCreation);
                }
                else{
                    inCreation.setPreferredSize(new Dimension(110, 50));
                    if(i<2){
                       inCreation.setText("              "+String.valueOf(litery[i])); 
                    }
                    else if(i<4){
                        inCreation.setText("                "+String.valueOf(litery[i]));
                    }
                    else if(i<6){
                        inCreation.setText("                  "+String.valueOf(litery[i]));
                    }
                    else{
                        inCreation.setText("                     "+String.valueOf(litery[i]));
                    }
                    aux.add(inCreation); 
                }
            }
           if(bok==outerE||bok==outerW){
                bok.setLayout(new GridLayout(0, 1));
                }
            else{
                aux.setLayout(new GridLayout(1, 0));
                JLabel blank = new JLabel();
                JLabel blank2 = new JLabel();
                blank.setPreferredSize(new Dimension(50,50));
                blank2.setPreferredSize(new Dimension(50,50));
                bok.add(blank, BorderLayout.EAST);
                bok.add(aux, BorderLayout.CENTER);
                bok.add(blank2, BorderLayout.WEST);
            }
        }
        this.add(inner, BorderLayout.CENTER);
        this.add(outerN, BorderLayout.NORTH);
        this.add(outerE, BorderLayout.EAST);
        this.add(outerS, BorderLayout.SOUTH);
        this.add(outerW, BorderLayout.WEST);
        inner.setLayout(new GridLayout(8,8));
        Dimension dim = new Dimension(880,880);
        inner.setPreferredSize(dim);
        inner.setMaximumSize(dim);
        inner.setMinimumSize(dim);
        for (int i = 0; i < fieldSet.length; i++) {
            for (int j = fieldSet[i].length-1; j >= 0; j--) {
                fieldSet[i][j] = new Pole(litery[i], liczby[j], nadajPoluKolor(i));
                inner.add(fieldSet[i][j]);
            }
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
        
    
    public Color nadajPoluKolor(int index){
        Color toReturn = null;
        if(index==0||index==2||index==4||index==6){
            System.out.println("Jestem tu");
            if(flaga){
                toReturn = kolory[0];
                flaga = false;
            }
            else{
                toReturn = kolory[1];
                flaga = true;
            }
        }
        else{
            System.out.println("a tero tu");
           if(flaga){
                toReturn = kolory[1];
                flaga = false;
            }
            else{
                toReturn = kolory[0];
                flaga = true;
            }
        }
        return toReturn;
    }

    
    
}
