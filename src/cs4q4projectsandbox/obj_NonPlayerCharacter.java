package cs4q4projectsandbox;

class NonPlayerCharacter extends MappObject {

    
    //--- NonPlayerCharacter Constructors
    
    private String FullName;
    private String Nickname;
    
    NonPlayerCharacter(Mapp CM, int GPx, int GPy, String N) {
        super(CM, GPx, GPy, "npc");
        
        this.Nickname = N;
    }
    
    NonPlayerCharacter(Mapp CM, int GPx, int GPy, String FN, String N) {
        this(CM, GPx, GPy, N);
        
        this.FullName = FN;
    }
    
    //--- NonPlayerCharacter Constructor Get/Set
    
    String getFullName() {
        return this.FullName;
    }
    
    String getNickname() {
        return this.Nickname;
    }
}