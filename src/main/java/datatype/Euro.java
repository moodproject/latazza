package datatype;

public class Euro {
	private int valore;

	public Euro() {
		valore = 0;
	}
	
	public Euro(int euro, int cent) {
		if (euro >= 0) {
			valore = euro*100 + cent;
		} else {
			valore = euro*100 - cent;
		}
	}

	public Euro(double d) {
		valore = (int)(d*100);
	}
	
	public int getValue() {
		return valore;
	}
	
	public int getCent() {
		if (valore > 0) {
			String valore_stringa = Integer.toString(valore);
			int lunghezza = valore_stringa.length();
			if (lunghezza >= 3){
				String cent_stringa = valore_stringa.substring(lunghezza-2, lunghezza);
				return  Integer.parseInt(cent_stringa);
			} else {
				return  Integer.parseInt(valore_stringa);
			}
		} if (valore < 0 ) {
			String valore_stringa = Integer.toString(valore);
			int lunghezza = valore_stringa.length();
			if (lunghezza > 3){
				String cent_stringa = valore_stringa.substring(lunghezza-2, lunghezza);
				return  Integer.parseInt(cent_stringa);
			} else {
				return  Integer.parseInt(valore_stringa);
			}
		} else {
			return  0;
		}
	}
	
	public int getEuro() {
		if (valore > 0) {
			String valore_stringa = Integer.toString(valore);
			int lunghezza = valore_stringa.length();
			if (lunghezza >= 3){
				String euro_stringa = valore_stringa.substring(0, lunghezza-2);
				return  Integer.parseInt(euro_stringa);
			} else {
				return  0;
			}
		} else if (valore < 0 ) {
			String valore_stringa = Integer.toString(valore);
			int lunghezza = valore_stringa.length();
			if (lunghezza > 3){
				String euro_stringa = valore_stringa.substring(0, lunghezza-2);
				return  Integer.parseInt(euro_stringa);
			} else {
				return  0;
			}
		} else {
			return  0;
		}	
	}

	public Euro addition(Euro e) {
		this.valore = this.valore + e.getValue();
		return this;
	}

	public Euro subtract(Euro e){
		this.valore = this.valore - e.getValue();
		return this;
	}

	public Euro multiply(int num) {
		Euro result = new Euro();
		for (int i = 1 ; i <= num; i++) {
			result = result.addition(this);
		}
		return result;	
	}

	public boolean minor(Euro e){ 
		if (valore < e.getValue())
				return true;
		else return false;
	}
	
	public boolean major(Euro e){
		if (valore > e.getValue())
				return true;
		else return false;
	}
	
	public boolean equalTo(Euro e){
		if (valore == e.getValue())
				return true;
		else return false;
	}
	
    // restituisce la rappresentazione "stringa"
	public String toString() {
		if (this.getCent() > 0) {
			String euro_stringa = Integer.toString(this.getEuro());
			String cent_stringa = Integer.toString(this.getCent());
			return euro_stringa + "." + cent_stringa;
		} else if (this.getCent() < 0) {
			int cent = this.getCent() * (-1);
			String euro_stringa = "-0";
			String cent_stringa = Integer.toString(cent);
			return euro_stringa + "." + cent_stringa;
		} else {
			return Integer.toString(this.getEuro());
		}
	}
	
	// dato l'Euro restituisce il double corrispondente
	public double toDouble() {
    	return (double)valore/100;
	}
}
