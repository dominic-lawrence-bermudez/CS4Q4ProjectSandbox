package cs4q4projectsandbox;

class Mapp {
    private int[] Dimensions_XY = {16, 12};
    
    //---
    
    int Floor;
    int[] Position_XY;

    String[][] CollisionValues = new String[Dimensions_XY[0]][Dimensions_XY[1]];
    
    private String MapImage;
    
    Mapp(int F, int[] P_XY) {
        Floor = F;
        Position_XY = new int[]{P_XY[0], P_XY[1]};

        //--- MapImage Generation
        
        MapImage = "Map_F" + Floor + "_";
        
        for (int i = 0; i <= 1; i++) {
            if (P_XY[i] < 10)
                MapImage += "0";
            MapImage += Position_XY[i];
        }
    }
    
    String getMapImage() {
        return MapImage;
    }
}