package spaceinvaders.controller;


import com.googlecode.lanterna.input.KeyStroke;

public class RunStateController {
    private Controller controller;

    public RunStateController(Controller controller){
        this.controller = controller;
    }

    public void processKey(KeyStroke key){
        controller.processKey(key);
    }
    public Controller getController(){
        return controller;
    }
}
