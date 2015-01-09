/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.Component;

/**
 *
 * @author Jerzy Małyszko
 */
class Game {
    
    static Szachownica szachownica = SzachyExec.szachownica;
    Team biali;
    Team czarni;
    
    Game(){
        biali = new Team(Team.Teams.Biali);
        czarni = new Team(Team.Teams.Czarni);
        deployFigures(biali);
        deployFigures(czarni);
        for (Component c:szachownica.getComponents()) {
            c.repaint();
        }
    }
    
    void deployFigures(Team t){
        if(t.getName()==Team.Teams.Biali){
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
        }
        else if(t.getName()==Team.Teams.Czarni){
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
}
