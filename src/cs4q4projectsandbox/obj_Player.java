package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    int[] PlayerPosition_Grid; //int[2]
    int[] PlayerPosition_Pixel;
    private String DirectionFacing;
    
    Player() {
        DirectionFacing = "down";
        PlayerPosition_Grid = new int[]{9, 7};
    }
    
    void walk() {
        switch (DirectionFacing) {
            case "left":
                PlayerPosition_Grid[0] -= 1;
                break;
            case "right":
                PlayerPosition_Grid[0] += 1;
                break;
            case "down":
                PlayerPosition_Grid[1] -= 1;
                break;
            case "up":
                PlayerPosition_Grid[1] += 1;
                break;
        }
    }
    
    String getDirectionFacing() {
        return DirectionFacing;
    }
    
    void setDirectionFacing(String ND) {
        DirectionFacing = ND;
    }
}