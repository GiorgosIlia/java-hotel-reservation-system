import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Main {

	public static void main(String[] args) {
		
		Hotel h1 = new Hotel("Bellevue (200\u20AC)",200);
		Hotel h3 = new Hotel("CityHotel (500\u20AC)",500);
		Hotel h2 = new Hotel("Hermes (100\u20AC)",100);
		Hotel h4 = new Hotel("Panorama (150\u20AC)",150);
		
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();

		hotels.add(h1);
		hotels.add(h2);
		hotels.add(h3);
		hotels.add(h4);
		
        hotels.sort((o1, o2)-> o1.getName().compareTo(o2.getName()));
		
	  
		try {
			FileOutputStream fileOut = new FileOutputStream("hotels.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(hotels);
			out.close();
			fileOut.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
		InputFrame inputFrame = new InputFrame(hotels);
		
	}

}
