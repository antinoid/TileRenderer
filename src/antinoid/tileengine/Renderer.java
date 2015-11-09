package antinoid.tileengine;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author d
 */
public class Renderer extends JPanel {

    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private Screen screen;
    
    public Renderer(Dimension dim) {
        super();
        setPreferredSize(dim);
        createFrame();
        image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        screen = new Screen(dim.width, dim.height);        
    }
    
    public Screen getScreen() {
        return screen;
    }
    
    public void start() {
        frame.setVisible(true);
        requestFocus();
    }
    
    private void createFrame() {
        frame = new JFrame("Antinoid Simulation");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void render() {
        
        screen.clear();        
        
        for (int i = 0; i < pixels.length; i++) {
           // pixels
        }
    }
}
