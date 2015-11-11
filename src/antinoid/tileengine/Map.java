package antinoid.tileengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author d
 */
public class Map {

    int[] tiles;
    private int width, height;
    private final Screen screen;
    
    public Map(int width, int height, Screen screen) {
        this.width = width;
        this.height = height;
        this.screen = screen;
        tiles = new int[width * height];
    }
    
    public Map(String filePath, Screen screen) {
        
        this.screen = screen;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            System.out.println(width);
            tiles = new int[width * height];
            
            String delimiter = " ";
            for (int y = 0; y < height; y++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiter);
                for (int x = 0; x < width; x++) {
                    tiles[x + y * width] = Integer.parseInt(tokens[x]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
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
        int x1 = ((xOffset + screen.getWidth()) >> Tile.MASK) + 1;
        int y1 = ((yOffset + screen.getHeight()) >> Tile.MASK) + 1;
        
        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                // convert to screen coordinates in Pixel precision
                int xPos = (x << Tile.MASK) - xOffset;
                int yPos = (y << Tile.MASK) - yOffset;
                screen.renderTile(xPos, yPos, getTile(x, y));
            }
        }
    }
    
    private Tile getTile(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
        if(tiles[x + y * width] == 0) return Tile.voidTile;
        if(tiles[x + y * width] == 1) return Tile.waterDeep;
        if(tiles[x + y * width] == 2) return Tile.waterShallow;
        if(tiles[x + y * width] == 3) return Tile.grass;
        return Tile.voidTile;
    }
}
