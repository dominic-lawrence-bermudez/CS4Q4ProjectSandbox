package cs4q4projectsandbox;

class MapObject {
    private String Name;
    private String SpecificObjectType;
    private String GeneralObjectType;
    
    private String ObjectImage;
    
    MapObject(String SOT) {
        this.SpecificObjectType = SOT;
        
        setGeneralObjectType();
        
        //--- ObjectImage Generation
        
        
    }
    
    MapObject(String SOT, String GOT) {
        this.SpecificObjectType = SOT;
        this.GeneralObjectType = GOT;
    }
    
    //--- General Object Type (for Collision, Pickup, Warp, etc.)
    
    private void setGeneralObjectType() {
        switch(this.SpecificObjectType) {
            case "wall":
            case "spike":
            case "chair":
            case "table":
                GeneralObjectType = "wall";
                break;
            case "block":
                GeneralObjectType = "push";
                break;
            case "door":
                GeneralObjectType = "warpXY";
            case "ladderUp":
            case "stairsUp":
                GeneralObjectType = "warpFloorUp";
                break;
            case "ladderDown":
            case "stairsDown":
                GeneralObjectType = "warpFloorDown";
                break;
            case "button":
            default:
                GeneralObjectType = "pass";
                break; 
        }
    }
    
    String getGeneralObjectType() {
        return GeneralObjectType;
    }
}