package spaceinvaders.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import spaceinvaders.model.ElementModel;
import spaceinvaders.model.ProtectionModel;

public class ProtectionViewer implements ElementViewer {
    ProtectionModel model;

    public ProtectionViewer(ProtectionModel model){
        this.model=model;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.drawRectangle(new TerminalPosition(model.getX(), model.getY()), new TerminalSize(model.getWidth(), model.getHeight()), TextCharacter.fromCharacter('=')[0]);
        graphics.drawRectangle(new TerminalPosition(model.getX() + 1, model.getY() + model.getHeight() - 1), new TerminalSize(model.getWidth() - 2, 1), TextCharacter.fromCharacter(' ')[0]);
        drawNumber(graphics);
    }

    public void drawNumber(TextGraphics graphics) {
        int firstDigitLife = model.getLife() / 10;
        int secondDigitLife = model.getLife() % 10;
        graphics.setCharacter(model.getX() + model.getWidth()/2 - 1,model.getY() + 1, TextCharacter.fromCharacter((char) ((char) firstDigitLife + '0'))[0]);
        graphics.setCharacter(model.getX() + model.getWidth()/2, model.getY() + 1, TextCharacter.fromCharacter((char) ((char) secondDigitLife + '0'))[0]);
    }

    @Override
    public ElementModel getModel() {
        return model;
    }
}
