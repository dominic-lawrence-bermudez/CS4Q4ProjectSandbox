package cs4q4projectsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class CS4Q4ProjectSandboxController implements Initializable {
    @FXML AnchorPane ap_parentAnchor;
    
    final private int[] GridSpawnpoint = {9, 7};    
    int currentMapFloor;
    int[] currentMapXY;
    Mapp currentMapp;
    
    private Player player;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap_parentAnchor.requestFocus();
                
        player = new Player(GridSpawnpoint);
        updatePlayerPixelPosition();
        
        //---
        
        currentMapFloor = 1;
        currentMapXY = new int[]{2, 2};
        
        changeMap(currentMapFloor, currentMapXY);
        loadMap(currentMapFloor, currentMapXY);
    }
    
    void changeMap(int cMF, int[] cMXY) {
        currentMapp = new Mapp(cMF, cMXY);
    }
    
    //---
    
    @FXML ImageView imgv_mapImage;
    
    void loadMap(int cMF, int[] cMXY) {
        String MapImagePath = "file:./src/resources/maps/" + currentMapp.getMapID() + ".png";
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
        
        loadMap(currentMapFloor, currentMapXY);
    }
    
    //---
    
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
    
    boolean isPlayerMovableInDirection(String direction) {
        MappObject overlap = player.getObjectInDirection(currentMapp, direction);
        
        return isObjectMovableInDirection(overlap, direction);
    }
    
    boolean isObjectMovableInDirection(MappObject objectToMove, String direction) {
        MappObject overlap = objectToMove.getObjectInDirection(objectToMove.getContainingMapp(), direction);
        
        switch (overlap.getCollisionType()) {
            case "wall":
                return false;
            case "push":
                return isObjectMovableInDirection(overlap, direction);
            default:
                return true;
        }
    }
    
    //--- Key Listeners
    
    @FXML
    void detectKeyPress(KeyEvent ke) throws IOException {
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
                
            case X:
                //openInventory()'
                
                break;
            
            case ESCAPE:
                //backToMenu();
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
    
    //---
    
}
