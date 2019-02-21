/**
 * @author Galimberti_Francesco
 *
 * @version 4.0
 */
package nb_corsacavalli;

import java.util.concurrent.Semaphore;

/**
 * @author Galimberti_Francesco
 *
 * @brief Classe DatiCondivisi, si occupa di memorizzare e restituire il numero
 * di galoppi di ogni cavallo della gara, e di memorizzare e restituire tutti
 * gli output dei Thread
 */
public class DatiCondivisi {

    private Integer[] nClop;
    private String[] schermo;
    private Semaphore mutex;
    
    /**
     * @author Galimberti_Francesco
     *
     * Dichiarazione dell'attrbuto che memorizza la prima cella libera
     * dell'attributo schermo
     */
    private int primaPosizioneLibera;

    /**
     * @author Galimberti_Francesco
     *
     * @brief Metodo costruttore con parametri che inizializza a 0 i numeri di
     * galoppi di ogni cavallo e la prima posizione disponibile, inoltre
     * inizializza la dimensione del vettore di stringhe
     */
    public DatiCondivisi() {
        nClop = new Integer[5];
        schermo = new String[100000];
        primaPosizioneLibera = 0;
        mutex = new Semaphore(0);
    }
    
    public synchronized Integer getNClop(Integer nClop){
        return this.nClop[nClop];
    }
    
    public synchronized void setClop(Integer nClop, Integer value){
        this.nClop[nClop] = value;
    }
    
    /**
     * @author Galimberti_Francesco
     *
     * @brief Metodo che permette di aggiungere al vettore "schermo" una stringa
     * passata come parametro, e di incrementare l'attributo che memorizza la
     * prima posizione libera del vettore
     *
     * @param s il parametro serve a passare al metodo una stringa che verrà
     * salvata nel vettore "schermo" nella posizione indicata dall'attributo
     * primaPosizioneLibera
     */
    public synchronized void aggiungiLinea(String s) {
        schermo[primaPosizioneLibera] = s;
        primaPosizioneLibera++;
    }

    /**
     * @author Galimberti_Francesco
     *
     * @brief Metodo che permette di visualizzare tutto il contenuto dello
     * schermo
     */
    public synchronized void visualizzaLinee() {
        for (int i = 0; i < this.primaPosizioneLibera; i++) {
            System.out.println(schermo[i]);
        }
    }
    public void mutexWait(){
        mutex.acquireUninterruptibly();
    }
    public void mutexSignal(){
        mutex.release();
    }

}
