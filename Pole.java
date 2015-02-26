/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author X870
 */
public class Pole extends JLabel {

    private final char litera;
    private final char liczba;
    private final int xCoo;
    private final int yCoo;
    boolean occupied;
    Figure figura;
    Color tlo;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    String initialToolTip;
    boolean shouldIClearBorder = true;

    Pole(char liczba, char litera, Color tlo, int xCoo, int yCoo) {
        this.litera = litera;
        this.liczba = liczba;
        this.xCoo = xCoo;
        this.yCoo = yCoo;
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
        if (this.figura != null) {
            this.setToolTipText("<html>" + getToolTipText() + "<br/>" + this.getFigura().getClass().getSimpleName() + " " + this.getFigura().getTeam().name() + "</html>");
        } else {
            this.setToolTipText(initialToolTip);
        }

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

    public int getxCoo() {
        return xCoo;
    }

    public int getyCoo() {
        return yCoo;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        rysujTloPola(g2d);
        g2d.setComposite(ac);
        drawFigure(g2d);
    }

    private void drawFigure(Graphics2D g2d) {
        if (figura != null) {
            g2d.drawImage(figura.getIkona(), 10, 10, null);
        }
    }

    private void rysujTloPola(Graphics2D g2d) {
        g2d.setColor(tlo);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    }

    @Override
    public String toString() {
        return "Pole " + litera + " " + liczba;
    }

    public boolean shouldIClearBorder() {
        return shouldIClearBorder;
    }

    public void setShouldIClearBorder(boolean shouldIClearBorder) {
        this.shouldIClearBorder = shouldIClearBorder;
    }

    void removeActualFigure() {
        if (this.getFigura().getTeam() == Team.Teams.Biali) {
            Pole p = SzachyExec.szachownica.getZbiteBiale().pollLast();
            this.getFigura().deployOnField(p);
            p.repaint();
        }
        else{
            Pole p = SzachyExec.szachownica.getZbiteCzarne().poll();
            this.getFigura().deployOnField(p);
            p.repaint();
        }
    }

}
