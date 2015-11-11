package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Sprite {
    
    private int size;
    private int[] pixels;
    
    public static final Sprite voidSprite = new Sprite(16, 0xFFFFFF);
    public static final Sprite water = new Sprite(16, 0x0000FF);
    public static final Sprite ground = new Sprite(16, 0x8B4513);
    public static final Sprite grass = new Sprite(16, 0x458B00);
    
    public Sprite(int size, int startX, int startY, Spritesheet sheet) {
        pixels = new int[size * size];
        loadPixels(startX, startY, sheet);
    }
    
    public Sprite(int size, int color) {
        pixels = new int[size * size];
        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = color;
            }
        }
    }
    
    public int getPixel(int x, int y) {
        return pixels[x + y * size];
    }
    
    private void loadPixels(int startX, int startY, Spritesheet sheet) {
        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                //pixels[x + y * size] = sheet.getPixels()[x * size]
            }
        }
    }

}
