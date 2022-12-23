package spaceinvaders;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.AlienGroupModel;
import spaceinvaders.model.AlienModel;
import spaceinvaders.model.ArenaModel;
import spaceinvaders.view.AlienGroupViewer;

public class AlienGroupViewerTest {

    @Test
    public void drawTest() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        ArenaModel arenaModel = Mockito.mock(ArenaModel.class);
         AlienGroupModel alienGroupModel;
         alienGroupModel = new AlienGroupModel(arenaModel);
         for ( AlienModel alien : alienGroupModel.getAliens()) {
             alien.getViewer().draw(graphics);
         }



    }
    @Test
    public void getModelTest() {
        AlienGroupModel alienGroupModel = Mockito.mock(AlienGroupModel.class);
        AlienGroupViewer alienGroupViewer = new AlienGroupViewer(alienGroupModel);
        Assertions.assertEquals(alienGroupModel, alienGroupViewer.getModel());
    }
}
