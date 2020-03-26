package cs4q4projectsandbox;

class Player {
    final private int TileSize = 64;
    
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    //---
    
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
    
    private void updatePixelPosition() {
        PixelPosition[0] = TileSize * GridPosition[0];
        PixelPosition[1] = TileSize * GridPosition[1];
    }
    
    int[] getPixelPosition() {
        return PixelPosition;
    }
    
    //---
    
    void setDirectionFacing(String ND) {
        DirectionFacing = ND;
    }
    
    String getDirectionFacing() {
        return DirectionFacing;
    }  
    
    //---
    
    
}