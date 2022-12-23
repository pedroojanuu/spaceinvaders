package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.MainMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.ExitToMenuCommand;
import spaceinvaders.model.menu.MainMenuModel;
import spaceinvaders.model.menu.MenuStateModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitToMenuCommandTest {
    GameModel gameModel;
    ExitToMenuCommand exitToMenuCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        exitToMenuCommand = new ExitToMenuCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(exitToMenuCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(exitToMenuCommand.getTitle(), "Exit to Menu");
    }

    @Test
    public void executeTest() {
        exitToMenuCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        exitToMenuCommand.setGameModel(gameModel2);
        exitToMenuCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        MainMenuModel model = MainMenuModel.getInstance(gameModel);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), MainMenuController.getInstance(model));
    }
}
