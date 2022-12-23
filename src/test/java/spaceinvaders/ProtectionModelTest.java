package spaceinvaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ProtectionModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtectionModelTest {
    ProtectionModel protection;

    @BeforeEach
    public void helper() {
        protection = new ProtectionModel(new PositionModel(2, 2), 2);
    }

    @Test
    public void getWidth() {
        assertEquals(4, protection.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(2, protection.getHeight());
    }

    @Test
    public void damage() {
        protection.damage();
        assertEquals(1, protection.getLife());
    }

    @Test
    public void isAlive() {
        assertEquals(true, protection.isAlive());
        protection.damage();
        assertEquals(true, protection.isAlive());
        protection.damage();
        assertEquals(false, protection.isAlive());
    }

    @Test
    public void kill() {
        protection.kill();
        assertEquals(false, protection.isAlive());
    }
}
