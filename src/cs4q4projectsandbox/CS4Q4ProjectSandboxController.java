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
import javafx.stage.Stage;

public class CS4Q4ProjectSandboxController implements Initializable {
    @FXML AnchorPane ap_parentAnchor;
    
    final private int[] GridSpawnpoint = {9, 7};    
    int currentMapFloor;
    int[] currentMapXY;
    Mapp currentMapp;
    
    private Player player;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        currentMapFloor = 1;
        currentMapXY = new int[]{3, 2};
        changeMap(currentMapFloor, currentMapXY);
        
        player = new Player(currentMapp, GridSpawnpoint);
        updatePlayerPixelPosition();
    }
    
    void changeMap(int cMF, int[] cMXY) {
        currentMapp = new Mapp(cMF, cMXY);
        loadMap(currentMapp);
    }
    
    //---
    
    @FXML ImageView imgv_mapImage;
    
    void loadMap(Mapp CM) {
        String MapImagePath = "file:./src/resources/maps/" + CM.getMapID() + ".png";
        imgv_mapImage.setImage(new Image(MapImagePath));
    }
    
    void changeMapToAdjacent(String relativeDirection) {
        int dF = 0, dX = 0, dY = 0;
        
        switch (relativeDirection) {
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
        
        currentMapFloor += dF;
        currentMapXY[0] += dX;
        currentMapXY[1] += dY;
        
        changeMap(currentMapFloor, currentMapXY);
    }
    
    //--- Player/Object Movement
    
    //@FXML StackPane stkp_playerTile;
    @FXML ImageView imgv_playerImage;
    
    void updatePlayerPixelPosition() {
        imgv_playerImage.setLayoutX(player.getPixelPosition()[0]);
        imgv_playerImage.setLayoutY(player.getPixelPosition()[1]);
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
                player.walk();
                updatePlayerPixelPosition();
                break;
            case RIGHT:
                player.setDirectionFacing("right");
                player.walk();
                updatePlayerPixelPosition();
                break;
            case UP:
                player.setDirectionFacing("up");
                player.walk();
                updatePlayerPixelPosition();
                break;
            case DOWN:
                player.setDirectionFacing("down");
                player.walk();
                updatePlayerPixelPosition();
                break;
            
            //to be removed
            case Z:
                changeMapToAdjacent("left");
                break;
            case X:
                //changeScene("inventory");
                changeMapToAdjacent("right");
                break;
            
            case ESCAPE:
                //changeScene("menu");
                break;
                
            default:
                break;
        }
        
        System.out.println("+" + player.getDirectionFacing());
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
        
        System.out.println("-" + player.getDirectionFacing());
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
