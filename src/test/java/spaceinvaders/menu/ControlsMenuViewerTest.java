package spaceinvaders.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.ControlsMenuModel;
import spaceinvaders.view.menu.ControlsMenuViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlsMenuViewerTest {
    GameModel gameModel;
    ControlsMenuModel model;
    ControlsMenuViewer viewer;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        ControlsMenuModel.reset();
        ControlsMenuViewer.reset();
        model = ControlsMenuModel.getInstance(gameModel);
        viewer = ControlsMenuViewer.getInstance(model);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(viewer, ControlsMenuViewer.getInstance(model));
    }

    @Test
    public void getInstanceTest2(){
        ControlsMenuModel modelMock = Mockito.mock(ControlsMenuModel.class);
        assertEquals(viewer, ControlsMenuViewer.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(viewer, ControlsMenuViewer.getInstance());
    }

    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock, Mockito.times(6)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(6, 20, "> Exit");
    }
    @Test
    public void resetTest(){
        ControlsMenuViewer.reset();
        assertEquals(null, ControlsMenuViewer.getInstance());
    }
}
