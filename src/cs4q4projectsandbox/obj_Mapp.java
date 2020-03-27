package cs4q4projectsandbox;

class Mapp {
    private static int[] MapSizeXY = {16, 12};
    final static int TILE_SIZE = 64;
    
    //---
    
    int Floor;
    int[] Position_XY;

    private MappObject[][] MappObjects = new MappObject[MapSizeXY[0]][MapSizeXY[1]];
    
    MappObject getMappObject(int GPx, int GPy) {
        return MappObjects[GPx][GPy];
    }
    
    //---
    
    private String MapID;
    
    Mapp(int F, int[] P_XY) {
        Floor = F;
        Position_XY = new int[]{P_XY[0], P_XY[1]};

        //--- MapImage Generation
        
        MapID = "Map_F" + Floor + "_";
        
        for (int i = 0; i <= 1; i++) {
            if (P_XY[i] < 10)
                MapID += "0";
            MapID += Position_XY[i];
        }
    }
    
    String getMapID() {
        return MapID;
    }
}