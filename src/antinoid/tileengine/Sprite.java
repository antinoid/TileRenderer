package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Sprite {
    
    private int size;
    private int[][] pixels;
    
    public static final Sprite voidSprite = new Sprite(16, 0x0);
    public static final Sprite water = new Sprite(16, 0x0000FF);
    public static final Sprite ground = new Sprite(16, 0x8B4513);
    
    public Sprite(int size, int color) {
        pixels = new int[size][size];
        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[y][x] = color;
            }
        }
    }
    
    public int getPixel(int x, int y) {
        return pixels[y][x];
    }

}
