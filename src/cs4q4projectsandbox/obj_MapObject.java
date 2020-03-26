package cs4q4projectsandbox;

class MapObject {
    final int TileSize = 64;
    
    private int[] GridPosition = new int[2];
    private int[] PixelPosition = new int[2];
    private String DirectionFacing;
    
    private String Name;
    private String SpecificObjectType;
    private String CollisionType;
    
    int[] GridSpan;
    
    private String ObjectImage;
    
    MapObject() {
        this.GridSpan = new int[]{1, 1};
        
        //--- ObjectImage Generation
        
        this.ObjectImage = this.Name;
    }
    
    MapObject(String SOT) {
        this();
        
        this.SpecificObjectType = SOT;
        setCollisionType();
    }
    
    MapObject(String SOT, String CT) {
        this();
        
        this.SpecificObjectType = SOT;
        this.CollisionType = CT;
    }
    
    //--- Collision Type
    
    private void setCollisionType() {
        switch(this.SpecificObjectType) {
            case "wall":
            case "spike":
                this.CollisionType = "wall";
                break;
            case "block":
                this.CollisionType = "push";
                break;
            case "door":
                this.CollisionType = "warpXY";
            case "ladderUp":
            case "stairsUp":
                this.CollisionType = "warpFloorUp";
                break;
            case "ladderDown":
            case "stairsDown":
                this.CollisionType = "warpFloorDown";
                break;
            case "button":
            default:
                this.CollisionType = "pass";
                break; 
        }
    }
    
    String getCollisionType() {
        return this.CollisionType;
    }
    
    //---
    
    private void updatePixelPosition() {
        this.PixelPosition[0] = this.TileSize * this.GridPosition[0];
        this.PixelPosition[1] = this.TileSize * this.GridPosition[1];
    }
    
    int[] getPixelPosition() {
        return this.PixelPosition;
    }
}