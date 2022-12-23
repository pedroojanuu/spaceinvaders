package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.StartInLevelMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.MenuStateModel;
import spaceinvaders.model.menu.StartCommand;
import spaceinvaders.model.menu.StartInLevelMenuCommand;
import spaceinvaders.model.menu.StartInLevelMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartInLevelMenuCommandTest {
    GameModel gameModel;
    StartInLevelMenuCommand startInLevelMenuCommand;
    StartCommand startCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        startCommand = Mockito.mock(StartCommand.class);
        startInLevelMenuCommand = new StartInLevelMenuCommand(gameModel, startCommand);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(startInLevelMenuCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(startInLevelMenuCommand.getTitle(), "Start In Level");
    }

    @Test
    public void executeTest() {
        startInLevelMenuCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        startInLevelMenuCommand.setGameModel(gameModel2);
        startInLevelMenuCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        StartInLevelMenuModel model = StartInLevelMenuModel.getInstance(gameModel, startCommand);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), StartInLevelMenuController.getInstance(model));
    }

    @Test
    public void getStartCommandTest() {
        assertEquals(startInLevelMenuCommand.getStartCommand(), startCommand);
    }
}
