package cs4q4projectsandbox;

abstract class MapObject1 {
    int[] GridSpan;
    
    private int[] GridPosition;
    private int[] PixelPosition;
    private String DirectionFacing;
    
    private String ObjectImage;
    
    // There is a chance that MapObject and Player will both be
    // put under the same superclass, due to their similarities.
    
    //---
    
    void setGridPosition(int GX, int GY) {
        this.GridPosition[0] = GX;
        this.GridPosition[1] = GY;
    }
    
    private void updatePixelPosition() {
        this.PixelPosition[0] = Mapp.TILE_SIZE * this.GridPosition[0];
        this.PixelPosition[1] = Mapp.TILE_SIZE * this.GridPosition[1];
    }
    
    int[] getGridPosition() {
        return this.GridPosition;
    }
    
    int[] getPixelPosition() {
        return this.PixelPosition;
    }
    
    //---
    
    MappObject getObjectInDirection(Mapp ContainingMapp, String Direction) {
        int dX = 0, dY = 0;
        
        switch (Direction) {
            case "left":
                dX = -1;
                break;
            case "right":
                dX = +1;
                break;
            case "up":
                dY = -1;
                break;
            case "down":
                dY = +1;
                break;
        }
        
        return ContainingMapp.getMappObject(this.GridPosition[0] + dX, this.GridPosition[1] + dY);
    }
    
    abstract boolean isMovableInDirection(String direction);
}