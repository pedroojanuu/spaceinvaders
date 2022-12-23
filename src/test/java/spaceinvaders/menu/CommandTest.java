package spaceinvaders.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.InfoCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void getTitleTest() {
        Command command = new InfoCommand(Mockito.mock(GameModel.class));
        command.setTitle("test2");
        assertEquals(command.getTitle(), "test2");
    }
}
