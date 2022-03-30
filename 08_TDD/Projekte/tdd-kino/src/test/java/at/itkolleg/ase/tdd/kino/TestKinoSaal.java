package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestKinoSaal {

    @Mock
    private KinoSaal saalTest;

    @BeforeEach
    void setup(){
        Map<Character, Integer> saalTestMap = new HashMap<>();
        saalTestMap.put('D',2);
        saalTestMap.put('E',3);
        saalTestMap.put('F',4);
        saalTest = new KinoSaal("SaalTest", saalTestMap);
    }

    @Test
    void testName(){
        assertEquals("SaalTest", saalTest.getName());
        Assertions.assertNotEquals("TestSaal", saalTest.getName());
    }

    @Test
    void platzPruefen(){
        Assertions.assertTrue(saalTest.pruefePlatz('D', 2));
        Assertions.assertTrue(saalTest.pruefePlatz('F', 4));
        Assertions.assertFalse(saalTest.pruefePlatz('G',12));
    }

    @Test
    void testEquals(){
        Assertions.assertTrue(saalTest.equals(saalTest));
    }

}
