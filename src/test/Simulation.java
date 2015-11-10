package test;

import antinoid.tileengine.Renderer;
import java.awt.Dimension;

/**
 *
 * @author d
 */
public class Simulation implements Runnable{

    private boolean isRunning = false;
    Thread runner;
    Renderer renderer;
    
    public Simulation() {
        renderer = new Renderer(new Dimension(800, 600));
        runner = new Thread(this);
    }
    
    public void start() {
        isRunning = true;      
        runner.start();
        renderer.start();
    }
    
    private void loop1(final double delta) {
        
        double nextTime = (double)System.nanoTime() / 1000000000.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(isRunning) {
            double now = (double)System.nanoTime() / 1000000000.0;
            
            if(now >= nextTime) {
                nextTime += delta;
                update();
                updates++;
                if( now < nextTime) {
                    render();
                    frames++;
                }
            } else {
                // calculate sleep time
                int sleepTime = (int)(1000.0 * (nextTime - now));
                
                if(sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("Updates: " + updates);
                //System.out.println("Frames: " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }

    @Override
    public void run() {
        loop1(1 / 60.0);
        /*
        while(true) {
            update();
            render();
            
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }*/        
    }
    
    private void update() {
        
    }
    
    private void render() {
        renderer.render();
    }
    
    public static void main(String[] args) {
        Simulation test = new Simulation();
        test.start();
    }
}
