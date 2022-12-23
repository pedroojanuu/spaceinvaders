package spaceinvaders.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import spaceinvaders.model.ArenaModel;

public class ArenaController implements Controller {
    private ShipController ship;
    private ArenaModel model;

    public ArenaController(ArenaModel model) {
        this.model = model;
        this.ship = new ShipController(model.getShip());
    }

    @Override
    public void processKey(KeyStroke key) {
        if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') ||
                key.getKeyType() == KeyType.Escape) {
            model.getExitCommand().execute();
        }
        ship.processKey(key);
    }

    public ArenaModel getModel(){
        return model;
    }
}
