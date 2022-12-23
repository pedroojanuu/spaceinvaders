package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.ControlsMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.ControlsCommand;
import spaceinvaders.model.menu.ControlsMenuModel;
import spaceinvaders.model.menu.MenuStateModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlsCommandTest {
    GameModel gameModel;
    ControlsCommand controlsCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        controlsCommand = new ControlsCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(controlsCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(controlsCommand.getTitle(), "Controls");
    }

    @Test
    public void executeTest() {
        controlsCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        controlsCommand.setGameModel(gameModel2);
        controlsCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        ControlsMenuModel model = ControlsMenuModel.getInstance(gameModel);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), ControlsMenuController.getInstance(model));
    }
}
