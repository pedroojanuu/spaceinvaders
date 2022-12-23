package spaceinvaders.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.InfoMenuModel;
import spaceinvaders.view.menu.InfoMenuViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoMenuViewerTest {
    GameModel gameModel;
    InfoMenuModel model;
    InfoMenuViewer viewer;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        InfoMenuModel.reset();
        InfoMenuViewer.reset();
        model = InfoMenuModel.getInstance(gameModel);
        viewer = InfoMenuViewer.getInstance(model);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(viewer, InfoMenuViewer.getInstance(model));
    }

    @Test
    public void getInstanceTest2(){
        InfoMenuModel modelMock = Mockito.mock(InfoMenuModel.class);
        assertEquals(viewer, InfoMenuViewer.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(viewer, InfoMenuViewer.getInstance());
    }

    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock, Mockito.times(7)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(graphicsMock, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(6, 20, "> Exit");
    }
    @Test
    public void resetTest(){
        InfoMenuViewer.reset();
        assertEquals(null, InfoMenuViewer.getInstance());
    }
}
