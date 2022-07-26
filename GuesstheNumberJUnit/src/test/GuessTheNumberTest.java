import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuessTheNumberTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void playTest() {
        GuessTheNumber.play("Brady",new Scanner(System.in));
    }

    @Test
    void gameOverTest() {
        GuessTheNumber.gameOver("Brady", new Scanner(System.in));
    }
}