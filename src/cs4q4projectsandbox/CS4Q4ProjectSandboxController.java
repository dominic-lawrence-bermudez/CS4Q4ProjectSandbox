package cs4q4projectsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
        updatePlayerPosition();
        
        //---
        
        currentMapFloor = 1;
        currentMapXY = new int[]{2, 2};
        
        loadMap(currentMapFloor, currentMapXY);
    }
    
    //---
    
    @FXML ImageView imgv_mapImage;
    
    void loadMap(int cMF, int[] cMXY) {
        Mapp currentMapp = new Mapp(currentMapFloor, currentMapXY);
        
        String MapImagePath = "file:./src/resources/maps/" + currentMapp.getMapImage() + ".png";
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
    
    void updatePlayerPosition() {
        imgv_playerImage.setLayoutX(player.getPixelPosition()[0]);
        imgv_playerImage.setLayoutY(player.getPixelPosition()[1]);
    }
    
    void updateObjectPosition(MappObject mapObject, ImageView mapObjectImage) {
        mapObjectImage.setLayoutX(mapObject.getPixelPosition()[0]);
        mapObjectImage.setLayoutY(mapObject.getPixelPosition()[1]);
    }
    
    //---
    
    int[] PlayerMovement_XY = new int[2];
    
    Map<Integer, Integer> TestTest = new HashMap<>();
    
    @FXML
    void detectKeyPress(KeyEvent ke) throws IOException {
        KeyCode KeyEventCode = ke.getCode();
              
        switch (KeyEventCode) {
            case LEFT:
                PlayerMovement_XY[0]--;
                break;
            case RIGHT:
                PlayerMovement_XY[0]++;
                break;
            case UP:
                PlayerMovement_XY[1]--;
                break;
            case DOWN:
                PlayerMovement_XY[1]++;
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
                PlayerMovement_XY[0]++;
                break;
            case RIGHT:
                PlayerMovement_XY[0]--;
                break;
            case UP:
                PlayerMovement_XY[1]++;
                break;
            case DOWN:
                PlayerMovement_XY[1]--;
                break;
                
            default:
                break;
        }
        
        System.out.println("-" + player.getDirectionFacing());
    }
    
    //---
    
    void moveObjectSmoothly (ImageView imageToMove, KeyCode direction) {
        /*
        
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

        tt.play();
        
        */
        
        new AnimationTimer() {
            @Override
            public void handle(long t_now) {
                Scene scene = imageToMove.getScene();
            }
        }.start();
    } 
}
