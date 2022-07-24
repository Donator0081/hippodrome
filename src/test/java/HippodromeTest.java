import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {


    @Test
    void testNullAtConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testBlankAtConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name " + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = mock(Horse.class);
            horses.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : hippodrome.getHorses()) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerTest() {
        Horse horse1 = new Horse("Name " + 1, 1, 1);
        Horse horse2 = new Horse("Name " + 2, 2, 2);
        Horse horse3 = new Horse("Name " + 3, 3, 3);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3));
        assertEquals(horse3, hippodrome.getWinner());
    }

}
