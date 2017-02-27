
package ui;

import controller.GameBoardController;
import engine.Tile;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class TileView extends ImageView{
    
    private Tile tile;
    private String fileName;
    private boolean belongsToPlayer;
    private GameBoardController controller;
    
    public TileView(boolean isPlayersTile, GameBoardController controller) {
        tile = null;
        fileName = "";
        this.belongsToPlayer = isPlayersTile;
        this.controller = controller;
        
        this.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = this.startDragAndDrop(TransferMode.ANY);
            
            Image img = new Image("/resources/Images/Small/" + fileName);
            db.setDragView(img);
            
            ClipboardContent content = new ClipboardContent();
            content.putImage(this.getImage());
            controller.currentlyDraggedTile = this;
            db.setContent(content);
            event.consume();
        });
        
        this.setOnDragDone((DragEvent event) -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                controller.completeDragOperation();
            } else {
                this.setImage(event.getDragboard().getImage());
            }
            event.consume();
        });
    }
    
    public TileView(Tile tile, boolean isPlayersTile, GameBoardController controller){
        this(isPlayersTile, controller);
        setTile(tile);
    }
    
    public String fileSourece(){
        return fileName;
    }
    
    public Tile getTile(){
        return tile;
    }
    
    public void setTile(Tile tile) {
        this.tile = tile;

        String str1= "tile";
        String str2="";
        String str3 ="";
        Image image;

        if (tile.isJoker())
        {
            if (tile.getColor() == Tile.Colors.RED) {
                fileName = "redJoker.png";
                image = new Image("/resources/Images/" + fileName);

            }
            else {
                fileName = "blackJoker.png";
                image = new Image("/resources/Images/" + fileName);
            }
        }
        else
        {
            str2 = Integer.toString(tile.getNum());
            switch(tile.getColor())
            {
                case RED:
                    str3="red.png";
                    break;

                case BLACK:
                    str3="black.png";
                    break;

                case YELLOW:
                    str3="yellow.png";
                    break;

                case BLUE:
                    str3="blue.png";
                    break;
            }

            image = new Image("/resources/Images/" + str1 + str2 + str3);
            fileName = str1 + str2 + str3;
        }
        super.setImage(image);
        super.setFitHeight(38);
        super.setFitWidth(28);
    }
    
    public void makeEmpty() {
        this.setImage(null);
        this.tile = null;
        this.fileName = "";
    }
    
    public boolean isEmpty() {
        return tile == null;
    }
    
    public boolean belongsToPlayer() {
        return belongsToPlayer;
    }
    
    public void setBelongToPlayer(boolean belongsToPlayer) {
        this.belongsToPlayer = belongsToPlayer;
    }
}
