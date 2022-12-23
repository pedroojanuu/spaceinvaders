package spaceinvaders.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.HighScoreMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.HighScoreMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighScoreMenuControllerTest {
    GameModel gameModel;
    HighScoreMenuModel modelMock;
    HighScoreMenuController controller;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        HighScoreMenuModel.reset();
        HighScoreMenuController.reset();
        modelMock = Mockito.mock(HighScoreMenuModel.class);
        controller = HighScoreMenuController.getInstance(modelMock);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(controller, HighScoreMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest2(){
        assertEquals(controller, HighScoreMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(controller, HighScoreMenuController.getInstance());
    }

    @Test
    public void processKeyQ(){
        Command exitCommandMock = Mockito.mock(Command.class);
        Mockito.when(modelMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke('q', false, false));
        Mockito.verify(exitCommandMock, Mockito.times(1)).execute();
    }
    @Test
    public void processKeyTestEsc(){
        Command exitCommandMock = Mockito.mock(Command.class);
        Mockito.when(modelMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke(KeyType.Escape));
        Mockito.verify(exitCommandMock, Mockito.times(1)).execute();
    }
    @Test
    public void processKeyTestEnter(){
        Command exitCommandMock = Mockito.mock(Command.class);
        Mockito.when(modelMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke(KeyType.Enter));
        Mockito.verify(exitCommandMock, Mockito.times(1)).execute();
    }
    @Test
    public void resetTest(){
        HighScoreMenuController.reset();
        assertEquals(null, HighScoreMenuController.getInstance());
    }

}
