package spaceinvaders;

import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.controller.ShipController;
import spaceinvaders.model.ShipModel;
import spaceinvaders.model.ShipShotModel;
import spaceinvaders.model.ShotObserverModel;

import static com.googlecode.lanterna.input.KeyType.ArrowLeft;
import static com.googlecode.lanterna.input.KeyType.ArrowRight;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ShipControllerTest {
    @Test
    public void processAKeyTest() {
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('a');
        int expectedLeftBound = model.getLeftBound()-1;
        int expectedRightBound = model.getRightBound()-1;
        int expectedX = model.getX()-1;
        ship.processKey(key);
        assertEquals(expectedLeftBound, model.getLeftBound());
        assertEquals(expectedRightBound, model.getRightBound());
        assertEquals(expectedX, model.getX());
    }
    @Test
    public void processDKeyTest() {
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn('d');
        int expectedLeftBound = model.getLeftBound()+1;
        int expectedRightBound = model.getRightBound()+1;
        int expectedX = model.getX()+1;
        ship.processKey(key);
        assertEquals(expectedLeftBound, model.getLeftBound());
        assertEquals(expectedRightBound, model.getRightBound());
        assertEquals(expectedX, model.getX());
    }
    @Test
    public void processSpaceKeyTest() {
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(key.getCharacter()).thenReturn(' ');
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        model.addObserver(observer);
        ship.processKey(key);
        verify(observer, Mockito.times(1)).update(any(ShipShotModel.class));
    }
    @Test
    public void processLeftArrowKeyTest() {
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(ArrowLeft);
        int expectedLeftBound = model.getLeftBound()-1;
        int expectedRightBound = model.getRightBound()-1;
        int expectedX = model.getX()-1;
        ship.processKey(key);
        assertEquals(expectedLeftBound, model.getLeftBound());
        assertEquals(expectedRightBound, model.getRightBound());
        assertEquals(expectedX, model.getX());
    }
    @Test
    public void processRightArrowKeyTest() {
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(ArrowRight);
        int expectedLeftBound = model.getLeftBound()+1;
        int expectedRightBound = model.getRightBound()+1;
        int expectedX = model.getX()+1;
        ship.processKey(key);
        assertEquals(expectedLeftBound, model.getLeftBound());
        assertEquals(expectedRightBound, model.getRightBound());
        assertEquals(expectedX, model.getX());
    }
    @Test
    public void processUpArrowKeyTest() {
        com.googlecode.lanterna.input.KeyStroke key = Mockito.mock(com.googlecode.lanterna.input.KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);
        ShipModel model = new ShipModel();
        ShipController ship = new ShipController(model);
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        model.addObserver(observer);
        ship.processKey(key);
        verify(observer, Mockito.times(1)).update(any(ShipShotModel.class));
    }
}
