package antinoid.tileengine;

import java.awt.Dimension;
import java.awt.Graphics;
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
    private final int width;
    private final int height;
    private BufferedImage image;
    private int[] pixels;
    private Screen screen;
    private Map map;
    
    public Renderer(Dimension dim) {
        super();
        setPreferredSize(dim);
        this.width = dim.width;
        this.height = dim.height;
        createFrame();
        image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        screen = new Screen(dim.width, dim.height);
        map = new RandomMap(64, 64, screen);
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
        map.render(0, 0);
        System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);
        System.out.println("pixels[1235]: " + pixels[1235]);
        Graphics g = getGraphics();
        
        g.drawImage(image, 0, 0, null);
        
        g.dispose();
    }
}
