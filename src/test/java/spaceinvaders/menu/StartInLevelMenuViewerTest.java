package spaceinvaders.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.menu.StartInLevelMenuController;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.StartCommand;
import spaceinvaders.model.menu.StartInLevelMenuModel;
import spaceinvaders.view.menu.StartInLevelMenuViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StartInLevelMenuViewerTest {
    GameModel gameModel;
    StartInLevelMenuModel model;
    StartInLevelMenuViewer viewer;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        StartInLevelMenuModel.reset();
        StartInLevelMenuViewer.reset();
        StartCommand startCommand = Mockito.mock(StartCommand.class);
        model = StartInLevelMenuModel.getInstance(gameModel, startCommand);
        viewer = StartInLevelMenuViewer.getInstance(model);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(viewer, StartInLevelMenuViewer.getInstance(model));
    }

    @Test
    public void getInstanceTest2(){
        StartInLevelMenuModel modelMock = Mockito.mock(StartInLevelMenuModel.class);
        assertEquals(viewer, StartInLevelMenuViewer.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(viewer, StartInLevelMenuViewer.getInstance());
    }

    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock, Mockito.times(2)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(6, 14, "");
        assertEquals("", model.getLevel());
    }
    @Test
    public void draw2(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        StartInLevelMenuController.reset();
        StartInLevelMenuController controller = StartInLevelMenuController.getInstance(model);
        controller.processKey(new KeyStroke('1', false, false));
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(6, 14, "1");
    }
    @Test
    public void resetTest(){
        StartInLevelMenuViewer.reset();
        assertNull(StartInLevelMenuViewer.getInstance());
    }
}
