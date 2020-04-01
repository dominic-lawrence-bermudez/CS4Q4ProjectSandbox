package cs4q4projectsandbox;

class MappObject {
    private int[] GridPosition = new int[2];
    private int[] PixelPosition = new int[2];
    
    //--- MappObject Constructors
    
    private Mapp ContainingMapp;
    
    private int[] GridSpan;
    
    private String SpecificObjectType;
    private String CollisionType;
    private String InteractionMessage;
    //private String ObjectImage;
    
    private MappObject() {
        this.GridSpan = new int[]{1, 1};
    }
    
    MappObject(Mapp CM, String SOT) {
        this();
        
        this.ContainingMapp = CM;
        
        this.SpecificObjectType = SOT;
        this.setCollisionType();
        
        this.InteractionMessage = "";
    }
    
    MappObject(Mapp CM, String SOT, String IM) {
        this(CM, SOT);
        
        this.InteractionMessage = IM;
    }
    
    MappObject(Mapp CM, int GPx, int GPy, String SOT) {
        this(CM, SOT);
        
        this.setGridPosition(GPx, GPy);
    }
    
    MappObject(Mapp CM, int GPx, int GPy, String SOT, String IM) {
        this(CM, GPx, GPy, SOT);
        
        this.InteractionMessage = IM;
    }
    
    //--- MappObject Constructor Get/Set
    
    final Mapp getContainingMapp() {
        return this.ContainingMapp;
    }
    
    final void setGridPosition(int GPx, int GPy) {
        this.GridPosition[0] = GPx;
        this.GridPosition[1] = GPy;
        
        updatePixelPosition();
    }
    
    private void updatePixelPosition() {
        this.PixelPosition[0] = Mapp.TILE_SIZE * this.GridPosition[0];
        this.PixelPosition[1] = Mapp.TILE_SIZE * this.GridPosition[1];
    }
    
    final int[] getGridPosition() {
        return this.GridPosition;
    }
    
    final int[] getPixelPosition() {
        return this.PixelPosition;
    }
    
    final String getSpecificObjectType() {
        return this.SpecificObjectType;
    }
    
    private void setCollisionType() {
        switch(this.SpecificObjectType) {
            case "boundary":
            case "wall":
                this.CollisionType = "wall";
                break;
            case "object":
            case "checkpoint":
            case "npc":
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
            case "stairs":
            case "item":
            case "nothing":
            default:
                this.CollisionType = "pass";
                break; 
        }
    }
    
    final String getCollisionType() {
        return this.CollisionType;
    }
    
    //--- Get Object In Direction
    
    final MappObject getMappObjectInDirection(Mapp ContainingMapp, String Direction) {
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
    
    //--- MappObject Movement
    
    final boolean isMovableInDirection(String direction) {
        MappObject nextObject = this.getMappObjectInDirection(this.getContainingMapp(), direction);
        
        switch (nextObject.getCollisionType()) {
            case "wall":
                return false;
            case "push":
                //return nextObject.isMovableInDirection(direction);
                return false;
            case "warpXY":
            case "warpFloorUp":
            case "warpFloorDown":
                return false;
            case "pass":
            default:
                return nextObject.getSpecificObjectType().equals("nothing");
        }
    }
    
    final void move(String direction) {
        int dX = 0, dY = 0;
        
        switch (direction) {
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
        
        this.setGridPosition(this.GridPosition[0] + dX, this.GridPosition[1] + dY);
    }
    
    //---
    
    void interactWithPlayer() {
        switch (this.SpecificObjectType) {
            case "item":
                // pick up item
                this.displayMessage();
                return;
            case "checkpoint":
                // switch scene
            default:
                break;
        }
    }
    
    void displayMessage() {
        if (this.InteractionMessage.equals(""))
            return;
        
        System.out.println(this.InteractionMessage);
    }
}