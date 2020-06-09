public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(kredietlimiet < tebetalen)
            throw new TeWeinigGeldException("Betaling is mislukt, kredietlimiet is overschreden");
        else if(tebetalen > saldo)
            throw new TeWeinigGeldException("Betaling is mislukt, onvoldoende saldo");
        else if(saldo >= tebetalen)
            saldo = saldo - tebetalen;
        else
            throw new TeWeinigGeldException("Betaling is mislukt"); // er is iets anders fout gegaan.
    }
}
