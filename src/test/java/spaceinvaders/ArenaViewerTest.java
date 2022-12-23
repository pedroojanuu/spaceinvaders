package spaceinvaders;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.*;
import spaceinvaders.view.ArenaViewer;
import spaceinvaders.view.ElementViewer;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

public class ArenaViewerTest {
    private ArenaModel model;
    private ArenaViewer viewer;
    private GameModel gameModel;

    @BeforeEach
    public void helper() {
        model = new ArenaModel(gameModel);
        viewer = new ArenaViewer(model);
    }
    @Test
    public void draw1(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ElementModel elementModel = Mockito.mock(ElementModel.class);
        ElementViewer elementViewer = Mockito.mock(ElementViewer.class);
        Mockito.when(elementModel.getViewer()).thenReturn(elementViewer);
        model.getElements().add(elementModel);
        viewer.draw(graphics);
        drawLives1();
        Mockito.verify(elementViewer, Mockito.times(1)).draw(graphics);
    }

    @Test
    public void draw2(){
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ShotModel shotModel = Mockito.mock(ShotModel.class);
        ElementViewer elementViewer = Mockito.mock(ElementViewer.class);
        Mockito.when(shotModel.getViewer()).thenReturn(elementViewer);
        model.getShots().add(shotModel);
        viewer.draw(graphics);
        drawLives2();
        Mockito.verify(elementViewer, Mockito.times(1)).draw(graphics);
    }

    @Test
    public void draw3() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        viewer = new ArenaViewer(arenaModel);
        Mockito.when(arenaModel.getElements()).thenReturn(new ArrayList<>());
        Mockito.when(arenaModel.getShots()).thenReturn(new ArrayList<>());
        Mockito.when(arenaModel.getYouWon()).thenReturn(true);
        ShipModel ship = Mockito.mock(ShipModel.class);
        Mockito.when(arenaModel.getShip()).thenReturn(ship);
        Mockito.when(ship.getLives()).thenReturn(1);
        viewer.draw(graphics);
       drawLives3();
        Mockito.verify(graphics, Mockito.atLeast(3)).setForegroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.atLeast(2)).putString(any(Integer.class), any(Integer.class), any(String.class));
    }

    @Test
    public void drawLives1() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        viewer = new ArenaViewer(arenaModel);
        ShipModel ship = Mockito.mock(ShipModel.class);
        Mockito.when(arenaModel.getShip()).thenReturn(ship);
        Mockito.when(ship.getLives()).thenReturn(1);

        viewer.drawLives(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(4)).putString(any(Integer.class), any(Integer.class), any(String.class));
    }
    @Test
    public void drawLives2() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        viewer = new ArenaViewer(arenaModel);
        ShipModel ship = Mockito.mock(ShipModel.class);
        Mockito.when(arenaModel.getShip()).thenReturn(ship);
        Mockito.when(ship.getLives()).thenReturn(2);

        viewer.drawLives(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(4)).putString(any(Integer.class), any(Integer.class), any(String.class));
    }
    @Test
    public void drawLives3() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
        viewer = new ArenaViewer(arenaModel);
        ShipModel ship = Mockito.mock(ShipModel.class);
        Mockito.when(arenaModel.getShip()).thenReturn(ship);
        Mockito.when(ship.getLives()).thenReturn(3);

        viewer.drawLives(graphics);
        Mockito.verify(graphics, Mockito.times(4)).putString(any(Integer.class), any(Integer.class), any(String.class));
    }
}
