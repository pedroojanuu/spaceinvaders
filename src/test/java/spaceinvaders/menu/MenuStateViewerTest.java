package spaceinvaders.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.view.menu.MenuStateViewer;
import spaceinvaders.view.menu.MenuViewer;

public class MenuStateViewerTest {
    MenuViewer menuViewer;
    MenuStateViewer menuStateViewer;
    @BeforeEach
    public void helper(){
        menuViewer = Mockito.mock(MenuViewer.class);
        menuStateViewer = new MenuStateViewer(menuViewer);
    }

    @Test
    public void draw(){
        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        menuStateViewer.draw(graphicsMock);
        Mockito.verify(menuViewer, Mockito.times(1)).draw(graphicsMock);
    }
}
