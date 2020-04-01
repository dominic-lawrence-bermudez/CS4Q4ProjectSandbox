package cs4q4projectsandbox;

class Item extends MappObject {
    private String Name;
    
    Item(Mapp CM, String N, String IM) {
        super(CM, "item", IM);
        
        this.Name = N;
    }
    
    Item(Mapp CM, int GPx, int GPy, String N, String IM) {
        super(CM, GPx, GPy, "item", IM);
        
        this.Name = N;
    }
    
    //--- Item Constructor Get/Set
    
    final String getName() {
        return this.Name;
    }
    
    //---
    
    void bePickedUp() {
        this.getContainingMapp().removeMappObject(this.getGridPosition()[0], this.getGridPosition()[1]);
    }
}