package datatype;

public enum Granularity {
	GIORNI, MESI, ANNI;
	
	public static Granularity getTipo(String s) {
		if (s.equals("Day"))
			return Granularity.GIORNI;
		if (s.equals("Month"))
			return Granularity.MESI;
		if (s.equals("Year"))
			return Granularity.ANNI;
		return null;
	}
}
