package cs4q4projectsandbox;

class Player {
    final String FullName = "Jerry Barry Cruz";
    final String Nickname = "Jeby";
    
    int[] PlayerPosition; //int[2]
    String DirectionFacing;
    
    Player() {
        DirectionFacing = "down";
        PlayerPosition = new int[]{64*9, 64*7};
    }
    
    final int walkValue = 2;
    
    void walk() {
        switch (DirectionFacing) {
            case "left":
                PlayerPosition[0] -= walkValue;
                break;
            case "right":
                PlayerPosition[0] += walkValue;
                break;
            case "down":
                PlayerPosition[1] -= walkValue;
                break;
            case "up":
                PlayerPosition[1] += walkValue;
                break;
        }
    }
    
    void setDirectionFacing(String ND) {
        DirectionFacing = ND;
    }
}