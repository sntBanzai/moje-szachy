/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.BorderFactory;
import javax.swing.Timer;

/**
 *
 * @author Jerzy Małyszko
 */
class Game {

    private final static Logger logger = Logger.getLogger("Game.class");

    static {
        Handler h;
        try {
            h = new FileHandler("gameLog.txt", true);
            h.setFormatter(new SimpleFormatter());
            logger.addHandler(h);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.setLevel(Level.ALL);
    }
    static Szachownica szachownica = SzachyExec.szachownica;
    Team biali;
    Team czarni;
    ArrayList<Timer> fieldTimers = new ArrayList<>();
    Timer gt;
    boolean gameOver = false;
    Team.Teams[] tab = Team.Teams.values();
    int turnCounter = 0;
    AI komp;

    Game() {
        biali = new Team(Team.Teams.Biali);
        czarni = new Team(Team.Teams.Czarni);
        deployFigures(biali);
        deployFigures(czarni);
        for (Component c : szachownica.getComponents()) {
            c.repaint();
        }
        LogRecord lr = new LogRecord(Level.INFO, "< < <Rozpoczęcie nowej gry!> > >");
        logger.log(lr);
        this.komp = new AI(Team.Teams.Biali, this);
    }

    void playTheGame() {
        turn(Team.Teams.Biali);

    }

    void turn(final Team.Teams color) {

        if (komp.getControlled() == color) {
            komp.doTurn();
        } else {
            for (final Pole p : szachownica.getFieldsOccupiedBy(color)) {
                p.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent me) {
                        p.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (p.shouldIClearBorder()) {
                            p.setBorder(null);
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        eraseTimers();
                        p.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
                        p.setShouldIClearBorder(false);
                        for (final Pole pp : p.getFigura().establishAvailableMoves()) {
                            for (MouseListener ms : pp.getMouseListeners()) {
                                pp.removeMouseListener(ms);
                            }
                            gt = new Timer(350, new ActionListener() {

                                Color[] borderColors = {Color.WHITE, Color.GRAY};
                                Color[] oppositeTeamOcc = {Color.RED, Color.GRAY};
                                boolean flag = true;

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (pp.isOccupied()) {
                                        if (flag) {
                                            pp.setBorder(BorderFactory.createLineBorder(oppositeTeamOcc[0], 5));
                                            pp.repaint();
                                            flag = false;
                                        } else {
                                            pp.setBorder(BorderFactory.createLineBorder(oppositeTeamOcc[1], 5));
                                            pp.repaint();
                                            flag = true;
                                        }
                                    } else {
                                        if (flag) {
                                            pp.setBorder(BorderFactory.createLineBorder(borderColors[0], 5));
                                            pp.repaint();
                                            flag = false;
                                        } else {
                                            pp.setBorder(BorderFactory.createLineBorder(borderColors[1], 5));
                                            pp.repaint();
                                            flag = true;
                                        }
                                    }

                                }
                            });
                            gt.start();
                            fieldTimers.add(gt);
                            pp.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (p.getFigura() == null) {
                                        return;
                                    }
                                    System.out.println("Pole p" + p);
                                    System.out.println("Pole pp " + pp);
                                    p.getFigura().deployOnField(pp);
                                    pp.repaint();
                                    p.getFigura().releaseField(p);
                                    p.repaint();
                                    logger.log(Level.INFO, "Wykonano ruch z  " + p.toString() + " na  " + pp.toString() + ", drużyna " + pp.getFigura().getTeam() + ", figura " + pp.getFigura());
                                    eraseTimers();
                                    whichHaveListeners();
                                    p.setShouldIClearBorder(true);
                                    turn(pp.getFigura().establishOpposite());
                                }
                            });
                        }
                    }

                });
            }
        }
    }

    final void deployFigures(Team t) {
        if (t.getName() == Team.Teams.Biali) {
            biali.pionki[0].deployOnField(szachownica.seekField('2', 'a'));
            biali.pionki[1].deployOnField(szachownica.seekField('2', 'b'));
            biali.pionki[2].deployOnField(szachownica.seekField('2', 'c'));
            biali.pionki[3].deployOnField(szachownica.seekField('2', 'd'));
            biali.pionki[4].deployOnField(szachownica.seekField('2', 'e'));
            biali.pionki[5].deployOnField(szachownica.seekField('2', 'f'));
            biali.pionki[6].deployOnField(szachownica.seekField('2', 'g'));
            biali.pionki[7].deployOnField(szachownica.seekField('2', 'h'));
            biali.gonce[0].deployOnField(szachownica.seekField('1', 'c'));
            biali.gonce[1].deployOnField(szachownica.seekField('1', 'f'));
            biali.konie[0].deployOnField(szachownica.seekField('1', 'b'));
            biali.konie[1].deployOnField(szachownica.seekField('1', 'g'));
            biali.wieze[0].deployOnField(szachownica.seekField('1', 'a'));
            biali.wieze[1].deployOnField(szachownica.seekField('1', 'h'));
            biali.hetman.deployOnField(szachownica.seekField('1', 'd'));
            biali.krol.deployOnField(szachownica.seekField('1', 'e'));
        } else if (t.getName() == Team.Teams.Czarni) {
            czarni.pionki[0].deployOnField(szachownica.seekField('7', 'a'));
            czarni.pionki[1].deployOnField(szachownica.seekField('7', 'b'));
            czarni.pionki[2].deployOnField(szachownica.seekField('7', 'c'));
            czarni.pionki[3].deployOnField(szachownica.seekField('7', 'd'));
            czarni.pionki[4].deployOnField(szachownica.seekField('7', 'e'));
            czarni.pionki[5].deployOnField(szachownica.seekField('7', 'f'));
            czarni.pionki[6].deployOnField(szachownica.seekField('7', 'g'));
            czarni.pionki[7].deployOnField(szachownica.seekField('7', 'h'));
            czarni.gonce[0].deployOnField(szachownica.seekField('8', 'c'));
            czarni.gonce[1].deployOnField(szachownica.seekField('8', 'f'));
            czarni.konie[0].deployOnField(szachownica.seekField('8', 'b'));
            czarni.konie[1].deployOnField(szachownica.seekField('8', 'g'));
            czarni.wieze[0].deployOnField(szachownica.seekField('8', 'a'));
            czarni.wieze[1].deployOnField(szachownica.seekField('8', 'h'));
            czarni.hetman.deployOnField(szachownica.seekField('8', 'd'));
            czarni.krol.deployOnField(szachownica.seekField('8', 'e'));
        }

    }

    void eraseTimers() {
        for (Timer t : fieldTimers) {
            t.stop();
        }
        fieldTimers.clear();
        for (Pole[] ololo : szachownica.getFieldSet()) {
            for (Pole borderRem : ololo) {
                borderRem.setBorder(null);

            }
        }
    }

    void whichHaveListeners() {
        for (Pole[] ololo : szachownica.getFieldSet()) {
            for (Pole listenerRem : ololo) {
                for (MouseListener ms : listenerRem.getMouseListeners()) {
                    listenerRem.removeMouseListener(ms);

                }
            }
        }
    }

}
