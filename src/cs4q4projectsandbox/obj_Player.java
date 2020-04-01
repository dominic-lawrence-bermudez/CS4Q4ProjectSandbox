package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    //--- Player Constructors
    
    private Mapp ContainingMapp;
    
    int[] GridPosition = new int[2];
    int[] PixelPosition = new int[2];
    private String DirectionFacing;
    
    Player(Mapp CM, int[] GP) {
        this.ContainingMapp = CM;
        this.DirectionFacing = "down";
        
        this.setGridPosition(GP[0], GP[1]);
    }

    //--- Player Constructor Get/Set
    
    Mapp getContainingMapp() {
        return this.ContainingMapp;
    }
    
    void setGridPosition(int GPx, int GPy) {
        this.GridPosition[0] = GPx;
        this.GridPosition[1] = GPy;
        
        updatePixelPosition();
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
    
    void setDirectionFacing(String DF) {
        this.DirectionFacing = DF;
    }
    
    String getDirectionFacing() {
        return this.DirectionFacing;
    }
    
    //--- Get Object In Direction
    
    MappObject getMappObjectInDirection(Mapp ContainingMapp, String Direction) {
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
        
        int newX = this.GridPosition[0] + dX;
        int newY = this.GridPosition[1] + dY;
        
        if (newX < 0 || newX >= Mapp.MAP_SIZE_XY[0])
            return new MappObject(ContainingMapp, "boundary");
        if (newY < 0 || newY >= Mapp.MAP_SIZE_XY[1])
            return new MappObject(ContainingMapp, "boundary");
        
        return ContainingMapp.getMappObject(newX, newY);
    }
    
    //--- Player Movement
    
    boolean canWalkInDirection(String direction) {
        MappObject nextObject = this.getMappObjectInDirection(this.getContainingMapp(), direction);
        
        switch (nextObject.getCollisionType()) {
            case "wall":
                return false;
            case "push":
                return nextObject.isMovableInDirection(direction);
            case "warpXY":
            case "warpFloorUp":
            case "warpFloorDown":
                return true;
            case "pass":
            default:
                return true;
        }
    }
    
    void walk(String direction) {
        if (!canWalkInDirection(direction))
            return;
          
        //---
            
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
        
        //---
        
        MappObject currentMappObject = this.getMappObjectInDirection(this.getContainingMapp(), "here");
        
        switch (currentMappObject.getCollisionType()) {
            case "push":
                //nextObject.move(direction);
                break;
            case "warpXY":
                this.getContainingMapp().getContainingWorld().changeCurrentMapToAdjacent(direction);
                break;
            case "warpFloorUp":
                this.getContainingMapp().getContainingWorld().changeCurrentMapToAdjacent("above");
                break;
            case "warpFloorDown":
                this.getContainingMapp().getContainingWorld().changeCurrentMapToAdjacent("below");
                break;
            default:
                break;                
        }
    }
    
    //---
    
    void interact() {
        MappObject objectFacing = this.getMappObjectInDirection(this.ContainingMapp, this.DirectionFacing);
    
        objectFacing.interactWithPlayer();
    }
}