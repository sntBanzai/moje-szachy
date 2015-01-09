/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.image.BufferedImage;

/**
 * Klasa abstrakcyjna stanowiąca podstawę do tworzenia klas reprezentujące
 * konkretne typy pionów szachowych.
 * @author Jerzy Małyszko
 */
abstract class Figure {
    /**
     * Statyczny obiekt String zawierający ścieżkę względną do katalogu, w którym
     * znajdują się pliki .jpg dla poszczególnych pionów.
     */
    static String Path = "src\\main\\resources\\images\\";
    /**
     * Pole zawierające obiekt graficzny reprezentujący na szachownicy konkretny pion.
     * Polu przydzielana jest wartość w klasie dziedziczącej.
     */
    BufferedImage ikona;
    /**
     * Instancja enumeracji Teams zagnieżdżonej w klasie Team. Reprezentuje drużynę
     * (czarnych bądź białych), w skład której wchodzi pionek.
     */
    Team.Teams team;
    /**
     * Wartość boolowska wskazująca czy pion znajduje się na szachownicy, czy też
     * został już zbity. Do wykorzystania w dalszej implementacji.
     */
    boolean onBoard;
    /**
     * Wskazuje informację jakie pole szachownicy (jeśli w ogóle) zajmuje dany pion.
     */
    Pole occupiedField;
    
    /**
     * Bezparametrowy konstruktor automaycznie ustanawiający wartość {@link #onBoard}
     * jako prawdziwą(przy rozpoczynaniu gry}. 
     */
    Figure(){
        onBoard = true;
    }
    
    /**
     * Metoda symulująca postawienie piona na konkretnym polu.
     * @param p Pole, które zajmować będzie od teraz pion, na rzecz którego wykonano
     * tą metodę.
     */
    void deployOnField(Pole p){
        if(!isOnBoard()){
            System.out.println("Pion nie znajduje się już na szachownicy!");
            return;
        }
        p.setOccupied(true);
        p.setFigura(this);  
    }
    /**
     * Metoda symulująca opuszczenie pola przez konkretny pion
     * @param p Pole, które zostaje opuszczone przez pion, na rzecz którego
     * wykonana została metoda.
     */
    void releaseField(Pole p){
        if(!isOnBoard()){
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
        return "Figure{" + "team=" + team + ", onBoard=" + onBoard + ", occupiedField=" + occupiedField + '}';
    }

    
    
    
    
    
    
}
