package test;

import antinoid.tileengine.Map;
import antinoid.tileengine.RandomMap;
import antinoid.tileengine.Renderer;
import antinoid.tileengine.Screen;
import java.awt.Dimension;

/**
 *
 * @author d
 */
public class Test implements Runnable{

    Renderer renderer;
    
    public Test() {
        renderer = new Renderer(new Dimension(800, 600));
        Thread runner = new Thread(this);
        renderer.start();
        runner.start();
    }
    
    public static void main(String[] args) {
        Test test = new Test();        
    }

    @Override
    public void run() {
        
        while(true) {
            update();
            render();
            
            try {
                //Thread.sleep(1);
            } catch (Exception e) {
            }
        }
        
    }
    
    private void update() {
        
    }
    
    private void render() {
        renderer.render();
    }
}
