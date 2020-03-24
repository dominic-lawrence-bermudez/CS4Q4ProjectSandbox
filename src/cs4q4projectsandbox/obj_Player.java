package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    //---
    
    int[] GridPosition = new int[2];
    int[] PixelPosition = new int[2];
    private String DirectionFacing;
    
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
        PixelPosition[0] = Tile.Size * GridPosition[0];
        PixelPosition[1] = Tile.Size * GridPosition[1];
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
}