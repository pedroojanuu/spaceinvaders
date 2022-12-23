package spaceinvaders.model;

import spaceinvaders.controller.Controller;
import spaceinvaders.view.RunStateViewer;

public interface RunStateModel {
    public void run();
    public RunStateViewer getViewer();
    public Controller getController();
}
