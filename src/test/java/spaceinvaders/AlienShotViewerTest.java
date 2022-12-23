package spaceinvaders;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.AlienShotModel;
import spaceinvaders.model.PositionModel;
import spaceinvaders.view.AlienShotViewer;

public class AlienShotViewerTest {
    @Test
    public void alienShotViewer() {
        AlienShotModel shotModel = Mockito.mock(AlienShotModel.class);
        AlienShotViewer alienShotViewer = new AlienShotViewer(shotModel);
        Assertions.assertEquals(shotModel, alienShotViewer.getModel());
    }
    @Test
    public void draw() {
        AlienShotModel alienShot = new AlienShotModel(new PositionModel(2, 2), 1);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        alienShot.getViewer().draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(2, 2, TextCharacter.fromCharacter('_')[0]);
    }
}
