package spaceinvaders;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import spaceinvaders.controller.GameController;
import spaceinvaders.model.GameModel;
import spaceinvaders.view.GameViewer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Game {
    static private GameModel model;
    static private GameController controller;
    static private GameViewer viewer;
    static private int width = 50;
    static private int height = 26;

    public static void main(String[] args) throws IOException, FontFormatException {
        new Game().run();
    }

    public AWTTerminalFontConfiguration loadFont() throws FontFormatException, IOException {
        File fontFile = new File("resources/fonts/SpaceInv_MONO.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.PLAIN, 30);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    public void run() throws IOException, FontFormatException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        AWTTerminalFontConfiguration fontConfig = loadFont();
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(width, height));
        Terminal terminal = factory.createTerminal();
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });

        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        if(model == null)
            model = new GameModel();
        if(controller == null)
            controller = new GameController(model, screen);
        if(viewer == null)
            viewer = new GameViewer(model, screen);

        int FPS = 10;
        int frameTime = 1000 / FPS;
        while (true) {
            long startTime = System.currentTimeMillis();

            model.run();
            viewer.draw();
            controller.processKey();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}
