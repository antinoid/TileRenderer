package antinoid.tileengine;

import java.util.Random;

/**
 *
 * @author d
 */
public class RandomMap extends Map {

    Random random = new Random();
    
    public RandomMap(int width, int height, Screen screen) {
        super(width, height, screen);
        
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(3);
            }
        }
    }

}
