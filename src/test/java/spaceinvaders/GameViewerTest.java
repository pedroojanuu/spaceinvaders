package spaceinvaders;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.GameModel;
import spaceinvaders.model.RunStateModel;
import spaceinvaders.view.GameViewer;
import spaceinvaders.view.RunStateViewer;

import java.io.IOException;

public class GameViewerTest {
    GameModel gameModel;
    GameViewer gameViewer;
    Screen screen;

    @BeforeEach
    public void helper() {
        gameModel = Mockito.mock(GameModel.class);
        screen = Mockito.mock(Screen.class);
        gameViewer = new GameViewer(gameModel, screen);
    }

    @Test
    public void drawTest() throws IOException {
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        RunStateModel state = Mockito.mock(RunStateModel.class);
        RunStateViewer viewer = Mockito.mock(RunStateViewer.class);
        Mockito.when(state.getViewer()).thenReturn(viewer);
        Mockito.when(gameModel.getState()).thenReturn(state);
        gameViewer.draw();
        Mockito.verify(viewer).draw(textGraphics);
    }

    @Test
    public void drawTest2() throws IOException {
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        RunStateModel state = Mockito.mock(RunStateModel.class);
        RunStateViewer viewer = Mockito.mock(RunStateViewer.class);
        Mockito.when(state.getViewer()).thenReturn(viewer);
        Mockito.when(gameModel.getState()).thenReturn(state);
        gameViewer.draw();
        Mockito.verify(screen, Mockito.times(1)).newTextGraphics();
        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }
}
