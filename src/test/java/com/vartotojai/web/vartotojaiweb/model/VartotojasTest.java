package com.vartotojai.web.vartotojaiweb.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VartotojasTest {

	@Test
	void testVartotojasStringString() {
		Vartotojas v = new Vartotojas("Algis", "876547");
		assertAll("Test Vartotojas constructor",
				() -> assertEquals("Algis", v.getVardas()),
				() -> assertEquals("876547", v.getTelNr())
				);
	}

	@Test
	void testCompareTo() {
		Vartotojas v1 = new Vartotojas(1, "Algis", "876547");
		Vartotojas v2 = new Vartotojas(1,"Algis", "876547");
		assertEquals(0, v1.compareTo(v2));
	}

	@Test
	void testEqualsObject() {
		Vartotojas v1 = new Vartotojas(1, "Algis", "876547");
		Vartotojas v2 = new Vartotojas(1, "Algis", "876547");
		assertTrue(v1.equals(v2));
	}

	@Test
	void testSetID() {
		Vartotojas v = new Vartotojas();
		assertThrows(NullPointerException.class, () -> {  v.setID(-1);  });
	}

	@Test
	void testSetVardas() {
		Vartotojas v = new Vartotojas();
		assertThrows(NullPointerException.class, () -> {  v.setVardas(null);  });
	}

}
