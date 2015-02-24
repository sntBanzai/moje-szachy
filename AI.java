/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import static com.mycompany.szachy.Game.szachownica;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author X870
 */
public class AI {

    static Szachownica szach = SzachyExec.szachownica;
    private Team.Teams controlled;
    private Game g;

    public AI(Team.Teams controlled, Game g) {
        this.controlled = controlled;
        this.g = g;
    }

    void doTurn() {
        Pole chosen = null;
        Pole destination = null;
        for (Pole p : szachownica.getFieldsOccupiedBy(this.getControlled())) {
            for (Pole pp : p.getFigura().establishAvailableMoves()) {
                if (pp.isOccupied() && pp.getFigura().getTeam() != controlled) {
                    if (destination != null) {
                        if (destination.getFigura().getRank() < pp.getFigura().getRank()) {
                            destination = pp;
                            chosen = p;
                        }
                        else{
                            destination = pp;
                            chosen = p;
                        }
                    }
                }
            }
        }
        if (chosen == null) {
            ArrayList<Pole> transformed = new ArrayList(szachownica.getFieldsOccupiedBy(this.getControlled()));
            do {
                chosen = transformed.get(new Random().nextInt(transformed.size()));
            } while (chosen.getFigura().establishAvailableMoves().size() < 1);
            transformed = new ArrayList<>(chosen.getFigura().establishAvailableMoves());
            destination = transformed.get(new Random().nextInt(transformed.size()));
        }
        chosen.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        if(destination.isOccupied()){
            destination.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        }
        else{
            destination.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        }
        chosen.repaint();
        destination.repaint();
//        try {
//            TimeUnit.SECONDS.sleep(3L);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        chosen.setBorder(null);
        destination.setBorder(null);
        chosen.getFigura().deployOnField(destination);
        chosen.getFigura().releaseField(chosen);
        chosen.repaint();
        destination.repaint();
        g.turn(destination.getFigura().establishOpposite());
    }

    public Team.Teams getControlled() {
        return controlled;
    }

}
