package spaceinvaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceinvaders.model.AlienShotModel;
import spaceinvaders.model.PositionModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AlienShotModelTest {
    AlienShotModel alienShot;
    @BeforeEach
    public void helper() {
        alienShot = new AlienShotModel(new PositionModel(2, 2),1);
    }

    @Test
    public void update() {
        alienShot.update();
        assertEquals(3, alienShot.getPosition().getY());
    }

    @Test
    public void isAlive(){
        assertFalse(alienShot.isAlive());
    }

    @Test
    public void getSpeed(){
        assertEquals(1, alienShot.getSpeed());
    }
}
