package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.menu.StartCommand;
import spaceinvaders.model.menu.StartInLevelCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartInLevelCommandTest {
    StartCommand startCommand;
    StartInLevelCommand startInLevelCommand;
    @BeforeEach
    public void helper() {
        startCommand = Mockito.mock(StartCommand.class);
        startInLevelCommand = new StartInLevelCommand(startCommand, 2);
        Mockito.verify(startCommand, Mockito.times(1)).setLevel(2);
    }

    @Test
    public void getStartCommandTest() {
        assertEquals(startInLevelCommand.getStartCommand(), startCommand);
    }

    @Test
    public void getTitle() {
        assertEquals(startInLevelCommand.getTitle(), "Start Game In Level");
    }

    @Test
    public void executeTest() {
        startInLevelCommand.execute();
        Mockito.verify(startCommand, Mockito.times(1)).execute();
    }
}
