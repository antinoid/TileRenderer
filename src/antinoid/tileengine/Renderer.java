package antinoid.tileengine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author d
 */
public class Renderer extends JPanel {

    private final int width;
    private final int height;
    private int[] pixels;
    private int xOffset, yOffset;

    private JFrame frame;
    private BufferedImage image;
    private Screen screen;
    private Map map;
    private InputAdapter input;
    
    private Sprite fish = Sprite.fishLeft;

    public Renderer(Dimension dim) {
        super();
        setPreferredSize(dim);
        setFocusable(true);
        setDoubleBuffered(true);
        this.width = dim.width;
        this.height = dim.height;
        image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        screen = new Screen(dim.width, dim.height);
        map = new Map("res/maps/pond.txt", screen);
        //map = new RandomMap(64, 64, screen);
        input = new InputAdapter();
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
        createFrame();
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
        //frame.setFocusable(true);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.createBufferStrategy(3);
    }

    public void render() {
        processInput();
        draw();
    }

    // TODO public, call in update loop
    private void processInput() {
        if (input.up) {
            yOffset--;
        } else if (input.down) {
            yOffset++;
        }
        if (input.left) {
            xOffset--;
        } else if (input.right) {
            xOffset++;
        }
        if (input.resetOffset) {
            xOffset = 0;
            yOffset = 0;
            input.resetOffset = false;
        }

    }

    private void draw() {

        screen.clear();
        xOffset += input.draggedX;
        yOffset += input.draggedY;
        input.draggedX = 0;
        input.draggedY = 0;
        map.render(xOffset, yOffset);
        fish.render(200, 200, screen);
        System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);
        Graphics g = getGraphics();

        g.drawImage(image, 0, 0, null);

        g.dispose();
    }

    private class InputAdapter implements KeyListener, MouseListener, MouseMotionListener {

        protected boolean resetOffset;
        protected boolean up, down, right, left;
        protected boolean mouseDown;
        protected int draggedX, draggedY;
        private int lastX, lastY;
        private int mouseX, mouseY;

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                // fall through intended
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    right = true;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                // fall through intended
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    right = false;
                    break;
                case KeyEvent.VK_SPACE:
                    resetOffset = true;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseDown = true;
            lastX = e.getX();
            lastY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            draggedX += lastX - mouseX;
            draggedY += lastY - mouseY;
            lastX = mouseX;
            lastY = mouseY;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
