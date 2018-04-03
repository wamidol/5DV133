
package clock;

import org.junit.Test;

import static org.junit.Assert.*;


public class ClockTest {

    final int testIncrementTimes = 120;

    @Test
    public void shouldCreateClock() throws Exception {
        Clock n = new Clock();
    }

    @Test(expected = LimitException.class)
    public void shouldThrowLimitExceptionHours() throws Exception {
        Clock n = new Clock(32, 23);
    }

    @Test(expected = LimitException.class)
    public void shouldThrowLimitExceptionMinutes() throws Exception {
        Clock n = new Clock(2, -23);
    }


    @Test
    public void initialStateTest() throws Exception {
        Clock n = new Clock();
        assertEquals("00:00", n.getTime());
    }

    @Test
    public void initialStateTestIncrement() throws Exception {
        Clock n = new Clock();
        n.timeTick();
        assertEquals("00:01", n.getTime());
    }

    @Test
    public void setTimeTest() throws Exception {
        Clock n = new Clock();
        n.setTime(23,59);
        assertEquals("23:59", n.getTime());
    }

    @Test(expected = LimitException.class)
    public void setTimeTestLimit() throws Exception {
        Clock n = new Clock();
        n.setTime(233, 529);
    }

    @Test
    public void setTimeTestIncrement() throws Exception {
        Clock n = new Clock();
        n.setTime(23,59);
        n.timeTick();
        assertEquals("00:00", n.getTime());
        n.timeTick();
        assertEquals("00:01", n.getTime());
        for (int i = 0; i < testIncrementTimes; i++) {
            n.timeTick();
        }
        assertEquals("02:01", n.getTime());
        for (int i = 0; i < testIncrementTimes * 4 + 23; i++) {
            n.timeTick();
        }
        assertEquals("10:24", n.getTime());

        n.setTime(13,42);
        assertEquals("13:42", n.getTime());

    }

    @Test
    public void TotalWrapAroundTest() throws Exception {
        Clock n = new Clock();
        n.setTime(13, 42);
        assertEquals("13:42", n.getTime());
        for (int i = 0; i < 1440; i++) {
                n.timeTick();
        }
        assertEquals("13:42", n.getTime());
        n.timeTick();

        for (int i = 0; i < 617; i++) {
            n.timeTick();
        }
        assertEquals("00:00", n.getTime());

    }
}
