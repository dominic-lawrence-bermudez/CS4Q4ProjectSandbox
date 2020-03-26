package cs4q4projectsandbox;

class MapObject1 {
    final private int TileSize = 64;
    
    int[] GridSpan;
    
    private int[] GridPosition;
    private int[] PixelPosition;
    private String DirectionFacing;
    
    private String ObjectImage;
    
    // There is a chance that MapObject and Player will both be
    // put under the same superclass, due to their similarities.
    
    //---
    
    private void updatePixelPosition() {
        this.PixelPosition[0] = this.TileSize * this.GridPosition[0];
        this.PixelPosition[1] = this.TileSize * this.GridPosition[1];
    }
    
    int[] getPixelPosition() {
        return PixelPosition;
    }
    
    //---
}