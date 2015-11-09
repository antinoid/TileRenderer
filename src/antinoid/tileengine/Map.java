package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Map {

    protected final int[][] tiles;
    private final int width, height;
    private final Screen screen;
    
    public Map(int width, int height, Screen screen) {
        this.width = width;
        this.height = height;
        this.screen = screen;
        tiles = new int[width][height];
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void render(int xOffset, int yOffset) {
        
        // set Screen offset
        screen.setOffset(xOffset, yOffset);
        
        // end and start point in Tile precision
        int x0 = (xOffset >> Tile.MASK) - 1;
        int y0 = (yOffset >> Tile.MASK) - 1;
        int x1 = ((xOffset + screen.getWidth()) >> Tile.SIZE) + 1;
        int y1 = ((yOffset + screen.getHeight()) >> Tile.SIZE) + 1;
        
        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                // convert to screen coordinates in Pixel precision
                int xPos = (x - xOffset) << Tile.MASK;
                int yPos = (y - yOffset) << Tile.MASK;
                System.out.println("x: " + x);
                screen.renderTile(xPos, yPos, getTile(x, y));
            }
        }
    }
    
    private Tile getTile(int x, int y) {
        if(x < 0 || x > width || y < 0 || y > height) return Tile.voidTile;
        if(tiles[y][x] == 0) return Tile.water;
        if(tiles[y][x] == 1) return Tile.ground;
        return Tile.voidTile;
    }
}
