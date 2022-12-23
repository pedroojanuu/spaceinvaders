package spaceinvaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ShotModel;

public class ShotModelTest {
    ShotModel shotModel;
    PositionModel position;
    @BeforeEach
    public void helper() {
        this.position = new PositionModel(1, 1);
        this.shotModel = new ShotModel(position, 1, false, '_');
    }
    @Test
    public void getPositionTest() {
        Assertions.assertEquals(this.position, shotModel.getPosition());
    }
    @Test
    public void updateUpTest() {
        int expected = position.getY() + 1;
        shotModel.update();
        Assertions.assertEquals(expected, position.getY());
    }
    @Test
    public void updateDownTest() {
        this.shotModel = new ShotModel(position, 1, true, '_');
        int expected = position.getY() - 1;
        shotModel.update();
        Assertions.assertEquals(expected, position.getY());
    }
    @Test
    public void upDownTest() {
        Assertions.assertTrue(ShotModel.up);
        Assertions.assertFalse(ShotModel.down);
    }
    @Test
    public void isAliveTest() {
        Assertions.assertFalse(shotModel.isAlive());
    }
    @Test
    public void getSpeedTest() {
        Assertions.assertEquals(1, shotModel.getSpeed());
    }
    @Test
    public void getCharacterTest() {
        Assertions.assertEquals('_', shotModel.getCharacter());
    }
    @Test
    public void isTangibleTest() {
        Assertions.assertFalse(shotModel.isTangible());
    }
}
