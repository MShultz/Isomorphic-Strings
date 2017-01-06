package shultz.algorithms.isomorph;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		try{
		Identifier identity = new Identifier(args[0]);
		identity.identify();
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Error: Invalid file name; exiting program");
		} catch (FileNotFoundException e) {
			System.out.print("Error: Unable to find file; exiting program");
		}
	}

}
