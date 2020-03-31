package cs4q4projectsandbox;

class MappObject {
    private int[] GridPosition = new int[2];
    private int[] PixelPosition = new int[2];
    
    //--- MappObject Constructors
    
    private Mapp ContainingMapp;
    
    int[] GridSpan;
    
    private String Name;
    private String SpecificObjectType;
    private String CollisionType;
    
    //private String ObjectImage;
    
    MappObject() {
        this.GridSpan = new int[]{1, 1};
    }
    
    MappObject(Mapp CM, String SOT) {
        this();
        
        this.ContainingMapp = CM;
        
        this.SpecificObjectType = SOT;
        setCollisionType();
    }
    
    MappObject(Mapp CM, String SOT, String CT) {
        this();
        
        this.ContainingMapp = CM;
        
        this.SpecificObjectType = SOT;
        this.CollisionType = CT;
    }
    
    //--- MappObject Constructor Get/Set
    
    Mapp getContainingMapp() {
        return this.ContainingMapp;
    }
    
    String getSpecificObjectType() {
        return this.SpecificObjectType;
    }
    
    private void setCollisionType() {
        switch(this.SpecificObjectType) {
            case "boundary":
                this.CollisionType = "wall";
                break;
            case "wall":
            case "spike":
                this.CollisionType = "wall";
                break;
            case "block":
                this.CollisionType = "push";
                break;
            case "door":
                this.CollisionType = "warpXY";
                break;
            case "ladderAbove":
            case "stairsAbove":
                this.CollisionType = "warpFloorAbove";
                break;
            case "ladderBelow":
            case "stairsBelow":
                this.CollisionType = "warpFloorBelow";
                break;
            case "nothing":
            default:
                this.CollisionType = "pass";
                break; 
        }
    }
    
    String getCollisionType() {
        return this.CollisionType;
    }
    
    //---
    
    boolean isMovableInDirection(String direction) {
        MappObject nextObject = this.getObjectInDirection(this.getContainingMapp(), direction);
        
        switch (nextObject.getCollisionType()) {
            case "wall":
                return false;
            case "push":
                return nextObject.isMovableInDirection(direction);
            case "warpXY":
            case "warpFloorUp":
            case "warpFloorDown":
                return false;
            case "pass":
            default:
                if (nextObject.getSpecificObjectType().equals("nothing"))
                    return true;
                else
                    return false;
        }
    }
    
    void move(String direction) {
        switch (direction) {
            case "left":
                this.GridPosition[0]--;
                break;
            case "right":
                this.GridPosition[0]++;
                break;
            case "up":
                this.GridPosition[1]--;
                break;
            case "down":
                this.GridPosition[1]++;
                break;
        }
        
        updatePixelPosition();
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
            case "here":
                break;
        }
        
        return ContainingMapp.getMappObject(this.GridPosition[0] + dX, this.GridPosition[1] + dY);
    }
}