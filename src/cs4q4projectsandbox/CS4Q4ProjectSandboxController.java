package cs4q4projectsandbox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CS4Q4ProjectSandboxController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentMapFloor = 1;
        currentMapXY = new int[]{3, 2};
        
        loadMap(currentMapFloor, currentMapXY);
        
        //---
        
        player = new Player();
        
        ap_parentAnchor.requestFocus();
    }
    
    @FXML AnchorPane ap_parentAnchor;
    
    int currentMapFloor;
    int[] currentMapXY;
    
    Player player;
    
    //---
    
    @FXML ImageView imgv_mapImage;
    
    void changeMapToAdjacent(int dF, int dX, int dY) {
        currentMapFloor += dF;
        currentMapXY[0] += dX;
        currentMapXY[1] += dY;
        
        loadMap(currentMapFloor, currentMapXY);
    }
    
    void loadMap(int cMF, int[] cMXY) {
        Map currentMap = new Map(currentMapFloor, currentMapXY);
        
        String MapImagePath = "file:./src/resources/maps/" + currentMap.MapImage + ".png";
        imgv_mapImage.setImage(new Image(MapImagePath));
    }
    
    @FXML ImageView imgv_playerImage;
    
    @FXML
    void clicky() {
        System.out.println("test");
    }
    
    @FXML
    void detectKeyPress(KeyEvent ke) throws IOException {
        KeyCode KeyEventCode = ke.getCode();
        
        switch (KeyEventCode) {
            case LEFT:
                player.setDirectionFacing("left");
                player.walk();
                //imgv_playerImage.setLayoutX(player.PlayerPosition[0]);
                break;
            case RIGHT:
                player.setDirectionFacing("right");
                player.walk();
                //imgv_playerImage.setLayoutX(player.PlayerPosition[0]);
                break;
            case DOWN:
                player.setDirectionFacing("down");
                player.walk();
                //imgv_playerImage.setLayoutY(player.PlayerPosition[1]);
                break;
            case UP:
                player.setDirectionFacing("up");
                player.walk();
                //imgv_playerImage.setLayoutY(player.PlayerPosition[1]);
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
}
