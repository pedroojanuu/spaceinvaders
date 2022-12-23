package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.ArenaModel;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.StartCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StartCommandTest {
    GameModel gameModel;
    StartCommand startCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        startCommand = new StartCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(startCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(startCommand.getTitle(), "Start Game");
    }

    @Test
    public void restartArenaTest() {
        ArenaModel oldArena = startCommand.getArena();
        startCommand.restartArena();
        assertNotEquals(oldArena, startCommand.getArena());
    }

    @Test
    public void executeTest() {
        startCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void setLevelTest() {
        startCommand.setLevel(2);
        assertEquals(startCommand.getArena().getLevel(), 2);
    }
}
