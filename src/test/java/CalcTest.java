import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {

    Calc calc = new Calc();

    @Test
    public void sumShouldWork() {


        assertTrue(calc.sum(2, 3) == 5);
        assertFalse(calc.sum(8, 5) == 11);
        assertTrue(calc.sum(11, 3) == 14);
        assertEquals(5, calc.sum(2, 3));
    }

    @Test
    public void divisionShouldWork() {

        float result = calc.division(6, 2);

        assertEquals(3, result);
    }

    @Test
    public void divisionShouldReturnNegativeNumber() {

        float result = calc.division(6, -2);

        assertEquals(-3, result);
    }

    @Test
    public void divisionShouldReturnDecimalNumber() {

        float result = calc.division(10, 3);

        assertEquals(3.3333332538604736, result);
        assertEquals(3.33, result, 0.01); // using delta
    }

    @Test
    public void divisionShouldReturnZeroWithDenZero() {

        float result = calc.division(0, 2);

        assertEquals(0, result);
    }

    @Test
    public void divisionShouldThrowExceptionWhenZeroDivision() {
        //todo next class
        float result = calc.division(0, 2);

        assertEquals(0, result);
    }

    @Test
    public void assertionsShouldWork() {

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
