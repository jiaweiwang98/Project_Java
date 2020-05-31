import java.util.ArrayList;

public class Administratie {

    private static final int DAYS_IN_WEEK = 7 ;

    //private contructor, prevent initiatie buiten het object
    private Administratie(){
    }

    /**
     * Deze methode berekent van de int array aantal de gemiddelde waarde
     *
     * @param aantal
     * @return het gemiddelde
     */
    public double berekenGemiddeldAantal(int[] aantal) {
        double toReturn = 0;
        for (int amount : aantal) {
            toReturn += amount;
        }
        return toReturn / aantal.length;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public static double berekenGemiddeldeOmzet(double[] omzet) {
        double toReturn = 0;

        for (double amount: omzet) {
            toReturn += amount ;
        }
        return toReturn / omzet.length;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYS_IN_WEEK];

        for(int i = 0; i < DAYS_IN_WEEK; i++) {

            int j = 0;
            while(i + DAYS_IN_WEEK * j < omzet.length) {
                temp[i] += omzet[i + DAYS_IN_WEEK * j];
                j++;
            }
        }
        return temp;
    }
}
