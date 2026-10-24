package soule.crossword.controllers;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import soule.crossword.utils.Clue;
import soule.crossword.utils.Crossword;
import soule.crossword.utils.Database;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CrosswordController implements Initializable {

    @FXML
    private GridPane board;

    @FXML
    private ListView<Clue> hListview;

    @FXML
    private ListView<Clue> vListview;

    Crossword crossword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Database database = new Database();
        crossword = Crossword.createPuzzle(database, database.getRandomPuzzleNumber());

        for (int i = 0; i < crossword.getHeight(); i++) {
            for (int j = 0; j < crossword.getWidth(); j++) {
                if (crossword.getCell(i, j).isBlack()) {
                    crossword.getCell(i, j).setStyle("-fx-background-color: black");
                    crossword.getCell(i, j).setDisable(true);
                    crossword.getCell(i , j).setFocusTraversable(false);
                }

                double height = board.getPrefHeight() / crossword.getHeight();
                double width = board.getPrefWidth() / crossword.getWidth();

                crossword.getCell(i, j).setPrefHeight(height);
                crossword.getCell(i, j).setPrefWidth(width);

                createFocusListener(i, j);

                setKeyBoardListener(i, j);

                setScaleTransition(i, j);

                board.add(crossword.getCell(i, j), j, i);
            }
        }

        hListview.setItems(crossword.getHorizontalClues());
        vListview.setItems(crossword.getVerticalClues());

        setListViewsListeners();

    }

    void createFocusListener(int i, int j) {
        crossword.getCell(i, j).focusedProperty().addListener(observable -> {
            hListview.getStyleClass().clear();
            vListview.getStyleClass().clear();

            if (crossword.isHorizontal()) {
                hListview.getStyleClass().add("current-clue");
            } else {
                vListview.getStyleClass().add("current-clue");
            }

            Clue selectedHClue = null;//= crossword.getHorizontalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getHorizontalClue())).get(0);
            Clue selectedVClue = null;//= crossword.getVerticalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getVerticalClue())).get(0);

            if (crossword.getCell(i, j).getHorizontalClue() != null && crossword.getCell(i, j).getVerticalClue() != null) {
                selectedHClue = crossword.getHorizontalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getHorizontalClue())).get(0);
                selectedVClue = crossword.getVerticalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getVerticalClue())).get(0);
            } else if (crossword.getCell(i, j).getVerticalClue() == null) {
                selectedHClue = crossword.getHorizontalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getHorizontalClue())).get(0);
            } else {
                selectedVClue = crossword.getVerticalClues().filtered(clue -> clue.getClue().equals(crossword.getCell(i, j).getVerticalClue())).get(0);
            }

            hListview.scrollTo(selectedHClue);
            hListview.getSelectionModel().select(selectedHClue);

            vListview.scrollTo(selectedVClue);
            vListview.getSelectionModel().select(selectedVClue);

            if (crossword.getCell(i, j).getVerticalClue() == null) {
                vListview.getSelectionModel().clearSelection();
            }

            if (crossword.getCell(i, j).getHorizontalClue() == null) {
                hListview.getSelectionModel().clearSelection();
            }

        });
    }

    void setKeyBoardListener(int i, int j) {
        crossword.getCell(i, j).setOnKeyTyped(keyEvent -> {
            // Touches A à Z
            if (((int)keyEvent.getCharacter().charAt(0)>=65
                    && (int)keyEvent.getCharacter().charAt(0)<=90)
                    || ((int)keyEvent.getCharacter().charAt(0)>=97
                    && (int)keyEvent.getCharacter().charAt(0)<=122)) {
                if (!keyEvent.getCharacter().toUpperCase().equals(crossword.getCell(i, j).getProposition())) {
                    crossword.getCell(i, j).setStyle("-fx-background-color: white; -fx-border-style: solid");
                }
                crossword.getCell(i, j).setProposition(keyEvent.getCharacter().toUpperCase(Locale.ROOT));
                crossword.getCell(i, j).setText(keyEvent.getCharacter().toUpperCase());
                if (crossword.isHorizontal()) {
                    if (crossword.correctCoords(i, j+1) && !crossword.getCell(i, j+1).isBlack()) {
                        crossword.getCell(i, j+1).requestFocus();
                    }
                } else {
                    if (crossword.correctCoords(i+1, j) && !crossword.getCell(i+1, j).isBlack()) {
                        crossword.getCell(i+1, j).requestFocus();
                    }
                }
            }
        });
        crossword.getCell(i, j).setOnKeyPressed(keyEvent -> {

            // Touche entrée
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {

                Clue clue = crossword.isHorizontal()
                        ? hListview.getSelectionModel().getSelectedItem()
                        : vListview.getSelectionModel().getSelectedItem();

                if (crossword.isHorizontal()) {
                    int k = clue.getColumn();
                    while (crossword.correctCoords(i, k) && !crossword.isBlackSquare(i, k)) {
                        if (crossword.getSolution(i, k) == crossword.getProposition(i, k)) {
                            crossword.getCell(i, k).setStyle("-fx-background-color: green");
                        }
                        k++;
                    }
                } else {
                    int k = clue.getRow();
                    while (crossword.correctCoords(k, j) && !crossword.isBlackSquare(k, j)) {
                        if (crossword.getSolution(k, j) == crossword.getProposition(k, j)) {
                            crossword.getCell(k, j).setStyle("-fx-background-color: green");
                        }
                        k++;
                    }
                }

            }

            // Touches de suppression
            if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                crossword.getCell(i, j).clear();
                crossword.getCell(i, j).setProposition("");

                crossword.getCell(i, j).setStyle("-fx-background-color: white; -fx-border-style: solid");

                if (crossword.isHorizontal() && crossword.correctCoords(i, j-1) && !crossword.isBlackSquare(i, j-1)) {
                    crossword.getCell(i, j-1).requestFocus();
                }

                if (!crossword.isHorizontal() && crossword.correctCoords(i-1, j) && !crossword.isBlackSquare(i-1, j)) {
                    crossword.getCell(i-1, j).requestFocus();
                }
            }

            // Touche UP
            if (keyEvent.getCode().equals(KeyCode.UP) && crossword.correctCoords(i-1, j) && !crossword.isBlackSquare(i-1, j)) {
                crossword.setHorizontal(false);
                crossword.getCell(i-1, j).requestFocus();
            }

            // Touche DOWN
            if (keyEvent.getCode().equals(KeyCode.DOWN) && crossword.correctCoords(i+1, j) && !crossword.isBlackSquare(i+1, j)) {
                crossword.setHorizontal(false);
                crossword.getCell(i+1, j).requestFocus();
            }

            // Touche LEFT
            if (keyEvent.getCode().equals(KeyCode.LEFT) && crossword.correctCoords(i, j-1) && !crossword.isBlackSquare(i, j-1)) {
                crossword.setHorizontal(true);
                crossword.getCell(i, j-1).requestFocus();
            }

            // Touche RIGHT
            if (keyEvent.getCode().equals(KeyCode.RIGHT) && crossword.correctCoords(i, j+1) && !crossword.isBlackSquare(i, j+1)) {
                crossword.setHorizontal(true);
                crossword.getCell(i, j+1).requestFocus();
            }

        });
    }

    void setScaleTransition(int i, int j) {
        ChangeListener<String> changeListener = (observableValue, s, t1) -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), crossword.getCell(i, j));
            scaleTransition.setFromX(0);
            scaleTransition.setFromY(0);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        };
        crossword.getCell(i, j).textProperty().addListener(changeListener);
    }

    void setListViewsListeners() {
        hListview.setOnMouseEntered(mouseEvent -> {
            hListview.requestFocus();
        });

        hListview.setOnMouseExited(mouseEvent -> {
            if (hListview.getSelectionModel().getSelectedItem() != null) {
                crossword.getCell(hListview.getSelectionModel().getSelectedItem().getRow(), hListview.getSelectionModel().getSelectedItem().getColumn()).requestFocus();
            }
        });

        hListview.setOnMouseClicked(mouseEvent -> {
            crossword.setHorizontal(true);
            crossword.getCell(hListview.getSelectionModel().getSelectedItem().getRow(), hListview.getSelectionModel().getSelectedItem().getColumn()).requestFocus();
        });

        vListview.setOnMouseEntered(mouseEvent -> {
            vListview.requestFocus();
        });

        vListview.setOnMouseExited(mouseEvent -> {
            if (vListview.getSelectionModel().getSelectedItem() != null) {
                crossword.getCell(vListview.getSelectionModel().getSelectedItem().getRow(), vListview.getSelectionModel().getSelectedItem().getColumn()).requestFocus();
            }
        });

        vListview.setOnMouseClicked(mouseEvent -> {
            crossword.setHorizontal(false);
            crossword.getCell(vListview.getSelectionModel().getSelectedItem().getRow(), vListview.getSelectionModel().getSelectedItem().getColumn()).requestFocus();
        });
    }

}
