package fr.mowItnow.tondeuse;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PositionTest {
	
	
	@Test
	public void testCoordonateXY() {
		
		String posAsString = "1 2";
		Position of = Position.of(posAsString);
		assertEquals(1, of.getX());
		assertEquals(2, of.getY());
	}

	
	@Test
	public void when_X_negative_then_throw_exeption() {
		
	    assertThrows(IllegalArgumentException.class, () -> {	
			Position.of("-1 2");
	    });

	}
	
	@Test
	public void when_Y_negative_then_throw_exeption() {
		
	    assertThrows(IllegalArgumentException.class, () -> {	
			Position.of("1 -2");
	    });

	}

    
}
