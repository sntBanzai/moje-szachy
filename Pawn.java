/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author X870
 */
final class Pawn extends Figure {
    
    boolean isFirstMove = true;

    Pawn(Team.Teams team) {
        this.team = team;
        File f1 = new File(Path+"whitePawn.jpg");
        File f2 = new File(Path+"blackPawn.jpg");
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(f1);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku (pionek)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(f2);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku (pionek)");
            }
        }
    }

    @Override
    HashSet<Pole> establishAvailableMoves() {
        HashSet<Pole> returnVal = new HashSet<>();
        int range = 1;
        if(isFirstMove) range = 2;
        switch(this.getTeam()){
            case Biali:
                Pole ahead = null;
                if(this.getOccupiedField().getyCoo()==maxY){
                    ahead = szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()+range);
                    if(!ahead.isOccupied()) returnVal.add(ahead);
                }
                Pole cross = null;
                if(!(this.getOccupiedField().getxCoo()==0)){
                    cross = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()+1);
                    if(canMove(cross)) returnVal.add(cross);
                }
                if(!(this.getOccupiedField().getxCoo()==maxX)){
                    cross = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()+1);
                    if(canMove(cross)) returnVal.add(cross);
                }
                break;
            case Czarni:
                Pole ahead2 = null;
                if(!(this.getOccupiedField().getyCoo()==0)){
                    ahead2 = szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()-range);
                    if(!ahead2.isOccupied()) returnVal.add(ahead2);
                }
                Pole cross2 = null;
                if(!(this.getOccupiedField().getxCoo()==0)){
                    cross2 = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()-1);
                    if(canMove(cross2)) returnVal.add(cross2);
                }
                if(!(this.getOccupiedField().getxCoo()==maxX)){
                    cross2 = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()-1);
                    if(canMove(cross2)) returnVal.add(cross2);
                }
                break;
        }
        return returnVal;
    }
    
    private boolean canMove(Pole p){
        return p.isOccupied()&&(p.getFigura().getTeam()==establishOpposite());
    }
    
}
