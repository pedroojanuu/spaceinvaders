package spaceinvaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class AlienModelTest {
    private AlienModel alien;

    @BeforeEach
    public void helper(){
        this.alien = new AlienModel(new PositionModel(20, 9), "&","#FFFFFF");
    }
    @Test
    public void damage() {
        alien.damage();
        assertEquals(false, alien.isAlive());
    }

    @Test
    public void isAlive() {
        assertEquals(true,alien.isAlive());
        alien.damage();
        assertEquals(false,alien.isAlive());

    }

    @Test
    public void getWidth() {
        assertEquals(1,alien.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(1,alien.getHeight());
    }

    @Test
    public void getSymbol() {
        assertEquals("&",alien.getSymbol());
    }

    @Test
    public void getColor() {
        assertEquals("#FFFFFF",alien.getColor());
    }

    @Test
    public void collideWith() {
        assertEquals(true, alien.collideWith( new ShipShotModel(new PositionModel(20, 9))));
    }

    @Test
    public void collideWith2() {
        assertEquals(false, alien.collideWith( new ShipShotModel(new PositionModel(20, 10))));
    }

    @Test
    public void fireTest() {
        ShotObserverModel observer = Mockito.mock(ShotObserverModel.class);
        alien.addObserver(observer);
        alien.fire(1);
        verify(observer, Mockito.times(1)).update(any(AlienShotModel.class));
    }
}
