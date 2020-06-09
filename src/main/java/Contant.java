public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if(saldo >= tebetalen)
            saldo = saldo - tebetalen;

        else if(saldo < tebetalen)
            throw new TeWeinigGeldException("Contant betalen niet gelukt, te weinig geld");
    }
}

