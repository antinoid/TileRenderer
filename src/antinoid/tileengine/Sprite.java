package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Sprite {

    private int size;
    private final int[] pixels;

    public static final Sprite voidSprite = new Sprite(16, 0x0);
    public static final Sprite waterDeep = new Sprite(16, 0, 0, Spritesheet.tiles);
    public static final Sprite waterShallow = new Sprite(16, 1, 0, Spritesheet.tiles);
    public static final Sprite grass = new Sprite(16, 2, 0, Spritesheet.tiles);

    public static final Sprite fishUp = new Sprite(16, 0, 0, Spritesheet.fish);
    public static final Sprite fishDown = new Sprite(16, 1, 0, Spritesheet.fish);
    public static final Sprite fishLeft = new Sprite(16, 2, 0, Spritesheet.fish);
    public static final Sprite fishRight = new Sprite(16, 3, 0, Spritesheet.fish);

    public Sprite(int size, int x, int y, Spritesheet sheet) {
        this.size = size;
        pixels = new int[size * size];
        loadPixels(x * size, y * size, sheet);
    }

    public Sprite(int size, int color) {
        pixels = new int[size * size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = color;
            }
        }
    }

    public int getPixel(int x, int y) {
        return pixels[x + y * size];
    }
    
    public void render(int xPos, int yPos, Screen screen) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                screen.getPixels()[(xPos + x) + (yPos + y) * screen.getWidth()] = pixels[x + y * size];
            }
        }
    }

    private void loadPixels(int sheetX, int sheetY, Spritesheet sheet) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.getPixels()[(x + sheetX) + (y + sheetY) * sheet.getWidth()];
            }
        }
    }
}
