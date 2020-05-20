public class Datum {

	private int dag;
	private int maand;
	private int jaar;
	/**
	 * Constructor
	 */
	public Datum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand ;
		this.jaar = jaar;
		bestaatDatum(dag, maand, jaar);
	}
	
	/**
	 * Constructor
	 */
	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	public int getDag() {
		return dag;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

		public int getMaand() {
		return maand;
	}

		public void setMaand(int maand) {
		this.maand = maand;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		// Controle jaar
		if(jaar >= 1900 && jaar <= 2100) {
			// Controle maand
			int maxDagen = 0;
			switch (maand) {
				case 1: case 3: case 5:
				case 7: case 8: case 10:
				case 12:
					maxDagen = 31;
					break;
				case 4: case 6:
				case 9: case 11:
					maxDagen = 30;
					break;
				case 2:
					if (((jaar % 4 == 0) &&
							!(jaar % 100 == 0))
							|| (jaar % 400 == 0))
						maxDagen = 29;
					else
						maxDagen = 28;
					break;
				// Return false wanneer het niet een geldige maand is
				default:
					return false;
			}
			// Controle dag
			if (dag >= 1 && dag <= maxDagen) {
				return true;
			} else {
				return false;
			}
		} else { // Retrun false wanneer het geen geldig jaar is
			return false;
		}

	}


	/**
	 * Getter voor geboortedatum
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		String temp;
		if (dag==0 && maand==0 && jaar==0) {
		temp="Onbekend";
		} else {
		temp=dag+"/"+maand+"/"+jaar;
		}
		return temp;
		}
}
