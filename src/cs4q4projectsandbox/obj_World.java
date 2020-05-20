package cs4q4projectsandbox;

class World {
    final static int MAX_MAP_FLOORS = 4;
    final static int[] MAX_MAPS_XY = {16, 16};
    
    //--- World Constructors
    
    private String WorldName;
    private Mapp[][][] Mapps = new Mapp[MAX_MAP_FLOORS][MAX_MAPS_XY[0]][MAX_MAPS_XY[1]];
    
    private World() {
        for (int f = 0; f < MAX_MAP_FLOORS; f++) {
            for (int x = 0; x < MAX_MAPS_XY[0]; x++) {
                for (int y = 0; y < MAX_MAPS_XY[1]; y++) {
                    this.Mapps[f][x][y] = new Mapp(this, f, new int[]{x, y});
                }
            }
        }
    }
    
    World(int F, int Mx, int My) {
        this();
        
        this.CurrentMapp = Mapps[F][Mx][My];
    }
    
    World(String WN, int F, int Mx, int My) {
        this(F, Mx, My);
        
        this.WorldName = WN;
    }
    
    //--- World Constructors Get/Set
      
    final Mapp getMapp(int F, int Mx, int My) {
        return this.Mapps[F][Mx][My];
    }
    
    private Mapp CurrentMapp;
    
    final void setCurrentMapp(int F, int Mx, int My) {
        this.CurrentMapp = Mapps[F][Mx][My];
    }
    
    final void changeCurrentMapToAdjacent(String direction) {
        int dF = 0, dX = 0, dY = 0;
        
        switch (direction) {
            case "below":
                dF = -1;
                break;
            case "above":
                dF = +1;
                break;
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
            default:
                break;
        }
        
        System.out.println("Change Map: " + dF + " " + dX + " " + dY);
        
        CurrentMapp = this.getMapp(
            this.CurrentMapp.getFloor() + dF,
            this.CurrentMapp.getPositionXY()[0] + dX,
            this.CurrentMapp.getPositionXY()[1] + dY
        );
    }
    
    final Mapp getCurrentMapp() {
        return this.CurrentMapp;
    }
}