package cs4q4projectsandbox;

class MappObject {
    private int[] GridPosition = new int[2];
    private int[] PixelPosition = new int[2];
    
    private Mapp ContainingMapp;
    
    private String Name;
    private String SpecificObjectType;
    private String CollisionType;
    
    int[] GridSpan;
    
    private String ObjectImage;
    
    MappObject() {
        this.GridSpan = new int[]{1, 1};
        
        //--- ObjectImage Generation
        
        this.ObjectImage = this.Name;
    }
    
    MappObject(Mapp M, String SOT) {
        this();
        
        this.ContainingMapp = M;
        
        this.SpecificObjectType = SOT;
        setCollisionType();
    }
    
    MappObject(Mapp M, String SOT, String CT) {
        this();
        
        this.ContainingMapp = M;
        
        this.SpecificObjectType = SOT;
        this.CollisionType = CT;
    }
    
    Mapp getContainingMapp() {
        return this.ContainingMapp;
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
}