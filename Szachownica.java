/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.szachy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Niniejsza klasa reprezentuje obiekt szachownicy składającej się z zestawy 64
 * obiektów klasy Pole. ({@link Pole Przejdź do dokumentacji klasy Pole}).
 * @author Jerzy Małyszko
 */
public class Szachownica extends JPanel {
    
    /**
     * Tablica zawierająca dwie stałe klasy Color, służącę jako źródło do pobierania
     * koloru pola w metodzie {@link #nadajPoluKolor(int) nadajPoluKolor(int)}
     */
    static Color[] kolory = {Color.LIGHT_GRAY, Color.GRAY};
    /**
     * Tablica zawierająca elementy służące do numerowania na osi X obiektów klasy {@link 
     * Pole Pole} tworzonych w trakcie wykonywania konstruktora {@link #Szachownica() 
     * klasy Szachownica}
     */
    static char[] litery = {'a','b','c','d','e','f','g','h'};
    /**
     * Tablica zawierająca elementy służące do numerowania na osi Y obiektów klasy {@link 
     * Pole Pole} tworzonych w trakcie wykonywania konstruktora {@link #Szachownica() 
     * klasy Szachownica}
     */
    static char[] liczby = {'1','2','3','4','5','6','7','8'};
    /**
     * Dwuwymiarowa tablica o wymiarach 8 na 8 przechowująca obiekty klasy Pole.
     */
    final Pole[][] fieldSet = new Pole[8][8];
    /**
     * Zmienna pomocnicza wykorzystywana w procesie wykonania metody {@link #nadajPoluKolor(int) 
     * nadajPoluKolor(int)}
     */
    boolean flaga= true;
    
    /**
     * Publiczny bezparametrowy konstruktor klasy Szachownica. Tworzy na wstępie 
     * cztery boczne panele (instancje klasy {@link JPanel JPanel}). Niniejsze 
     * panele są następnie wypełniane oznaczeniami poszczególnych pól szachownicy
     * na osi X oraz Y. 
     * Następnie w dwóch zagnieżdżonych pętlach (jedna w drugiej) tworzone są obiekty
     * klasy {@link Pole Pole}, każdy oznaczony unikatowym zestawem współrzędnych,
     * pobranych ze statycznych pól klasy Szachownica {@link #litery litery} oraz
     * {@link #liczby liczby}. Tworzone instancje klasy Pole umieszczane są wewnątrz
     * pola instancji klasy Szachownica o nazwie {@link #fieldSet fieldSet}.
     * 
     * Utworzone elementy pozycjonowane są na ekranie za pomocą zarządców rozkładu
     * pakietu Swing.
     * 
     */
    public Szachownica(){
        setLayout(new BorderLayout());
        JPanel inner = new JPanel();
        JPanel outerN = new JPanel();
        JPanel outerE = new JPanel();
        JPanel outerS = new JPanel();
        JPanel outerW = new JPanel();
        final ArrayList<JPanel> sides = new ArrayList<>();
        sides.add(outerE); sides.add(outerW); sides.add(outerN); sides.add(outerS);
        for(JPanel bok:sides){
            Container aux = new Container();
            for (int i = 0; i < 8; i++) {
                JLabel inCreation = new JLabel();
                if(bok==outerE||bok==outerW){
                    inCreation.setPreferredSize(new Dimension(50, 110));
                    inCreation.setText("       "+String.valueOf(liczby[i]));
                    bok.add(inCreation);
                }
                else{
                    inCreation.setPreferredSize(new Dimension(110, 50));
                    if(i<2){
                       inCreation.setText("              "+String.valueOf(litery[i])); 
                    }
                    else if(i<4){
                        inCreation.setText("                "+String.valueOf(litery[i]));
                    }
                    else if(i<6){
                        inCreation.setText("                  "+String.valueOf(litery[i]));
                    }
                    else{
                        inCreation.setText("                     "+String.valueOf(litery[i]));
                    }
                    aux.add(inCreation); 
                }
            }
           if(bok==outerE||bok==outerW){
                bok.setLayout(new GridLayout(0, 1));
                }
            else{
                aux.setLayout(new GridLayout(1, 0));
                JLabel blank = new JLabel();
                JLabel blank2 = new JLabel();
                blank.setPreferredSize(new Dimension(50,50));
                blank2.setPreferredSize(new Dimension(50,50));
                bok.add(blank, BorderLayout.EAST);
                bok.add(aux, BorderLayout.CENTER);
                bok.add(blank2, BorderLayout.WEST);
            }
        }
        this.add(inner, BorderLayout.CENTER);
        this.add(outerN, BorderLayout.NORTH);
        this.add(outerE, BorderLayout.EAST);
        this.add(outerS, BorderLayout.SOUTH);
        this.add(outerW, BorderLayout.WEST);
        inner.setLayout(new GridLayout(8,8));
        Dimension dim = new Dimension(880,880);
        inner.setPreferredSize(dim);
        inner.setMaximumSize(dim);
        inner.setMinimumSize(dim);
        for (int i = 0; i < fieldSet.length; i++) {
            for (int j = 0; j < fieldSet[i].length; j++) {
                fieldSet[i][j] = new Pole(liczby[i],litery[j], nadajPoluKolor(i), i, j);
                inner.add(fieldSet[i][j]);
                fieldSet[i][j].setToolTipText("Pole "+litery[j]+" "+liczby[i]);
                fieldSet[i][j].initialToolTip = "Pole "+litery[j]+" "+liczby[i];
            }
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
        
    /**
     * Jest to metoda pomocnicza, dba o to by pola szachownicy miały odpowiedni kolor.
     * @param index Przy wywołaniu metody, jako argument przekazywany jest aktualny
     * indeks tablicy "wyższego rzędu" pola {@link #fieldSet fieldSet}
     * @return Zwraca instancję klasy java.awt.Color, który posłuży do nadania
     * koloru obiektowy klasy {@link Pole Pole} w czasie wyświetlania na ekranie.
     */
    public Color nadajPoluKolor(int index){
        Color toReturn = null;
        if(index==0||index==2||index==4||index==6){
            if(flaga){
                toReturn = kolory[0];
                flaga = false;
            }
            else{
                toReturn = kolory[1];
                flaga = true;
            }
        }
        else{
           if(flaga){
                toReturn = kolory[1];
                flaga = false;
            }
            else{
                toReturn = kolory[0];
                flaga = true;
            }
        }
        return toReturn;
    }
    
    /**
     * Metoda pozwalająca uzyskać referencję do znajdującego się na szachownicy pola
     * o zadanych parametrach.
     * @param liczba Pozycja pola na osi Y (zakres od '1' do '8').
     * @param litera Pozycja pola na osi X (zakres od 'a' do 'f').
     * @return Zwraca referencję do poszukiwanego pola.
     */
    Pole seekField(char liczba, char litera){
        Pole toReturn = null;
        for (int i = 0; i < fieldSet.length; i++) {
            for (int j = 0; j < fieldSet[i].length; j++) {
                if(fieldSet[i][j].getLitera()==litera&&fieldSet[i][j].getLiczba()==liczba)
                    toReturn = fieldSet[i][j];
            }
        }
        return toReturn;
    }
    
    Pole seekField(int xCoo, int yCoo){
        return fieldSet[xCoo][yCoo];
    }
    
    HashSet<Pole> getFieldsOccupiedBy(Team.Teams t){
        HashSet<Pole> retVal = new HashSet<>();
        for (int i = 0; i < fieldSet.length; i++) {
            for (int j = 0; j < fieldSet[i].length; j++) {
                if(fieldSet[i][j].isOccupied()&&fieldSet[i][j].getFigura().getTeam()==t){
                    retVal.add(fieldSet[i][j]);
                }
            }
        }
        return retVal;
    }

    public Pole[][] getFieldSet() {
        return fieldSet;
    }
    
    
    
}
