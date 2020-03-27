package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    //---
    
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
    
    void walk() {
        switch (this.DirectionFacing) {
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
    
    //--- Direction Facing
    
    void setDirectionFacing(String DF) {
        this.DirectionFacing = DF;
    }
    
    String getDirectionFacing() {
        return this.DirectionFacing;
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
    
    boolean isMovableInDirection(String direction) {
        MappObject nextObject = this.getObjectInDirection(ContainingMapp, direction);
        
        return nextObject.isMovableInDirection(direction);
    }
}