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
class King extends Figure {
    
    private HashSet<Pole> retVal;

    King(Team.Teams team) {
        this.team = team;
        this.rank = 10;
        File f1 = new File(Path+"whiteKing.jpg");
        File f2 = new File(Path+"blackKing.jpg");
        if(team==Team.Teams.Biali){
            try {
                ikona = ImageIO.read(f1);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("BĹ‚Ä…d przy odczycie pliku obrazka (krĂłl)");
            }
        }
        else if(team==Team.Teams.Czarni){
            try {
                ikona = ImageIO.read(f2);
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("BĹ‚Ä…d przy odczycie pliku obrazka (krĂłl)");
            }
        }
    }

    @Override
    HashSet<Pole> establishAvailableMoves() {
        retVal= new HashSet<>();
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()+1));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()-1));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()-1));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()+1));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()+1, this.getOccupiedField().getyCoo()));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo()-1, this.getOccupiedField().getyCoo()));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()-1));
        checkMoveAvailability(szachownica.seekField(this.getOccupiedField().getxCoo(), this.getOccupiedField().getyCoo()+1));
        return retVal;
    }
    
    void checkMoveAvailability(Pole p){
        int xTraversal = this.getOccupiedField().getxCoo() - p.getxCoo();
        int yTraversal = this.getOccupiedField().getyCoo() - p.getyCoo();
        if(!p.isOccupied()){
            retVal.add(p);
        }
        else if(p.isOccupied()&&p.getFigura().getTeam()==establishOpposite()){
            retVal.add(p);
        }
    }
}
