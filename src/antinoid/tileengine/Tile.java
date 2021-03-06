package antinoid.tileengine;

/**
 *
 * @author d
 */
public class Tile {

    public static final int SIZE = 16;
    public static final int MASK = (int)(Math.log(SIZE) / Math.log(2));
    
    private final Sprite sprite;
    
    public static final Tile voidTile = new Tile(Sprite.voidSprite);
    public static final Tile waterDeep = new Tile(Sprite.waterDeep);
    public static final Tile waterShallow = new Tile(Sprite.waterShallow);
    public static final Tile grass = new Tile(Sprite.grass);
    
    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public int getPixel(int x, int y) {
        return sprite.getPixel(x, y);
    }
}
