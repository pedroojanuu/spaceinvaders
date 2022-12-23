package spaceinvaders.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.AlienModel;
import spaceinvaders.model.ElementModel;

public class AlienViewer implements ElementViewer {
    AlienModel model;

    public AlienViewer(AlienModel model) {
        this.model = model;
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(model.getColor()));
        graphics.putString(new TerminalPosition(model.getX(), model.getY()), model.getSymbol());
    }

    @Override
    public ElementModel getModel() {
        return model;
    }
}
