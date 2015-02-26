/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

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
class Queen extends Figure {
    
    private Pole auxiliary;
    private HashSet<Pole> retVal;
    
    Queen(Team.Teams team) {
        this.team = team;
        this.rank = 3;
        File f1 = new File(Path+"whiteQueen.jpg");
        File f2 = new File(Path+"blackQueen.jpg");
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(f1);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku obrazka (hetman)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(f2);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku obrazka (hetman)");
            }
        }
    }

    @Override
    HashSet<Pole> establishAvailableMoves() {
        retVal = new HashSet<>();
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()+1<=maxX){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()-1>=0){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getyCoo()+1<=maxY){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()+1));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getyCoo()-1>=0){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()-1));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()+1<=maxX&&this.getOccupiedField().getyCoo()+1<=maxY){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()+1));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()-1>=0&&this.getOccupiedField().getyCoo()-1>=0){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()-1));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()+1<=maxX&&this.getOccupiedField().getyCoo()-1>=0){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()-1));
        }
        auxiliary = this.getOccupiedField();
        if(this.getOccupiedField().getxCoo()-1>=0&&this.getOccupiedField().getyCoo()+1<maxY){
            checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()+1));
        }
        return retVal;
    }
    
    void checkMoveAvailability(Pole p){
         int xTraversal = p.getxCoo() - auxiliary.getxCoo();
        int yTraversal = p.getyCoo() - auxiliary.getyCoo();
        if(!p.isOccupied()){
            retVal.add(p);
            auxiliary = p;
            if((xTraversal>0&&p.getxCoo()==maxX)||(yTraversal>0&&p.getyCoo()==maxY)||(xTraversal<0&&p.getxCoo()==0)||(yTraversal<0&&p.getyCoo()==0)){
                return;
            }
            else {
                checkMoveAvailability(szachownica.seekField(p.getxCoo()+xTraversal, p.getyCoo()+yTraversal)); 
            }
        }
        else if(p.isOccupied()&&p.getFigura().getTeam()==establishOpposite()){
            retVal.add(p);
        }
    }
    
}
