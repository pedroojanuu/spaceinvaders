package spaceinvaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ShipShotModel;
import spaceinvaders.view.ShipShotViewer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShipShotModelTest {
    ShipShotModel shipShot;
    ShipShotViewer shipShotViewer;

    @BeforeEach
    public void helper() {
        shipShot = new ShipShotModel(new PositionModel(2, 2));
        shipShotViewer = new ShipShotViewer(shipShot);
    }
    @Test
    public void update() {
        shipShot.update();
        assertEquals(1, shipShot.getPosition().getY());
    }

    @Test
    public void isAlive(){
        assertFalse(shipShot.isAlive());
    }

    @Test
    public void getSpeed(){
        assertEquals(1, shipShot.getSpeed());
    }

    @Test
    public void getCharacter(){
        assertEquals('^', shipShot.getCharacter());
    }

    @Test
    public void isTangible(){
        assertFalse(shipShot.isTangible());
    }
}
