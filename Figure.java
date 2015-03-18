/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Klasa abstrakcyjna stanowiąca podstawę do tworzenia klas reprezentujące
 * konkretne typy pionów szachowych.
 *
 * @author Jerzy Małyszko
 */
abstract class Figure {

    /**
     * Pole zawierające obiekt graficzny reprezentujący na szachownicy konkretny
     * pion. Polu przydzielana jest wartość w klasie dziedziczącej.
     */
    BufferedImage ikona;
    /**
     * Instancja enumeracji Teams zagnieżdżonej w klasie Team. Reprezentuje
     * drużynę (czarnych bądź białych), w skład której wchodzi pionek.
     */
    Team.Teams team;
    /**
     * Wartość boolowska wskazująca czy pion znajduje się na szachownicy, czy
     * też został już zbity. Do wykorzystania w dalszej implementacji.
     */

    boolean onBoard;
    /**
     * Wskazuje informację jakie pole szachownicy (jeśli w ogóle) zajmuje dany
     * pion.
     */
    Pole occupiedField;

    HashSet<Pole> availableMoves;

    static Szachownica szachownica = SzachyExec.szachownica;

    static int maxX = szachownica.fieldSet[0].length - 1;
    static int maxY = szachownica.fieldSet.length - 1;
    int rank;
    
    private static int figureWidth = establishFigureWidth();
    private static int figureHeight = establishFigureHeight();

    /**
     * Bezparametrowy konstruktor automaycznie ustanawiający wartość
     * {@link #onBoard} jako prawdziwą(przy rozpoczynaniu gry}.
     */
    Figure() {
        onBoard = true;
    }

    /**
     * Metoda symulująca postawienie piona na konkretnym polu.
     *
     * @param p Pole, które zajmować będzie od teraz pion, na rzecz którego
     * wykonano tą metodę.
     */
    void deployOnField(Pole p) {
        if (!isOnBoard()) {
            System.out.println("Pion nie znajduje się już na szachownicy!");
            return;
        }
        p.setOccupied(true);
        p.setFigura(this);
        this.setOccupiedField(p);
    }

    /**
     * Metoda symulująca opuszczenie pola przez konkretny pion
     *
     * @param p Pole, które zostaje opuszczone przez pion, na rzecz którego
     * wykonana została metoda.
     */
    void releaseField(Pole p) {
        if (!isOnBoard()) {
            System.out.println("Pion nie znajduje się już na szachownicy!");
            return;
        }
        p.setOccupied(false);
        p.setFigura(null);

    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    public Pole getOccupiedField() {
        return occupiedField;
    }

    public void setOccupiedField(Pole occupied) {
        this.occupiedField = occupied;
    }

    BufferedImage getIkona() {
        return ikona;
    }

    void setIkona(BufferedImage ikona) {
        this.ikona = ikona;
    }

    Team.Teams getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "" + this.getClass().getSimpleName();
    }

    abstract HashSet<Pole> establishAvailableMoves();

    Team.Teams establishOpposite() {
        Team.Teams retVal = null;
        for (Team.Teams t : Team.Teams.values()) {
            if (t == this.getTeam()) {
                continue;
            }
            retVal = t;
        }
        return retVal;
    }

    public int getRank() {
        return rank;
    }

    public static int getFigureWidth() {
        return figureWidth;
    }

    public static int getFigureHeight() {
        return figureHeight;
    }
    
    private static int establishFigureWidth(){
        int w = SzachyExec.szachownica.fieldSet[0][0].getWidth();
        return (int) (w*0.85);
    }
    
    private static int establishFigureHeight(){
        int w = SzachyExec.szachownica.fieldSet[0][0].getHeight();
        return (int) (w*0.85);
    }

    protected void adjustImage() {
        BufferedImage transit = new BufferedImage(Figure.getFigureWidth(), Figure.getFigureHeight(), BufferedImage.SCALE_SMOOTH);
        Graphics2D g = (Graphics2D) transit.getGraphics();
        g.drawImage(ikona, 0, 0, Figure.getFigureWidth(), Figure.getFigureHeight(), null);
        ikona = transit;
    }

}
