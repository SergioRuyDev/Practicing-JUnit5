import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {

    @Test
    public void calcShouldWork() {

        Calc calc = new Calc();

        assertTrue(calc.sum(2, 3) == 5);
        assertFalse(calc.sum(8, 5) == 11);
        assertTrue(calc.sum(11, 3) == 14);
        assertEquals(5, calc.sum(2, 3));
    }

    @Test
    public void assertivas() {

        assertEquals("Home", "Home");
        assertNotEquals("Home", "home");
        assertTrue("Home".equalsIgnoreCase("HOME"));
        assertTrue("Home".startsWith("Ho"));
        assertTrue("Home".endsWith("me"));

        List<String> s1 = new ArrayList<>();
        s1.add("Home");
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        assertNotEquals(s1, s2);; // assertEquals check the content
        assertNotSame(s1, s2); // assertSame look to the reference
        assertNull(s3);
//        fail("Some behavior not work"); not common use
    }
}
