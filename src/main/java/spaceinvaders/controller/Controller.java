package spaceinvaders.controller;

import com.googlecode.lanterna.input.KeyStroke;

public interface Controller {
    void processKey(KeyStroke key);
}
