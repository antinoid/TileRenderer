package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Screen {

    private final int width, height;
    protected final int[] pixels;
    private int xOffset, yOffset;
    
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[height * width];
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int[] getPixels() {
        return pixels;
    }
    
    /**
     * Set Screen offset in pixels
     * @param xOffset
     * @param yOffset 
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void addOffset(int x, int y) {
        this.xOffset += x;
        this.yOffset += y;
    }
    
    /**
     * Clears Screen
     */
    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                pixels[x + y * width] = 0;
            }
        }
    }
    
    /**
     * Renders a Tile
     * @param xPosition coordinate on the Screen in Pixel
     * @param yPosition coordinate on the Screen in Pixel
     * @param tile the Tile to be rendered
     */
    public void renderTile(int xPosition, int yPosition, Tile tile) {
        for (int y = 0; y < Tile.SIZE; y++) {
            int y2 = y + yPosition;
            if(y2 < 0 || y2 >= height) continue;
            for (int x = 0; x < Tile.SIZE; x++) {
                int x2 = x + xPosition;
                if(x2 < 0 || x2 >= width) continue;
                pixels[x2 + y2 * width] = tile.getPixel(x, y);
            }
        }
    }
}