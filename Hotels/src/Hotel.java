import java.io.Serializable;
import java.util.ArrayList;


public class Hotel implements Serializable {
	
	private String name;
	private int price;
	
	public Hotel(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Hotel(String name) {
		this.name = name;
	}
	
	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	
	public String printInfo() {
		return this.name+" "+this.price;
	}		
}
