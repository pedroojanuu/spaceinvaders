package spaceinvaders;


import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ShotModel;
import spaceinvaders.view.ShotViewer;

public class ShotViewerTest {

    private ShotViewer shotViewer;
    private ShotModel shotModel;

    @BeforeEach
    public void helper() {
        this.shotModel = Mockito.mock(ShotModel.class);
        Mockito.when(shotModel.getPosition()).thenReturn(new PositionModel(1, 1));
        Mockito.when(shotModel.getCharacter()).thenReturn(' ');
        this.shotViewer = new ShotViewer(shotModel);
    }

    @Test
    public void drawTest() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        shotViewer.draw(graphics);
        Mockito.verify(graphics, Mockito.times(1)).setCharacter(1, 1, TextCharacter.fromCharacter(' ')[0]);
    }

    @Test
    public void getModelTest() {
        Assertions.assertEquals(shotModel, shotViewer.getModel());
    }
}
