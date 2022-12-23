package spaceinvaders;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spaceinvaders.model.AlienModel;
import spaceinvaders.model.PositionModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienViewerTest {
    private AlienModel alien;

    @BeforeEach
    public void helper(){
        this.alien = new AlienModel(new PositionModel(20, 9), "&","#FFFFFF");
    }

    @Test
    public void draw() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        alien.getViewer().draw(graphics);
        Mockito.verify(graphics).setForegroundColor(TextColor.Factory.fromString(alien.getColor()));
        Mockito.verify(graphics, Mockito.times(1)).putString(new TerminalPosition(20, 9), alien.getSymbol());
    }

    @Test
    public void getModel() {
        assertEquals(alien, alien.getViewer().getModel());
    }
}
