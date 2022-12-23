package spaceinvaders;

import org.junit.jupiter.api.Test;
import spaceinvaders.model.PositionModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionModelTest {
    @Test

    public void getX() {
        PositionModel positionModel = new PositionModel(1, 2);
        assertEquals(1, positionModel.getX());
    }
    @Test
    public void getY() {
        PositionModel positionModel = new PositionModel(1, 2);
        assertEquals(2, positionModel.getY());
    }
    @Test
    public void setX() {
        PositionModel positionModel = new PositionModel(1, 2);
        positionModel.setX(3);
        assertEquals(3, positionModel.getX());
    }
    @Test
    public void setY() {
        PositionModel positionModel = new PositionModel(1, 2);
        positionModel.setY(3);
        assertEquals(3, positionModel.getY());
    }
    @Test
    public void getX2() {
        PositionModel positionModelTemp = new PositionModel(1, 2);
        PositionModel positionModel = new PositionModel(positionModelTemp);
        assertEquals(1, positionModel.getX());
    }
    @Test
    public void getY2() {
        PositionModel positionModelTemp = new PositionModel(1, 2);
        PositionModel positionModel = new PositionModel(positionModelTemp);
        assertEquals(2, positionModel.getY());
    }
    @Test
    public void setX2() {
        PositionModel positionModelTemp = new PositionModel(1, 2);
        PositionModel positionModel = new PositionModel(positionModelTemp);
        positionModel.setX(3);
        assertEquals(3, positionModel.getX());
    }
    @Test
    public void setY2() {
        PositionModel positionModelTemp = new PositionModel(1, 2);
        PositionModel positionModel = new PositionModel(positionModelTemp);
        positionModel.setY(3);
        assertEquals(3, positionModel.getY());
    }

    public void testPositionModel() {
        PositionModel positionModel = new PositionModel(new PositionModel(1, 2));
        assertEquals(1, positionModel.getX());
        assertEquals(2, positionModel.getY());
    }
}
