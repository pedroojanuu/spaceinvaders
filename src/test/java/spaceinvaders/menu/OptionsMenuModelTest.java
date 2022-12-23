package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.OptionsMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsMenuModelTest {
    GameModel gameModelMock;
    OptionsMenuModel model;
    @BeforeEach
    public void helper(){
        gameModelMock = Mockito.mock(GameModel.class);
        OptionsMenuModel.reset();
        model = OptionsMenuModel.getInstance(gameModelMock);
    }

    @Test
    public void addCommandTest(){
        model.clearCommands();
        model.addCommands();
        assertEquals(model.getCommands().size(), 3);
        assertEquals(model.getCommands().get(0).getTitle(),"Controls");
        assertEquals(model.getCommands().get(1).getTitle(), "Info");
        assertEquals(model.getCommands().get(2).getTitle(),"Exit to Menu");
    }

    @Test
    public void getExitCommandTest(){
        Command exitCommand = Mockito.mock(Command.class);
        model.setExitCommand(exitCommand);
        assertEquals(exitCommand, model.getExitCommand());
    }

    @Test
    public void getSelectedCommandTest(){
        model.clearCommands();
        model.addCommands();
        assertEquals(model.getSelectedCommand().getTitle(), "Controls");
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Info");
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Exit to Menu");
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Controls");
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Exit to Menu");
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Info");
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Controls");
    }

    @Test
    public void getSelectedCommandIntTest(){
        model.clearCommands();
        model.addCommands();
        assertEquals(model.getSelectedCommandInt(), 0);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 1);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 2);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 0);
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 2);
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 1);
        model.upSelectedCommand();
        assertEquals(model.getSelectedCommandInt(), 0);
    }

    @Test
    public void downSelectedCommandTest(){
        model.clearCommands();
        model.addCommands();
        assertEquals(model.getSelectedCommand().getTitle(), "Controls");
        assertEquals(model.getSelectedCommandInt(), 0);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Info");
        assertEquals(model.getSelectedCommandInt(), 1);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Exit to Menu");
        assertEquals(model.getSelectedCommandInt(), 2);
        model.downSelectedCommand();
        assertEquals(model.getSelectedCommand().getTitle(), "Controls");
        assertEquals(model.getSelectedCommandInt(), 0);
    }

    @Test
    public void getGameModelTest(){
        assertEquals(model.getGameModel(), gameModelMock);
    }

    @Test
    public void clearCommandsTest(){
        model.clearCommands();
        assertEquals(model.getCommands().size(), 0);
    }
}
