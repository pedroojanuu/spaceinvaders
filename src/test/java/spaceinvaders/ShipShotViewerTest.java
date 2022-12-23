package spaceinvaders;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ShipShotModel;
import spaceinvaders.view.ShipShotViewer;

public class ShipShotViewerTest {
    ShipShotModel shipShot;
    ShipShotViewer shipShotViewer;
    @BeforeEach
    public void helper() {
        shipShot = new ShipShotModel(new PositionModel(2, 2));
        shipShotViewer = new ShipShotViewer(shipShot);
    }

    @Test
    public void draw() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        shipShot.getViewer().draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(2, 2, TextCharacter.fromCharacter('^')[0]);
    }
}
