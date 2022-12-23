package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.InfoMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoMenuModelTest {
    GameModel gameModelMock;
    InfoMenuModel model;
    @BeforeEach
    public void helper(){
        gameModelMock = Mockito.mock(GameModel.class);
        InfoMenuModel.reset();
        model = InfoMenuModel.getInstance(gameModelMock);
    }
    @Test
    public void getExitCommandTest(){
        Command exitCommand = Mockito.mock(Command.class);
        model.setExitCommand(exitCommand);
        assertEquals(exitCommand, model.getExitCommand());
    }
}
