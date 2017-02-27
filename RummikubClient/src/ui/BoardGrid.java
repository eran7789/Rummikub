
package ui;

import controller.GameBoardController;
import engine.GameBoard;
import engine.PlayerBoard;
import engine.Tile;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;



public class BoardGrid extends GridPane {
    
    private final int numOfRows;
    private final int numOfCols;
    private final boolean isPlayerBoard;
    private GameBoardController controller;
    
    TileView[][] tileViewMatrix = new TileView[9][20];
    
    public BoardGrid (PlayerBoard board, GameBoardController controller) {
       TileView tile;
       isPlayerBoard = true;
       numOfRows = 2;
       numOfCols = 20;
       this.controller = controller;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                
                if (i*numOfCols + j < board.size())
                    tile = new TileView
                        (board.getTile(i * numOfCols + j), 
                                isPlayerBoard, controller);
                else
                    tile = new TileView(isPlayerBoard, controller);
                Pane pane = createCell(tile);
                tileViewMatrix[i][j] = (TileView)pane.getChildren().get(0);
                super.add(pane, j, i);
            }
        }
    }
    
    public BoardGrid (GameBoardController controller){
        TileView tile;
        isPlayerBoard = false;
        numOfRows = 9;
        numOfCols = 20;
        this.controller = controller;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                tile = new TileView(isPlayerBoard, controller);
                Pane pane = createCell(tile);
                tileViewMatrix[i][j] = (TileView)pane.getChildren().get(0);
                super.add(pane, j, i);
            }
        }
    }
    
    public BoardGrid(GameBoard board, GameBoardController controller) {
        this(controller);
        final int SpaceBetween = 2;
        boolean hasFoundASpot;
        int rowIndex = -1;
        
        for (int i = 0; i < board.sequences().size(); i++) {
            hasFoundASpot = false;
            if (i < numOfRows) {
                for (int j = 0; j < board.getSequenceSize(i); j++) {
                    tileViewMatrix[i][j+1].
                            setTile(board.getSequence(i).getTileByIndex(j));
                }
            } else {
                if (rowIndex >= numOfRows)
                    rowIndex = -1;
                int startOfEmptySpace = 1;
                while (!hasFoundASpot) {
                    rowIndex++;
                    while (!tileViewMatrix[rowIndex][startOfEmptySpace].isEmpty())
                        startOfEmptySpace += SpaceBetween;
                    startOfEmptySpace++;
                    if (numOfCols - startOfEmptySpace > board.getSequenceSize(i))
                        hasFoundASpot = true;
                    else 
                        hasFoundASpot = false;
                }
                for (int j = 0; j < board.getSequenceSize(i); j++) {
                    tileViewMatrix[rowIndex][j+startOfEmptySpace].
                            setTile(board.getSequence(i).getTileByIndex(j));
                }
            }
        }
    }

    private Pane createCell(TileView tile) {
        tile.setFitHeight(39);
        tile.setFitWidth(30);
        
        AnchorPane pane = new AnchorPane(tile);
        pane.setOnDragOver((DragEvent event) -> {
            if (controller.isValidDrag((TileView) event.getGestureSource(), pane)) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
        
        pane.setOnDragEntered((DragEvent event) -> {
            controller.dragTargetPane = pane;
            if (controller.isValidDrag((TileView) event.getGestureSource(), pane)) {
                ((TileView)pane.getChildren().get(0)).setImage(event.getDragboard().getImage());
            }
            event.consume();
        });
        
        pane.setOnDragExited((DragEvent event) -> {
            if (controller.isValidDrag((TileView) event.getGestureSource(), pane)) {
                if (((TileView)controller.dragTargetPane.getChildren().get(0)).isEmpty())
                    ((TileView)pane.getChildren().get(0)).setImage(null);
            }
            event.consume();
        });
        
        pane.setOnDragDropped((DragEvent event) -> {
            if (event.getDragboard().hasImage()) {
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });   
        
        
        return pane;
    }
    
    public boolean isPlayer(){
        return isPlayerBoard;
    }
    
    public TileView getTile(int row, int col) {
        for (Node node : super.getChildren()) {
            if (GridPane.getColumnIndex(node) == col &&
                    GridPane.getRowIndex(node) == row) {
                return (TileView)((Pane) node).getChildren().get(0);
            }
        }
        return null;
    }
    
    public void setTile(Tile tile, int row, int col) {
        for (Node node : super.getChildren()) {
            if (GridPane.getColumnIndex(node) == col &&
                    GridPane.getRowIndex(node) == row) {
                ((TileView)((Pane) node).getChildren().get(0)).setTile(tile);
            }
        }
    }
    
    public TileView[][] getTileViewMatrix() {
        return tileViewMatrix;
    }
}
