/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author X870
 */
public class Pole extends JLabel {
    
    
    final char litera;
    final char liczba;
    boolean occupied;
    Figure figura;
    Color tlo;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    
    
    Pole(char liczba, char litera, Color tlo){
        this.litera = litera;
        this.liczba = liczba;
        setMaximumSize(new Dimension(110, 110));
        setPreferredSize(new Dimension(110, 110));
        setMinimumSize(new Dimension(110, 110));
        this.tlo = tlo;
    }

    public Figure getFigura() {
        return figura;
    }

    public void setFigura(Figure figura) {
        this.figura = figura;
    }
    
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    public char getLitera() {
        return litera;
    }

    public char getLiczba() {
        return liczba;
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        rysujTloPola(g2d);
        g2d.setComposite(ac);
        rysujFigurę(g2d);
    }
    
    private void rysujFigurę(Graphics2D g2d){
        if(figura!=null){
            g2d.drawImage(figura.getIkona(), 10, 10, null);
        }
    }
    
    private void rysujTloPola(Graphics2D g2d){
        g2d.setColor(tlo);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
    }

    @Override
    public String toString() {
        return "Pole{" + "litera= " + litera + ", liczba= " + liczba + ", tlo= " + tlo + '}';
    }
    
}
