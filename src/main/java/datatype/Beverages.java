package datatype;

public enum Beverages {
	CAFFE, ARABICO, THE, THELIMONE, CAMOMILLA;
	
	public static Beverages getTipo(String s) {
		if (s.equals("Coffee"))
			return Beverages.CAFFE;
		if (s.equals("Arabic coffee"))
			return Beverages.ARABICO;
		if (s.equals("Tea"))
			return Beverages.THE;
		if (s.equals("Lemon-tea"))
			return Beverages.THELIMONE;
		if (s.equals("Camomile-tea"))
			return Beverages.CAMOMILLA;
		return null;
	}	
  }
