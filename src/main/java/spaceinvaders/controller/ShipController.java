package spaceinvaders.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.model.ShipModel;

public class ShipController {
    private ShipModel ship;
    public ShipController (ShipModel ship) {
        this.ship = ship;
    }
    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character) {
            switch (key.getCharacter()) {
                case 'a':
                    if (!ship.canIMove(true)) return;
                    ship.setX(ship.getX() - 1);
                    ship.setLeftBound(ship.getLeftBound()-1);
                    ship.setRightBound(ship.getRightBound()-1);
                    break;
                case 'd':
                    if (!ship.canIMove(false)) return;
                    ship.setX(ship.getX() + 1);
                    ship.setLeftBound(ship.getLeftBound()+1);
                    ship.setRightBound(ship.getRightBound()+1);
                    break;
                case ' ':
                    ship.fire();
                    break;
                default: break;
            }
        } else {
            KeyType keyType = key.getKeyType();
            switch (keyType) {
                case ArrowLeft:
                    if (!ship.canIMove(true)) return;
                    ship.setX(ship.getX() - 1);
                    ship.setLeftBound(ship.getLeftBound()-1);
                    ship.setRightBound(ship.getRightBound()-1);
                    break;
                case ArrowRight:
                    if (!ship.canIMove(false)) return;
                    ship.setX(ship.getX() + 1);
                    ship.setLeftBound(ship.getLeftBound()+1);
                    ship.setRightBound(ship.getRightBound()+1);
                    break;
                case ArrowUp:
                    ship.fire(); break;
                default: break;
            }
        }
    }
}
