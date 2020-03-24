package cs4q4projectsandbox;

class MapObject {
    private String Name;
    private String ObjectType;
    
    private String ObjectImage;
    
    MapObject(String OT) {
        ObjectType = OT;
        
        setCollisionType();
        
        //--- ObjectImage Generation
        
        
    }
    
    //--- Push Type
    
    private String CollisionType;
    
    private void setCollisionType() {
        switch(ObjectType) {
            case "wall":
            case "spike":
                CollisionType = "wall";
                break;
            case "block":
                CollisionType = "push";
                break;
            case "door":
            case "ladder":
                CollisionType = "warp";
            case "button":
            default:
                CollisionType = "pass";
                break; 
        }
    }
    
    String getCollisionType() {
        return CollisionType;
    }
}