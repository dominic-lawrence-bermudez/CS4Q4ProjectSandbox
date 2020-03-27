package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    //---
    
    Mapp ContainingMapp;
    
    int[] GridPosition = new int[2];
    int[] PixelPosition = new int[2];
    private String DirectionFacing;
    
    String PlayerImage;
    
    Player(int[] GP) {
        DirectionFacing = "down";
        
        GridPosition[0] = GP[0];
        GridPosition[1] = GP[1];
        updatePixelPosition();
    }
    
    void walk() {
        switch (DirectionFacing) {
            case "left":
                GridPosition[0]--;
                break;
            case "right":
                GridPosition[0]++;
                break;
            case "up":
                GridPosition[1]--;
                break;
            case "down":
                GridPosition[1]++;
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
    
    //---
    
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
}