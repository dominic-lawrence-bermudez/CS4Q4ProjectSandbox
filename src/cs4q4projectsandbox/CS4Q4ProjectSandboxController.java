package cs4q4projectsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CS4Q4ProjectSandboxController implements Initializable {
    @FXML AnchorPane ap_parentAnchor;
    
    final private int[] GridSpawnpoint = {9, 7};    
    int currentMapFloor;
    int[] currentMapXY;
    
    private Player player;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap_parentAnchor.requestFocus();
                
        player = new Player(GridSpawnpoint);
        updatePlayerLocation();
        
        //---
        
        currentMapFloor = 1;
        currentMapXY = new int[]{3, 2};
        
        loadMap(currentMapFloor, currentMapXY);
    }
    
    //---
    
    @FXML ImageView imgv_mapImage;
    
    void loadMap(int cMF, int[] cMXY) {
        Map currentMap = new Map(currentMapFloor, currentMapXY);
        
        String MapImagePath = "file:./src/resources/maps/" + currentMap.getMapImage() + ".png";
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
    
    @FXML ImageView imgv_playerImage;
    
    void updatePlayerLocation() {
        imgv_playerImage.setLayoutX(player.getPixelPosition()[0]);
        imgv_playerImage.setLayoutY(player.getPixelPosition()[1]);
    }
    
    void updateObjectLocation(ImageView mapObject) {
        
    }
    
    //---
    
    String[] PlayerMovement_XY = new String[2];
    
    @FXML
    void detectKeyPress(KeyEvent ke) throws IOException {
        KeyCode KeyEventCode = ke.getCode();
              
        switch (KeyEventCode) {
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                moveObjectSmoothly(imgv_playerImage, KeyEventCode);
                break;
                
            case X:
                //openInventory()'
                changeMapToAdjacent("left");
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
            case RIGHT:
                break;
            case DOWN:
            case UP:
                break;
                
            default:
                break;
        }
        
        System.out.println("-" + player.getDirectionFacing());
    }
    
    //---
    
    void moveObjectSmoothly (ImageView imageToMove, KeyCode direction) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), imageToMove);
        
        switch (direction) {
            case LEFT:
                tt.setByX(-Tile.Size);
                break;
            case RIGHT:
                tt.setByX(Tile.Size);
                break;
            case UP:
                tt.setByY(-Tile.Size);
                break;
            case DOWN:
                tt.setByY(Tile.Size);
                break;
        }
        
        //tt.setOnFinished
        
        tt.play();
    }
}
