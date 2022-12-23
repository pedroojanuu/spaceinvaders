package spaceinvaders.model;

public class ShipShotModel extends ShotModel {
    public ShipShotModel(PositionModel position) {
        super(position, 1F, up, '^');
    }
}
