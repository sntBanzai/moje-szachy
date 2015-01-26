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
final class Knight extends Figure {
    
    Knight(Team.Teams team) {
        this.team = team;
        File f1 = new File(Path+"whiteKnight.jpg");
        File f2 = new File(Path+"blackKnight.jpg");
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(f1);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku obrazka (koń)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(f2);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku obrazka (koń)");
            }
        }
    }

    @Override
    HashSet<Pole> establishAvailableMoves() {
        HashSet<Pole> retVal = new HashSet<>();
        Pole jump = null;
        if((this.getOccupiedField().getyCoo()-2>=0)&&(this.getOccupiedField().getxCoo()!=0)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()-2);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getyCoo()-2>=0)&&(this.getOccupiedField().getxCoo()!=maxX)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()-2);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getyCoo()+2<=maxY)&&(this.getOccupiedField().getxCoo()!=0)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()+2);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getyCoo()+2<=maxY)&&(this.getOccupiedField().getxCoo()!=maxX)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()+2);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getxCoo()-2>=0)&&(this.getOccupiedField().getyCoo()!=0)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()-2, this.getOccupiedField().getyCoo()-1);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getxCoo()-2>=0)&&(this.getOccupiedField().getyCoo()!=maxY)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()-2, this.getOccupiedField().getyCoo()+1);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getxCoo()+2<=maxY)&&(this.getOccupiedField().getyCoo()!=0)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+2, this.getOccupiedField().getyCoo()-1);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getyCoo()+2<=maxY)&&(this.getOccupiedField().getxCoo()!=maxX)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+2, this.getOccupiedField().getyCoo()+1);
            if(canJump(jump)) retVal.add(jump);
        }
        
        return retVal;
    }

     private boolean canJump(Pole jump) {
        return !jump.isOccupied()||jump.getFigura().getTeam()==establishOpposite();
    }
    
}
