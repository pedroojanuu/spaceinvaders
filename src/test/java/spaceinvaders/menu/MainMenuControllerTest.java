package spaceinvaders.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.MainMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.MainMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuControllerTest {
    GameModel gameModel;
    MainMenuModel modelMock;
    MainMenuController controller;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        MainMenuModel.reset();
        MainMenuController.reset();
        modelMock = Mockito.mock(MainMenuModel.class);
        controller = MainMenuController.getInstance(modelMock);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(controller, MainMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest2(){
        assertEquals(controller, MainMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(controller, MainMenuController.getInstance());
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
    public void resetTest(){
        MainMenuController.reset();
        assertEquals(null, MainMenuController.getInstance());
    }

}
