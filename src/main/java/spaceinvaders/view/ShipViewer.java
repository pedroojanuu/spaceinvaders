package spaceinvaders.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.ElementModel;
import spaceinvaders.model.PositionModel;
import spaceinvaders.model.ShipModel;

public class ShipViewer implements ElementViewer {
    private ShipModel ship;
    public ShipViewer(ShipModel ship) {
        this.ship = ship;
    }
    @Override
    public void draw(TextGraphics graphics) {
        ship.resetDrawnPositions();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC300"));
        graphics.putString(ship.getX(), ship.getUpperBound(), "\"");
        ship.addDrawnPosition(new PositionModel(ship.getX(), ship.getUpperBound()));
        graphics.putString(ship.getRightBound(), ship.getUpperBound()+1, ",");
        ship.addDrawnPosition(new PositionModel(ship.getRightBound(), ship.getUpperBound()+1));
        for (int i = ship.getLeftBound()+1; i <= ship.getRightBound()-1; i++) {
            graphics.putString(i, ship.getUpperBound()+1, "=");
            ship.addDrawnPosition(new PositionModel(i, ship.getUpperBound()+1));
        }
        for (int i = ship.getLeftBound(); i <= ship.getRightBound(); i++) {
            graphics.putString(i, ship.getUpperBound()+2, "=");
            ship.addDrawnPosition(new PositionModel(i, ship.getUpperBound()+2));
        }
    }

    @Override
    public ElementModel getModel() {
        return ship;
    }
}
