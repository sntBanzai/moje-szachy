/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import static com.mycompany.szachy.Game.szachownica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Timer;

/**
 *
 * @author X870
 */
public class AI {

    private final static Logger logger = Logger.getLogger("Game.class");
    static Szachownica szach = SzachyExec.szachownica;
    private Team.Teams controlled;
    private Game g;
    private Timer t;
    private HashMap<Pole, HashSet<Pole>> whenJeopardized;

    public AI(Team.Teams controlled, Game g) {
        this.controlled = controlled;
        this.g = g;
    }

    void doTurn() {
        if (g.isKingJeopardized(controlled).size() > 0) {
            defendTheKing();
        } else {
            Pole chosen = null;
            Pole destination = null;
            boolean ersties = true;
            for (Pole p : szachownica.getFieldsOccupiedBy(this.getControlled())) {
                for (Pole pp : p.getFigura().establishAvailableMoves()) {
                    if (pp.isOccupied() && pp.getFigura().getTeam() != controlled) {
                        if (ersties) {
                            destination = pp;
                            chosen = p;
                            ersties = false;
                        }
                        if (destination != null && destination.getFigura().getRank() < pp.getFigura().getRank()) {
                            destination = pp;
                            chosen = p;
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
            showWhereYoullMove(chosen, destination);
            move(chosen, destination);
        }
    }

    private void showWhereYoullMove(Pole chosen, Pole destination) {
        chosen.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        if (destination.isOccupied()) {
            destination.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        } else {
            destination.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        }
        chosen.repaint();
        destination.repaint();
    }

    public Team.Teams getControlled() {
        return controlled;
    }

    private void move(final Pole fromWhere, final Pole where) {
        t = new Timer(3000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fromWhere.setBorder(null);
                where.setBorder(null);
                if (where.isOccupied()) {
                    where.removeActualFigure();
                }
                fromWhere.getFigura().deployOnField(where);
                fromWhere.getFigura().releaseField(fromWhere);
                fromWhere.repaint();
                where.repaint();
                logger.log(Level.INFO, "Wykonano ruch z  " + fromWhere.toString() + " na  "
                        + where.toString() + ", drużyna " + where.getFigura().getTeam()
                        + ", figura " + where.getFigura());
                g.turn(where.getFigura().establishOpposite(), t);

            }
        });
        t.start();

    }

    private void defendTheKing() {
        g.defendTheKing(g.isKingJeopardized(controlled));
        Pole chosen = null;
        Pole destination = null;
        for (Entry<Pole, HashSet<Pole>> ent : whenJeopardized.entrySet()) {
            if ((chosen != null) && (ent.getKey().getFigura().getRank() < chosen.getFigura().getRank())) {
                chosen = ent.getKey();
            } else if (chosen == null) {
                chosen = ent.getKey();
            }
        }
        ArrayList<Pole> destinations = new ArrayList<>(chosen.getFigura().establishAvailableMoves());
        destination = destinations.get(new Random().nextInt(destinations.size()));
        showWhereYoullMove(chosen, destination);
        move(chosen, destination);
    }

    void aquireData(HashMap<Pole, HashSet<Pole>> defsAndOptions) {

    }

}
