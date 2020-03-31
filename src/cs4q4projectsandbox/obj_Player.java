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
        
        this.GridPosition[0] = GP[0];
        this.GridPosition[1] = GP[1];
        updatePixelPosition();
    }

    //--- Player Constructor Get/Set
    
    Mapp getContainingMapp() {
        return this.ContainingMapp;
    }
    
    void setGridPosition(int GPx, int GPy) {
        this.GridPosition[0] = GPx;
        this.GridPosition[1] = GPy;
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
        
        if (this.GridPosition[0] + dX < 0 || this.GridPosition[0] + dX >= Mapp.MAP_SIZE_XY[0])
            return new MappObject(ContainingMapp, "boundary");
        if (this.GridPosition[1] + dY < 0 || this.GridPosition[1] + dY >= Mapp.MAP_SIZE_XY[1])
            return new MappObject(ContainingMapp, "boundary");
        
        return ContainingMapp.getMappObject(this.GridPosition[0] + dX, this.GridPosition[1] + dY);
    }
    
    //--- Player Movement
    
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
                return true;
            case "pass":
            default:
                return true;
        }
    }
    
    void walk(String direction) {
        if (!isMovableInDirection(this.DirectionFacing))
            return;
        
        MappObject nextObject = this.getObjectInDirection(this.getContainingMapp(), direction);
        
        //if (nextObject.getCollisionType().equals("push"))
            //nextObject.move(direction);
        
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
        
        switch (nextObject.getCollisionType()) {
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
        
        updatePixelPosition();
    }
}