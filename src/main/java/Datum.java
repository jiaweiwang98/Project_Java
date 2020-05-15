
public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 *
	 * @param dag dag van de maand
	 * @param maand maand van het jaar
	 * @param jaar het jaartal
	 */

	public Datum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}
	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	/**
	 *
	 * @return De dag van het jaar
	 */
	public int getDag() {
		return dag;
	}

	/**
	 *
	 * @param dag set een dag
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 *
	 * @return een maand van het jaar
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 *
	 * @param maand set een maand van het jaar
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 *
	 * @return een jaartal
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 *
	 * @param jaar set een jaartal
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {

		switch (getDag()){
			case 1:


		}
		return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		int geboortedatum = Persoon.getGeboortedatum();
		String datum = String.valueOf(geboortedatum);
		if(datum.equals("")){
			System.out.println("De gegevens zijn onbekend");
		}

		return datum;
	}
}
