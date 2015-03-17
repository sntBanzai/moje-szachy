/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.Graphics2D;
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
final class Knight extends Figure {
    
    Knight(Team.Teams team) {
        this.team = team;
        this.rank = 2;
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(getClass().getResource("/images/whiteKnight.jpg"));
                BufferedImage transit = new BufferedImage(Figure.getFigureWidth(), Figure.getFigureHeight(), BufferedImage.SCALE_SMOOTH);
                Graphics2D g = (Graphics2D) transit.getGraphics();
                g.drawImage(ikona, 0, 0, Figure.getFigureWidth(), Figure.getFigureHeight(), null);
                ikona = transit;
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd przy odczycie pliku obrazka (koń)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(getClass().getResource("/images/blackKnight.jpg"));
                BufferedImage transit = new BufferedImage(Figure.getFigureWidth(), Figure.getFigureHeight(), BufferedImage.SCALE_SMOOTH);
                Graphics2D g = (Graphics2D) transit.getGraphics();
                g.drawImage(ikona, 0, 0, Figure.getFigureWidth(), Figure.getFigureHeight(), null);
                ikona = transit;
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
        if((this.getOccupiedField().getxCoo()+2<=maxX)&&(this.getOccupiedField().getyCoo()!=0)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+2, this.getOccupiedField().getyCoo()-1);
            if(canJump(jump)) retVal.add(jump);
        }
        if((this.getOccupiedField().getxCoo()+2<=maxX)&&(this.getOccupiedField().getyCoo()!=maxY)){
            jump = szachownica.seekField(this.getOccupiedField().getxCoo()+2, this.getOccupiedField().getyCoo()+1);
            if(canJump(jump)) retVal.add(jump);
        }
        
        return retVal;
    }

     private boolean canJump(Pole jump) {
        return !jump.isOccupied()||jump.getFigura().getTeam()==establishOpposite();
    }
    
}
