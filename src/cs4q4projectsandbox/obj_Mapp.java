package cs4q4projectsandbox;

class Mapp {
    final static int[] MAP_SIZE_XY = {16, 12};
    final static int TILE_SIZE = 64;
    
    //--- Mapp Constructor
    
    private World ContainingWorld;

    private int Floor;
    private int[] PositionXY;
    
    private String MapID;
    
    private MappObject[][] MappObjects = new MappObject[MAP_SIZE_XY[0]][MAP_SIZE_XY[1]];
    
    Mapp(World CW, int F, int[] P_XY) {
        this.ContainingWorld = CW;
        
        //---
        
        this.Floor = F;
        this.PositionXY = new int[]{P_XY[0], P_XY[1]};
        
        for (int i = 0; i < MAP_SIZE_XY[0]; i++) {
            for (int j = 0; j < MAP_SIZE_XY[1]; j++) {
                this.MappObjects[i][j] = new MappObject(this, "nothing");
            }
        }
        
        this.MappObjects[5][5] = new MappObject(this, "wall");
        this.MappObjects[1][11] = new MappObject(this, "door");
        
        //--- MapID Generation
        
        this.MapID = "Map_F" + this.Floor + "_";
        
        for (int i = 0; i <= 1; i++) {
            if (P_XY[i] < 10)
                this.MapID += "0";
            this.MapID += this.PositionXY[i];
        }
    }
    
    //--- Mapp Constructor Get/Set
    
    World getContainingWorld() {
        return this.ContainingWorld;
    }
    
    void setFloor(int F) {
        this.Floor = F;
    }
    
    int getFloor() {
        return this.Floor;
    }
    
    void setPositionXY(int Px, int Py) {
        this.PositionXY[0] = Px;
        this.PositionXY[1] = Py;
    }
    
    int[] getPositionXY() {
        return this.PositionXY;
    }
    
    String getMapID() {
        return this.MapID;
    }
        
    //---

    MappObject getMappObject(int GPx, int GPy) {
        return this.MappObjects[GPx][GPy];
    }
}