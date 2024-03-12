package fr.mowItnow.tondeuse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TondeuseTest {

	@Test
	public void testOrientation() {

		Position csd = Position.of("5 5");

		Tondeuse tondeuse = Tondeuse.of("1 2 N", csd)
									.traiteInstructions("GAGAGAGAA");
		
		Orientation orientation = tondeuse.getOrientation();
		assertEquals(Orientation.N, orientation);
		
	}
	
	@Test
	public void testTondeuseCanMove() {
		
		int xCoinSuperieurDroit = 7;
		int xPositionApresMouvement = 4;
	
		boolean canMove = Tondeuse.peutAvancer(xCoinSuperieurDroit, xPositionApresMouvement);
		assertTrue(canMove);
	}
	
	@Test
	public void testTondeuseCanNotMove() {
		
		int xCoinSuperieurDroit = 7;
		int xPositionApresMouvement = 8;
	
		boolean canMove = Tondeuse.peutAvancer(xCoinSuperieurDroit, xPositionApresMouvement);
		assertFalse(canMove);
	}
	
	@Test
	public void testPositionFinale() {

		Position coinSupDroit = Position.of("5 5");
		String positionInitialeEtOrientation = "1 2 N";
		
		Tondeuse tondeuse = Tondeuse.of(positionInitialeEtOrientation, coinSupDroit)
									.traiteInstructions("GAGAGAGAA");
		
		Position pos = tondeuse.getPositionActuelle();
		assertEquals(1, pos.getX());
		assertEquals(3, pos.getY());
		
	}
	
	@Test
	public void when_Orientation_Not_Valid_then_throw_exeption() {

		 assertThrows(Exception.class, () -> {
			 
				Position coinSupDroit = Position.of("5 5");
				String positionInitialeEtOrientation = "1 2 X";
				
				Tondeuse.of(positionInitialeEtOrientation, coinSupDroit)
						.traiteInstructions("GAGAGAGAA");
		 });
		
	}
	
	
	@Test
	public void when_coinSupDroit_negative_then_throw_exeption() {
		
		 assertThrows(IllegalArgumentException.class, () -> {
			 
				Position coinSupDroit = Position.of("-5 5");
				String positionInitialeEtOrientation = "1 2 N";
				
				Tondeuse.of(positionInitialeEtOrientation, coinSupDroit)
						.traiteInstructions("GAGAGAGAA");
		    });
		
	}
	
	@Test
	public void when_pos_initiale_negative_then_throw_exeption() {
		
		 assertThrows(IllegalArgumentException.class, () -> {
			 
				Position coinSupDroit = Position.of("5 5");
				String positionInitialeEtOrientation = "-1 2 N";
				
				Tondeuse.of(positionInitialeEtOrientation, coinSupDroit)
						.traiteInstructions("GAGAGAGAA");
		    });
		
	}
	
	@Test
	public void when_Pos_Initiale_Not_Valid_then_throw_exeption() {
		
		 assertThrows(IllegalArgumentException.class, () -> {
			 
				Position coinSupDroit = Position.of("5 5");
				String positionInitialeEtOrientation = "6 6 N";
				
				Tondeuse.of(positionInitialeEtOrientation, coinSupDroit)
						.traiteInstructions("GAGAGAGAA");
		    });
		
	}

}
