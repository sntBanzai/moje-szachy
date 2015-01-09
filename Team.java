/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

/**
 *
 * @author Jerzy Malyszko
 */
 class Team{
    Teams name;
    Pawn[] pionki = new Pawn[8];
    Bishop[] gonce = new Bishop[2];
    Knight[] konie = new Knight[2];
    Rook[] wieze = new Rook[2];
    Queen hetman;
    King krol;

    Team(Teams name){
        this.name = name;
        for (int i = 0; i < pionki.length; i++) {
            pionki[i] = new Pawn(name);
        }
        for (int i = 0; i < gonce.length; i++) {
            gonce[i] = new Bishop(name);
        }
        for (int i = 0; i < konie.length; i++) {
            konie[i] = new Knight(name);
        }
        for (int i = 0; i < wieze.length; i++) {
            wieze[i] = new Rook(name);
        }
        hetman = new Queen(name);
        krol = new King(name);
    }
    
    public Teams getName() {
        return name;
    }
    
    enum Teams {
        Biali, Czarni
    }
}
