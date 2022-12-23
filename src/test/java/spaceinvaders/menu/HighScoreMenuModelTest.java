package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.HighScoreMenuModel;
import spaceinvaders.model.menu.StartCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighScoreMenuModelTest {
    GameModel gameModelMock;
    HighScoreMenuModel model;
    @BeforeEach
    public void helper(){
        gameModelMock = Mockito.mock(GameModel.class);
        HighScoreMenuModel.reset();
        model = HighScoreMenuModel.getInstance(gameModelMock);
    }
    @Test
    public void getExitCommandTest(){
        StartCommand startCommand = new StartCommand(gameModelMock);
        model.setExitCommand(startCommand);
        assertEquals(startCommand, model.getExitCommand());
    }
}
