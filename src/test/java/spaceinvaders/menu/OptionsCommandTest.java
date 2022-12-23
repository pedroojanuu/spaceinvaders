package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.OptionsMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.MenuStateModel;
import spaceinvaders.model.menu.OptionsCommand;
import spaceinvaders.model.menu.OptionsMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsCommandTest {
    GameModel gameModel;
    OptionsCommand optionsCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        optionsCommand = new OptionsCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(optionsCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(optionsCommand.getTitle(), "Options");
    }

    @Test
    public void executeTest() {
        optionsCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        optionsCommand.setGameModel(gameModel2);
        optionsCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        OptionsMenuModel model = OptionsMenuModel.getInstance(gameModel);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), OptionsMenuController.getInstance(model));
    }
}
