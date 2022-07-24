import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Spy
    Horse horse = new Horse("Penny", 10, 20);
    @Spy
    Horse horseWithTwoParams = new Horse("John", 20);

    @Test
    void testFirstParamNullAtConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    ", "\t\t"})
    void testFirstParamBlankAtConstructor(String param) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(param, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testSecondParamNegativeNumberAtConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void testThirdParamNegativeNumberAtConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        assertEquals("Penny", horse.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void getDistanceWithThreeParamsTest() {
        assertEquals(20, horse.getDistance());
    }

    @Test
    void getDistanceWithTwoParamsTest() {
        assertEquals(0, horseWithTwoParams.getDistance());
    }

    @Test
    void moveTest() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4})
    void moveTestWithParams(double param) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(param);
            double expression = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(expression, horse.getDistance());
        }
    }


}
