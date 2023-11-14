import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void checkExceptionIfFirstParamOfConstructorIsNull(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 2.0, 100.0));
    }
    @Test
    public void testIfFirstParamOfConstructorIsNullThenMessageCorrect() {
    IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
            new Horse(null, 2.0, 100.0));
    String actual = exception.getMessage();
    String expected = "Name cannot be null.";
    assertEquals(expected,actual);
    }

    @Test
    public void checkExceptionIfFirstParamOfConstructorIsBlank(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(" ", 2.0, 100.0));
    }

    @Test
    public void testIfFirstParamOfConstructorIsBlankThenMessageCorrect() {
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
                new Horse(" ", 2.0, 100.0));
        String actual = exception.getMessage();
        String expected = "Name cannot be blank.";
        assertEquals(expected,actual);
    }

    @Test
    public void checkExceptionIfSecondParamOfConstructorIsNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("Kenny", -2.0, 100.0));
    }

    @Test
    public void testIfSecondParamOfConstructorIsNegativeNumberThenMessageCorrect() {
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
                new Horse("Kenny", -2.0, 100.0));
        String actual = exception.getMessage();
        String expected = "Speed cannot be negative.";
        assertEquals(expected,actual);
    }

    @Test
    public void checkExceptionIfThirdParamOfConstructorIsNegativeNumber(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse("Kenny", 2.0, -100.0));
    }

    @Test
    public void testIfThirdParamOfConstructorIsNegativeNumberThenMessageCorrect() {
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () ->
                new Horse("Kenny", 2.0, -100.0));
        String actual = exception.getMessage();
        String expected = "Distance cannot be negative.";
        assertEquals(expected,actual);
    }

    @Test
    void testIfGetNameMethodReturnCorrectValue() {
        Horse horse = new Horse("Kenny", 2.0, 100.0);
        String actual = horse.getName();
        String expected = "Kenny";
        assertEquals(expected,actual);
    }

    @Test
    void testIfGetSpeedMethodReturnCorrectValue() {
        Horse horse = new Horse("Kenny", 2.0, 100.0);
        double actual = horse.getSpeed();
        double expected = 2.0;
        assertEquals(expected,actual);
    }

    @Test
    void testIfGetDistanceMethodReturnCorrectValue() {
        Horse horse = new Horse("Kenny", 2.0, 100.0);
        double actual = horse.getDistance();
        double expected = 100.0;
        assertEquals(expected,actual);
    }

    @Test
    void testMoveCallsGetRandomDoubleWithCorrectParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Kenny", 2.0, 100.0);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.5, 100.0, 5.0", "0.3, 50.0, 2.0","0.7, 350.0, 3.0"})
    void testMoveCalculatesDistanceCorrectly(double mockedRandomValue, double initialDistance, double speed) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandomValue);
            Horse horse = new Horse("Kenny", speed, initialDistance);
            horse.move();
            double expected = initialDistance + speed * mockedRandomValue;
            double actual = horse.getDistance();
            assertEquals(expected, actual);
        }
    }



}
