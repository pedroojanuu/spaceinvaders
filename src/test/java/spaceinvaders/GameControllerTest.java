package spaceinvaders;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.Controller;
import spaceinvaders.controller.GameController;
import spaceinvaders.controller.RunStateController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.RunStateModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {
    GameModel gameModel;
    GameController gameController;
    Screen screen;
    RunStateModel state;
    Controller stateController;

    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        state = Mockito.mock(RunStateModel.class);
        stateController = Mockito.mock(Controller.class);
        Mockito.when(state.getController()).thenReturn(stateController);
        Mockito.when(gameModel.getState()).thenReturn(state);
        screen = Mockito.mock(Screen.class);
        gameController = new GameController(gameModel, screen);
    }

    @Test
    public void setStateTest() {
        RunStateController stateController = Mockito.mock(RunStateController.class);
        gameController.setState(stateController);
        assertEquals(gameController.getState(), stateController);
    }

    @Test
    public void processKeyTest() throws Exception {
        KeyStroke key = new KeyStroke('a', false, false);
        Mockito.when(screen.pollInput()).thenReturn(key);
        RunStateController runStateController = Mockito.mock(RunStateController.class);
        gameController.setState(runStateController);
        Mockito.when(runStateController.getController()).thenReturn(stateController);
        gameController.processKey();
        Mockito.verify(screen, Mockito.times(1)).pollInput();
        Mockito.verify(runStateController).processKey(key);
    }

    @Test
    public void processKeyTest2() throws Exception {
        RunStateController runStateController = Mockito.mock(RunStateController.class);
        gameController.setState(runStateController);
        Mockito.when(runStateController.getController()).thenReturn(stateController);
        KeyStroke key = new KeyStroke('a', false, false);
        Mockito.when(screen.pollInput()).thenReturn(key);
        gameController.processKey();
        KeyStroke key2 = new KeyStroke('a', false, false);
        Mockito.when(screen.pollInput()).thenReturn(key2).thenReturn(null);
        gameController.processKey();
        Mockito.verify(screen, Mockito.times(3)).pollInput();
        Mockito.verify(runStateController).processKey(key);
    }

    @Test
    public void processKeyTest3() throws Exception {
        KeyStroke key = new KeyStroke('a', false, false);
        Mockito.when(screen.pollInput()).thenReturn(key);
        RunStateController runStateController = Mockito.mock(RunStateController.class);
        gameController.setState(runStateController);
        gameController.processKey();
        Mockito.verify(screen, Mockito.times(1)).pollInput();
        Mockito.verify(runStateController, Mockito.times(0)).processKey(key);
    }
}
