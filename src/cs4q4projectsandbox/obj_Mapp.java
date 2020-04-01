package cs4q4projectsandbox;

class Mapp {
    final static int[] MAP_SIZE_XY = {16, 12};
    final static int TILE_SIZE = 64;
    
    //--- Mapp Constructor
    
    private final World ContainingWorld;

    private int Floor;
    private int[] PositionXY;
    
    private String MapID;
    
    private MappObject[][] MappObjects = new MappObject[MAP_SIZE_XY[0]][MAP_SIZE_XY[1]];
    
    Mapp(World CW, int F, int[] P_XY) {
        this.ContainingWorld = CW;
        
        //---
        
        this.Floor = F;
        this.PositionXY = new int[]{P_XY[0], P_XY[1]};
        
        this.emptyMappObjects();
        
        this.addMappObject(5, 5, new MappObject(this, "wall"));
        this.addMappObject(1, 11, new MappObject(this, "door"));
        this.addMappObject(7, 7, new Item(this, "secret_key", "i hate minorities"));
        //--- MapID Generation
        
        this.MapID = "Map_F" + this.Floor + "_";
        
        for (int i = 0; i <= 1; i++) {
            if (P_XY[i] < 10)
                this.MapID += "0";
            this.MapID += this.PositionXY[i];
        }
    }
    
    //--- Mapp Constructor Get/Set
    
    final World getContainingWorld() {
        return this.ContainingWorld;
    }
    
    final void setFloor(int F) {
        this.Floor = F;
    }
    
    final int getFloor() {
        return this.Floor;
    }
    
    final void setPositionXY(int Px, int Py) {
        this.PositionXY[0] = Px;
        this.PositionXY[1] = Py;
    }
    
    final int[] getPositionXY() {
        return this.PositionXY;
    }
    
    final String getMapID() {
        return this.MapID;
    }
        
    //---
    
    final void emptyMappObjects() {
        for (int i = 0; i < MAP_SIZE_XY[0]; i++) {
            for (int j = 0; j < MAP_SIZE_XY[1]; j++) {
                this.MappObjects[i][j] = new MappObject(this, "nothing");
            }
        }
    }
    
    final void addMappObject(int GPx, int GPy, MappObject mappObject) {
        this.MappObjects[GPx][GPy] = mappObject;
        
        mappObject.setGridPosition(GPx, GPy);
    }
    
    final void removeMappObject(int GPx, int GPy) {
        this.MappObjects[GPx][GPy] = new MappObject(this, "nothing");
    }

    final MappObject getMappObject(int GPx, int GPy) {
        return this.MappObjects[GPx][GPy];
    }
}