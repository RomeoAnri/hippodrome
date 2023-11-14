import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    public void checkExceptionIfParamOfConstructorIsNull(){
        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
    }

    @Test
    public void testIfParamOfConstructorIsNullThenMessageCorrect() {
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
        String actual = exception.getMessage();
        String expected = "Horses cannot be null.";
        assertEquals(expected,actual);
    }

    @Test
    public void checkExceptionIfParamOfConstructorIsEmptyList(){
        assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void testIfParamOfConstructorIsEmptyListThenMessageCorrect() {
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<>()));
        String actual = exception.getMessage();
        String expected = "Horses cannot be empty.";
        assertEquals(expected,actual);
    }
    @Test
    void testIfMethodGetHorsesReturnsSameObjectsInSameOrder() {
        List<Horse> expected = new ArrayList<>();
        for (int counHorse = 0; counHorse < 30; counHorse++) {
            expected.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();
        assertEquals(expected, actual);
    }

    @Test
    void testIfMethodMoveCallsMoveMethodOnAllHorses() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int counHorse = 0; counHorse < 50; counHorse++) {
            mockedHorses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        for (Horse mockedHorse : mockedHorses) {
            verify(mockedHorse, times(1)).move();
        }
    }

    @Test
    void testIfMethodGetWinnerReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Kenny",1.0,100.0);
        Horse horse2 = new Horse("Bonny",2.0,200.0);
        Horse horse3 = new Horse("Joe",3.0,300.0);
        Horse horse4 = new Horse("Denny",4.0,400.0);
        Horse horse5 = new Horse("Henry",5.0,500.0);
        List<Horse> competitionList = Arrays.asList(horse1, horse2, horse3, horse4, horse5);
        Hippodrome hippodrome = new Hippodrome(competitionList);
        double actual = hippodrome.getWinner().getDistance();
        double expected = horse5.getDistance();
        assertEquals(expected,actual);

    }
}