public class Administratie {

    private static final int DAYSINWEEK = 7 ;

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
        toReturn = toReturn / aantal.length;
        return toReturn;
    }

    /**
     * Deze methode berekent van de double array omzet de gemiddelde waarde
     *
     * @param omzet
     * @return het gemiddelde
     */
    public double[] berekenGemiddeldeOmzet(double[] omzet) {
        double[] toReturn;
        toReturn = berekenDagOmzet(omzet);
        int counter = 0;
        for (double amount: toReturn) {
            toReturn[counter] = amount / Math.floor((double)omzet.length/7);
            counter++;
        }

        return toReturn;
    }

    /**
     * Methode om dagomzet uit te rekenen
     *
     * @param omzet
     * @return array (7 elementen) met dagomzetten
     */

    public static double[] berekenDagOmzet(double[] omzet) {
        double[] temp = new double[DAYSINWEEK];

        for(int i = 0; i < DAYSINWEEK; i++) {
            int j = 0;
            int counter = i + DAYSINWEEK * j;
            while(counter < omzet.length) {
                temp[i] += omzet[i + DAYSINWEEK * j];
                j++;
                counter = i + DAYSINWEEK * j;
            }
        }
        return temp;
    }
}
