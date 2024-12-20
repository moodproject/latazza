package datatype;

public class Selling {
	
	public int coffee, arabicCoffee, tea, lemonTea, camomileTea;
	
	public Selling(int caffe, int arabico, int the, int theLimone, int camomilla) {
		this.coffee = caffe;
		this.arabicCoffee = arabico;
		this.tea = the;
		this.lemonTea = theLimone;
		this.camomileTea = camomilla;
	}
	
	public void printSelling() {
		System.out.println("Coffee = " + this.coffee);
		System.out.println("Arabic coffee = " + this.arabicCoffee);
		System.out.println("Tea = " + this.tea);
		System.out.println("Lemon-Tea = " + this.lemonTea);
		System.out.println("Camomile-Tea = "+ this.camomileTea);
	}
	
	
	public String toString() {
		String result = this.coffee + " " + this.arabicCoffee + " " +
		                this.tea + " " + this.lemonTea + " " + 
		                this.camomileTea;
		return result;
	}
	
	// get
	public int getCoffeeSmallBags() {
		return coffee;
	}
	
	public int getArabicSmallBags() {
		return arabicCoffee;
	}
	
	public int getTeaSmallBags() {
		return tea;
	}
	
	public int getLemonTeaSmallBags() {
		return lemonTea;
	}
	
	public int getCamomileSmallBags() {
		return camomileTea;
	}
}
