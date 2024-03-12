package fr.mowItnow.tondeuse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

public class Main {

    
    private static final Logger LOGGER = System.getLogger(Main.class.getName());


	public static void main(String[] args) {
		

		String filePath = "../tondeuse/src/main/resources/inputFile.txt";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {

			String line1 = br.readLine();
			Position coinSupDroitPelouse = Position.of(line1);
			
			String posInitialeEtOrientation;;
			String instructions;
			
			int tondeuseCount = 0;
			while ((posInitialeEtOrientation = br.readLine()) != null && (instructions = br.readLine()) != null) {
                
				tondeuseCount++;

				try {
					
					Tondeuse tondeuse = Tondeuse.of(posInitialeEtOrientation, coinSupDroitPelouse)
												.traiteInstructions(instructions);

					// position finale de la tondeuse
					System.out.print(tondeuse + " ");

				} catch (Exception e) {
					LOGGER.log(Level.ERROR, "Tondeuse numéro "+ tondeuseCount, e);
				}
            }	
 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
