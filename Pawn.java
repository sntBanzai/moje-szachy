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
        this.rank = 1;
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(getClass().getResource("/images/whitePawn.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku (pionek)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(getClass().getResource("/images/blackPawn.jpg"));
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
        switch(this.getTeam()){
            case Biali:
                System.out.println("biali");
                Pole ahead = null;
                if(this.getOccupiedField().getxCoo()!=maxX){
                    if(isFirstMove){ 
                        ahead = szachownica.seekField(this.getOccupiedField().getxCoo()+2, this.getOccupiedField().getyCoo());
                        if(!ahead.isOccupied()) returnVal.add(ahead);
                    }
                    ahead = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo());
                    System.out.println(ahead);
                    if(!ahead.isOccupied()) returnVal.add(ahead);
                }
                Pole cross = null;
                if((this.getOccupiedField().getxCoo()!=maxX)&&(this.getOccupiedField().getyCoo()!=0)){
                    cross = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()-1);
                    if(canMove(cross)) returnVal.add(cross);
                }
                if((this.getOccupiedField().getxCoo()!=maxX)&&(this.getOccupiedField().getyCoo()!=maxY)){
                    cross = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()+1);
                    if(canMove(cross)) returnVal.add(cross);
                }
                break;
            case Czarni:
                Pole ahead2 = null;
                if(this.getOccupiedField().getxCoo()!=0){
                    if(isFirstMove){ 
                        ahead2 = szachownica.seekField(this.getOccupiedField().getxCoo()-2, this.getOccupiedField().getyCoo());
                        if(!ahead2.isOccupied()) returnVal.add(ahead2);
                    }
                    ahead2 = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo());
                    if(!ahead2.isOccupied()) returnVal.add(ahead2);
                }
                Pole cross2 = null;
                if(this.getOccupiedField().getxCoo()!=0&&(this.getOccupiedField().getyCoo()!=0)){
                    cross2 = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()-1);
                    if(canMove(cross2)) returnVal.add(cross2);
                }
                if(this.getOccupiedField().getxCoo()!=0&&(this.getOccupiedField().getyCoo()!=maxY)){
                    cross2 = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()+1);
                    if(canMove(cross2)) returnVal.add(cross2);
                }
                break;
        }
        System.out.println(returnVal);
        return returnVal;
    }
    
    private boolean canMove(Pole p){
        return p.isOccupied()&&(p.getFigura().getTeam()==establishOpposite());
    }
    
    @Override
    void releaseField(Pole p){
        if(!isOnBoard()){
            System.out.println("Pion nie znajduje się już na szachownicy!");
            return;
        }
        p.setOccupied(false);
        p.setFigura(null);
        if(isFirstMove) this.isFirstMove = false;
    }

}
