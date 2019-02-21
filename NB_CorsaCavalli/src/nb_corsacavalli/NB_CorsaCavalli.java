/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb_corsacavalli;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Galimberti_Francesco
 *
 * @brief La classe CorsaCavalli40 permette di simulare una gara tra 5 cavalli
 * La classe collabora con le classi: ThCorsa, DatiCondivisi
 */
public class NB_CorsaCavalli {


    /**
     * @author Galimberti_Francesco
     *
     * @brief Il metodo simula una gara-scommessa tra 5 cavalli.
     *
     * Il metodo permette all'utente di scegliere su quale cavallo puntare. Crea
     * e avvia i 5 Thread che corrispondono ai 5 cavalli. Quando l'utente digita
     * un tasto qualsiasi vengono visualizzati i galoppi dei cavalli altrimenti
     * digitando ENTER la gara termina. Viene visualizzato il cavallo vincitore
     * (in base al numero di galoppi) e verificata la vittoria o meno
     * dell'utente.
     */
    public static void main(String[] args) {

        try {
            // TODO code application logic here
            java.io.BufferedReader console = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            Scanner input = new Scanner(System.in);

            boolean finito = false;

            DatiCondivisi dati = new DatiCondivisi();
            ThCorsa Clop1 = new ThCorsa(1, true, true, dati);
            ThCorsa Clop2 = new ThCorsa(2, true, true, dati);
            ThCorsa Clop3 = new ThCorsa(3, true, true, dati);
            ThCorsa Clop4 = new ThCorsa(4, true, true, dati);
            ThCorsa Clop5 = new ThCorsa(5, true, true, dati);

            System.out.println("Su che cavallo punti? 1/2/3/4/5");
            int numCav = input.nextInt();

            Clop1.start();
            Clop2.start();
            Clop3.start();
            Clop4.start();
            Clop5.start();

            while (!finito) {
                dati.visualizzaLinee();
                String s = console.readLine();
                if (s.endsWith(" ")) {
                    finito = true;
                } else {
                    clearConsole();
                }
            }

            Clop1.interrupt();
            Clop2.interrupt();
            Clop3.interrupt();
            Clop4.interrupt();
            Clop5.interrupt();

            //attendi
            Clop1.join();
            Clop2.join();
            Clop3.join();
            Clop4.join();
            Clop5.join();

            int max = 0;
            int cavallo = 0;
            
            for(int i = 0; i < 5; i++){
                if(dati.getNClop(i) > max){
                    max = dati.getNClop(i);
                    cavallo = i + 1;
                }
            }
            

            System.out.println("Numero galoppi:");
            for(Integer i = 0; i < 5; i++){
                Integer num = i+1;
                System.out.println("Clop" + num.toString() + " --> " + dati.getNClop(i).toString());
            }
            System.out.println("Cavallo vincitore: " + cavallo);
            System.out.println("Cavallo puntato: " + numCav);

            if (numCav == cavallo) {
                System.out.println("WINNER");
            } else {
                System.out.println("LOSER");
            }

            System.out.println("Alla prossima!");
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(NB_CorsaCavalli.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

}
