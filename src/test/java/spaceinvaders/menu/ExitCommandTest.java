package spaceinvaders.menu;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceinvaders.model.menu.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    ExitCommand exitCommand;
    @BeforeEach
    public void helper() {
        exitCommand = new ExitCommand();
    }
    @Test
    public void getTitleTest() {
        assertEquals(exitCommand.getTitle(), "Exit");
    }
    @Test void executeTest() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> {
            exitCommand.execute();
        });
        assertEquals(0, status);
    }
}
