package spaceinvaders.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.AlienGroupModel;
import spaceinvaders.model.AlienModel;
import spaceinvaders.model.ElementModel;

public class AlienGroupViewer implements ElementViewer {
    public AlienGroupModel model;
    public AlienGroupViewer(AlienGroupModel model) {
        this.model = model;
    }
    @Override
    public void draw(TextGraphics graphics) {
        for (AlienModel alien : model.getAliens()) {
            alien.getViewer().draw(graphics);
        }
    }
    @Override
    public ElementModel getModel() {
        return model;
    }
}
