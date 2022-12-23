package spaceinvaders.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.OptionsMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.OptionsMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsMenuControllerTest {
    GameModel gameModel;
    OptionsMenuModel modelMock;
    OptionsMenuController controller;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        OptionsMenuModel.reset();
        OptionsMenuController.reset();
        modelMock = Mockito.mock(OptionsMenuModel.class);
        controller = OptionsMenuController.getInstance(modelMock);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(controller, OptionsMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest2(){
        assertEquals(controller, OptionsMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(controller, OptionsMenuController.getInstance());
    }

    @Test
    public void processKeyTestArrowUp(){
        controller.processKey(new KeyStroke(KeyType.ArrowUp));
        Mockito.verify(modelMock, Mockito.times(1)).upSelectedCommand();
    }
    @Test
    public void processKeyTestArrowDown(){
        controller.processKey(new KeyStroke(KeyType.ArrowDown));
        Mockito.verify(modelMock, Mockito.times(1)).downSelectedCommand();
    }
    @Test
    public void processKeyTestEnter(){
        Command ret = Mockito.mock(Command.class);
        Mockito.when(modelMock.getSelectedCommand()).thenReturn(ret);
        controller.processKey(new KeyStroke(KeyType.Enter));
        Mockito.verify(ret, Mockito.times(1)).execute();
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
    public void resetTest(){
        OptionsMenuController.reset();
        assertEquals(null, OptionsMenuController.getInstance());
    }

}

