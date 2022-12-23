package spaceinvaders.menu;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.StartInLevelMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.Command;
import spaceinvaders.model.menu.StartCommand;
import spaceinvaders.model.menu.StartInLevelMenuModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartInLevelMenuControllerTest {
    GameModel gameModel;
    StartInLevelMenuModel modelMock;
    StartInLevelMenuController controller;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        StartInLevelMenuModel.reset();
        StartInLevelMenuController.reset();
        modelMock = Mockito.mock(StartInLevelMenuModel.class);
        controller = StartInLevelMenuController.getInstance(modelMock);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(controller, StartInLevelMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest2(){
        assertEquals(controller, StartInLevelMenuController.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(controller, StartInLevelMenuController.getInstance());
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
    public void processKeyTestNumber(){
        Command exitCommandMock = Mockito.mock(Command.class);
        Mockito.when(modelMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke('1', false, false));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("1");
        controller.processKey(new KeyStroke('9', false, false));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("19");
        controller.processKey(new KeyStroke(KeyType.Backspace));
        Mockito.verify(modelMock, Mockito.times(2)).setLevel("1");
        controller.processKey(new KeyStroke('2', false, false));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("12");
        controller.processKey(new KeyStroke('3', false, false));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("123");
        controller.processKey(new KeyStroke(KeyType.Backspace));
        Mockito.verify(modelMock, Mockito.times(2)).setLevel("12");
        assertEquals("12", controller.getLevel());
    }

    @Test
    public void processKeyTestLetter(){
        Command exitCommandMock = Mockito.mock(Command.class);
        Mockito.when(modelMock.getExitCommand()).thenReturn(exitCommandMock);
        controller.processKey(new KeyStroke('a', false, false));
        assertEquals("", controller.getLevel());
        Mockito.verify(modelMock, Mockito.never()).setLevel("a");
        controller.processKey(new KeyStroke('9', false, false));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("9");
        controller.processKey(new KeyStroke(KeyType.Backspace));
        Mockito.verify(modelMock, Mockito.times(1)).setLevel("");
        controller.processKey(new KeyStroke('-', false, false));
        assertEquals("", controller.getLevel());
        Mockito.verify(modelMock, Mockito.never()).setLevel("-");
        controller.processKey(new KeyStroke(' ', false, false));
        assertEquals("", controller.getLevel());
        Mockito.verify(modelMock, Mockito.never()).setLevel(" ");
    }

    @Test
    public void processKeyTestEnter(){
        StartCommand startCommandMock = Mockito.mock(StartCommand.class);
        StartInLevelMenuModel model2 = StartInLevelMenuModel.getInstance(gameModel, startCommandMock);
        controller.setModel(model2);

        controller.processKey(new KeyStroke('4', false, false));
        controller.processKey(new KeyStroke('2', false, false));
        assertEquals("42", controller.getLevel());
        controller.processKey(new KeyStroke(KeyType.Enter));
        assertEquals("", model2.getLevel());
        Mockito.verify(startCommandMock, Mockito.times(1)).setLevel(42);
        Mockito.verify(startCommandMock, Mockito.times(1)).execute();
    }

    @Test
    public void resetTest(){
        StartInLevelMenuController.reset();
        assertEquals(null, StartInLevelMenuController.getInstance());
    }
}
