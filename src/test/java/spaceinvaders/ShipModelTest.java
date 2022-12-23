package spaceinvaders;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ShipModelTest {
    @Test
    public void ShipModelTest() {
        ShipModel underTest = new ShipModel(10);

        assertEquals(10, underTest.getPosition().getX());
        assertEquals(22, underTest.getPosition().getY());
        assertEquals(6, underTest.getLeftBound());
        assertEquals(14, underTest.getRightBound());

    }
    @Test
    public void getXTest() {
        ShipModel ship = new ShipModel();
        assertEquals(24, ship.getX());

    }
    @Test
    public void getYTest() {
        ShipModel ship = new ShipModel();
        assertEquals(22, ship.getY());
    }
    @Test
    public void setYTest() {
        ShipModel ship = new ShipModel();
        ship.setY(10);
        assertEquals(10, ship.getY());
    }
    @Test
    public void getLeftBoundTest() {
        ShipModel ship = new ShipModel();
        assertEquals(22, ship.getLeftBound());
    }
    @Test
    public void getRightBoundTest() {
        ShipModel ship = new ShipModel();
        assertEquals(26, ship.getRightBound());
    }

    @Test
    public void getUpperBoundTest() {
        ShipModel ship = new ShipModel();
        assertEquals(22, ship.getUpperBound());
    }

    @Test
    public void getLowerBoundTest() {
        ShipModel ship = new ShipModel();
        assertEquals(25, ship.getLowerBound());
    }


    @Test
    public void addObserverTest() {
        ShipModel ship = new ShipModel();
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        HashSet<ShotObserverModel> expected = ship.getObservers();
        expected.add(observer);
        ship.addObserver(observer);
        assertEquals(expected.size(), ship.getObservers().size());
    }
    @Test
    public void removeObserverTest() {
        ShipModel ship = new ShipModel();
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        ship.addObserver(observer);
        HashSet<ShotObserverModel> expected = ship.getObservers();
        expected.remove(observer);
        ship.removeObserver(observer);
        assertEquals(expected.size(), ship.getObservers().size());
    }
    @Test
    public void notifyObserversTest() {
        ShipModel ship = new ShipModel();
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        ship.addObserver(observer);
        ShotModel shot = Mockito.mock(ShotModel.class);
        ship.notifyObservers(shot);
        verify(observer, Mockito.times(1)).update(shot);
    }

    @Test
    public void canIMoveTestLeftTrue() {
        ShipModel ship = new ShipModel();
        assertTrue(ship.canIMove(true));
    }
    @Test
    public void canIMoveTestRightTrue() {
        ShipModel ship = new ShipModel();
        assertTrue(ship.canIMove(false));
    }
    @Test
    public void canIMoveTestLeftFalse() {
        ShipModel ship = new ShipModel(-5);
        assertFalse(ship.canIMove(true));
    }
    @Test
    public void canIMoveTestRightFalse() {
        ShipModel ship = new ShipModel(94);
        assertFalse(ship.canIMove(false));
    }
    @Test
    public void fireTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ShipModel ship = new ShipModel();
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        ship.addObserver(observer);
        ship.fire();
        verify(observer, Mockito.times(1)).update(any(ShipShotModel.class));
    }
    @Test
    public void isAliveTest() {
        ShipModel ship = new ShipModel();
        assertTrue(ship.isAlive());
        ship.damage();
        ship.damage();
        ship.damage();
        assertFalse(ship.isAlive());
    }
    @Test
    public void damageTest() {
        ShipModel ship = new ShipModel();
        ship.damage();
        assertEquals(2,ship.getLives());
    }

    @Test
    public void getLivesTest() {
        ShipModel ship = new ShipModel();
        assertEquals(3,ship.getLives());
    }
    @Test
    public void getWidthTest() {
        ShipModel ship = new ShipModel();
        assertEquals(5,ship.getWidth());

    }
    @Test
    public void getHeightTest() {
        ShipModel ship = new ShipModel();
        assertEquals(4,ship.getHeight());
    }
    @Test
    public void collideWith1() {
        ShipModel ship = new ShipModel();
        ship.addDrawnPosition(new PositionModel(24,22));
        assertTrue(ship.collideWith(new AlienShotModel(new PositionModel(24,22),1)));
    }

    @Test
    public void collideWith2() {
        ShipModel ship = new ShipModel();
        ship.addDrawnPosition(new PositionModel(24,22));
        assertFalse(ship.collideWith(new AlienShotModel(new PositionModel(24,21),1)));

    }
}
