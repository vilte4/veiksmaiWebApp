package com.vartotojai.web.vartotojaiweb.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VeiksmasTest {

    @Test
    void testVeiksmasStringIntString() {
        Veiksmas v = new Veiksmas("insert", 1, "01.02");
        assertAll("Test Veiksmas constructor",
                () -> assertEquals("insert", v.getReiksme()),
                () -> assertEquals(1, v.getVartotojoID()),
                () -> assertEquals("01.02", v.getDate())
        );
    }

    @Test
    void testCompareTo() {
        Veiksmas v1 = new Veiksmas(1, "insert", 1, "01.02");
        Veiksmas v2 = new Veiksmas(1,"insert", 1, "01.02");
        assertEquals(0, v1.compareTo(v2));
    }

    @Test
    void testEqualsObject() {
        Veiksmas v1 = new Veiksmas(1, "insert", 1, "01.02");
        Veiksmas v2 = new Veiksmas(1, "insert", 1, "01.02");
        assertTrue(v1.equals(v2));
    }

}