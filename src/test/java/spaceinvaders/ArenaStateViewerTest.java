package spaceinvaders;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.view.ArenaStateViewer;
import spaceinvaders.view.ArenaViewer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ArenaStateViewerTest {
    @Test
    public void drawTest() {
        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        ArenaStateViewer arenaStateViewer = new ArenaStateViewer(arenaViewer);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        arenaStateViewer.draw(graphics);
        verify(arenaViewer, times(1)).draw(graphics);
    }
}
