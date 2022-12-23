package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.InfoMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.InfoCommand;
import spaceinvaders.model.menu.InfoMenuModel;
import spaceinvaders.model.menu.MenuStateModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoCommandTest {
    GameModel gameModel;
    InfoCommand infoCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        infoCommand = new InfoCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(infoCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(infoCommand.getTitle(), "Info");
    }

    @Test
    public void executeTest() {
        infoCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        infoCommand.setGameModel(gameModel2);
        infoCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        InfoMenuModel model = InfoMenuModel.getInstance(gameModel);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), InfoMenuController.getInstance(model));
    }
}
