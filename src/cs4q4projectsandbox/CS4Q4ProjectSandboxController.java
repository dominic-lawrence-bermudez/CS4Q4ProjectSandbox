package cs4q4projectsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class CS4Q4ProjectSandboxController implements Initializable {
    @FXML AnchorPane ap_parentAnchor;
    
    final private int[] StartingMapp = {1, 3, 2};
    final private int[] PlayerSpawnpoint = {0, 0};    
    
    private World world;
    private Player player;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        world = new World(StartingMapp[0], StartingMapp[1], StartingMapp[2]);
        loadMapp(world.getCurrentMapp());
        
        player = new Player(world.getCurrentMapp(), PlayerSpawnpoint);
        updatePlayerPixelPosition();
    }
    
    //--- Map Loading
    
    @FXML ImageView imgv_mapImage;
    
    void loadMapp(Mapp map) {
        String MapImagePath = "file:./src/resources/maps/" + map.getMapID() + ".png";
        imgv_mapImage.setImage(new Image(MapImagePath));
    }
    
    void loadCurrentMapp() {
        loadMapp(world.getCurrentMapp());
    }
    
    //--- Player/Object Movement
    
    @FXML TilePane tp_playerTile;
    @FXML ImageView imgv_playerImage;
    
    void updatePlayerPixelPosition() {
        tp_playerTile.setLayoutX(player.getPixelPosition()[0]);
        tp_playerTile.setLayoutY(player.getPixelPosition()[1]);
    }
    
    void updateObjectGridPosition(MappObject mapObject, ImageView mapObjectImage) {
        mapObjectImage.setLayoutX(mapObject.getPixelPosition()[0]);
        mapObjectImage.setLayoutY(mapObject.getPixelPosition()[1]);
    }
    
    //--- Key Listeners
    
    @FXML
    void detectKeyPress(KeyEvent ke) {
        KeyCode KeyEventCode = ke.getCode();
              
        switch (KeyEventCode) {
            case LEFT:
                player.setDirectionFacing("left");
                player.walk("left");
                updatePlayerPixelPosition();
                break;
            case RIGHT:
                player.setDirectionFacing("right");
                player.walk("right");
                updatePlayerPixelPosition();
                break;
            case UP:
                player.setDirectionFacing("up");
                player.walk("up");
                updatePlayerPixelPosition();
                break;
            case DOWN:
                player.setDirectionFacing("down");
                player.walk("down");
                updatePlayerPixelPosition();
                break;
            
            //to be removed
            case Z:
                world.changeCurrentMapToAdjacent("left");
                break;
            case X:
                //changeScene("inventory");
                world.changeCurrentMapToAdjacent("right");
                break;
            
            case SPACE:
                player.interact();
                break;
                
            case ESCAPE:
                //changeScene("menu");
                break;
                
            default:
                break;
        }
        
        loadCurrentMapp();
        
        imgv_playerImage.setImage(new Image("file:./src/resources/sprites/player_" + player.getDirectionFacing() + ".png"));
        //System.out.println("+" + player.getDirectionFacing());
    }
    
    @FXML
    void detectKeyRelease(KeyEvent ke) {
        KeyCode KeyEventCode = ke.getCode();
        
        switch (KeyEventCode) {
            case LEFT:
                break;
            case RIGHT:
                break;
            case UP:
                break;
            case DOWN:
                break;
                
            default:
                break;
        }
        
        //System.out.println("-" + player.getDirectionFacing());
    }
    
    //--- Scene Change
    
    void changeScene(String newScene) throws IOException {
        String newResource = "";
        
        switch (newScene) {
            case "menu":
                //newResource = "landing.fxml";
                break;
            case "load":
                //newResource = "load.fxml";
                break;
            case "inventory":
                //newResource = "inventory.fxml";
                break;
            case "save":
                //newResource = "save.fxml";
                break;
            default:
                newResource = "CS4Q4ProjectSandbox.fxml";
                break;
        }
        
        Parent root = FXMLLoader.load(getClass().getResource(newResource));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
}
