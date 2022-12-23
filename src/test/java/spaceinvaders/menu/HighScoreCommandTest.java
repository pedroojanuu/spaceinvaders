package spaceinvaders.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.HighScoreMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.HighScoreCommand;
import spaceinvaders.model.menu.HighScoreMenuModel;
import spaceinvaders.model.menu.MenuStateModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighScoreCommandTest {
    GameModel gameModel;
    HighScoreCommand highScoreCommand;
    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        highScoreCommand = new HighScoreCommand(gameModel);
    }

    @Test
    public void getGameModelTest() {
        assertEquals(highScoreCommand.getGameModel(), gameModel);
    }

    @Test
    public void getTitle() {
        assertEquals(highScoreCommand.getTitle(), "HighScores");
    }

    @Test
    public void executeTest() {
        highScoreCommand.execute();
        Mockito.verify(gameModel, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void executeTest2() {
        GameModel gameModel2 = new GameModel();
        highScoreCommand.setGameModel(gameModel2);
        highScoreCommand.execute();
        MenuStateModel menuStateModel = (MenuStateModel) gameModel2.getState();
        HighScoreMenuModel model = HighScoreMenuModel.getInstance(gameModel);
        assertEquals(menuStateModel.getModel(), model);
        assertEquals(menuStateModel.getController(), HighScoreMenuController.getInstance(model));
    }
}
