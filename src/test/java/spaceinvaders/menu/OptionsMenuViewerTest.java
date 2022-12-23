package spaceinvaders.menu;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.menu.OptionsMenuModel;
import spaceinvaders.view.menu.OptionsMenuViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsMenuViewerTest {
    GameModel gameModel;
    OptionsMenuModel model;
    OptionsMenuViewer viewer;
    @BeforeEach
    public void helper(){
        gameModel = Mockito.mock(GameModel.class);
        OptionsMenuModel.reset();
        OptionsMenuViewer.reset();
        model = OptionsMenuModel.getInstance(gameModel);
        viewer = OptionsMenuViewer.getInstance(model);
    }

    @Test
    public void getInstanceTest1(){
        assertEquals(viewer, OptionsMenuViewer.getInstance(model));
    }

    @Test
    public void getInstanceTest2(){
        OptionsMenuModel modelMock = Mockito.mock(OptionsMenuModel.class);
        assertEquals(viewer, OptionsMenuViewer.getInstance(modelMock));
    }

    @Test
    public void getInstanceTest3(){
        assertEquals(viewer, OptionsMenuViewer.getInstance());
    }


    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        viewer.draw(graphicsMock);
        Mockito.verify(graphicsMock, Mockito.times(1)).putString(8, 8, "Options");
        Mockito.verify(graphicsMock, Mockito.atLeast(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(graphicsMock, Mockito.atLeast(4)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(graphicsMock, Mockito.atLeast(1)).setForegroundColor(TextColor.Factory.fromString("#FFC300"));

        for (int i = 0; i < model.getCommands().size(); i++) {
            if (model.getCommands().get(i) != null) {
                if (i == model.getSelectedCommandInt()) {
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(8, 12 + i, "> " + model.getCommands().get(i).getTitle());
                } else {
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(8, 12 + i, model.getCommands().get(i).getTitle());
                }
            }
        }

    }

    @Test
    public void draw2(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        model.downSelectedCommand();
        viewer.draw(graphicsMock);
        for (int i = 0; i < model.getCommands().size(); i++) {
            if (model.getCommands().get(i) != null) {
                if (i == model.getSelectedCommandInt()) {
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(8, 12 + i, "> " + model.getCommands().get(i).getTitle());
                } else {
                    Mockito.verify(graphicsMock, Mockito.times(1)).putString(8, 12 + i, model.getCommands().get(i).getTitle());
                }
            }
        }
    }

    @Test
    public void resetTest(){
        OptionsMenuViewer.reset();
        assertEquals(null, OptionsMenuViewer.getInstance());
    }
}
