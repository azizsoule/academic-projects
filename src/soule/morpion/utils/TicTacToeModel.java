package soule.morpion.utils;

import javafx.beans.binding.*;
import javafx.beans.property.*;

public class TicTacToeModel {

    /**
     * Taille du plateau de jeu (pour être extensible).
     */
    public final static int BOARD_WIDTH = 3;
    public final static int BOARD_HEIGHT = 3;

    /**
     * Nombre de pièces alignés pour gagner (idem).
     */
    private final static int WINNING_COUNT = 3;

    /**
     * Joueur courant.
     */
    private final ObjectProperty<Owner> turn = new SimpleObjectProperty<>(Owner.FIRST);

    /**
     * Vainqueur du jeu, NONE si pas de vainqueur.
     */
    private final ObjectProperty<Owner> winner = new SimpleObjectProperty<>(Owner.NONE);

    /**
     * Plateau de jeu.
     */
    private final ObjectProperty<Owner>[][] board;

    /**
     * Positions gagnantes.
     */
    private final BooleanProperty[][] winningBoard;

    private final IntegerProperty noneScore = new SimpleIntegerProperty(BOARD_HEIGHT*BOARD_WIDTH);

    private final IntegerProperty firstScore = new SimpleIntegerProperty(0);

    private final IntegerProperty secondScore = new SimpleIntegerProperty(0);

    /**
     * Constructeur privé.
     */
    private TicTacToeModel() {
        this.board = new ObjectProperty[BOARD_HEIGHT][BOARD_WIDTH];
        this.winningBoard = new BooleanProperty[BOARD_HEIGHT][BOARD_WIDTH];

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                this.board[i][j] = new SimpleObjectProperty<Owner>(Owner.NONE);
                this.winningBoard[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    /**
     * @return la seule instance possible du jeu.
     */
    public static TicTacToeModel getInstance() {
        return TicTacToeModelHolder.INSTANCE;
    }

    /**
     * Classe interne selon le pattern singleton.
     */
    private static class TicTacToeModelHolder {
        private static final TicTacToeModel INSTANCE = new TicTacToeModel();
    }

    public void restart() {
        noneScore.setValue(BOARD_HEIGHT*BOARD_WIDTH);
        firstScore.setValue(0);
        secondScore.setValue(0);

        setWinner(Owner.NONE);

        turn.setValue(Owner.FIRST);

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j].setValue(Owner.NONE);
                winningBoard[i][j].setValue(false);
            }
        }
    }

    public final ObjectProperty<Owner> turnProperty() {
        return turn;
    }

    public final ObjectProperty<Owner> getSquare(int row, int column) {
        return board[row][column];
    }

    public final BooleanProperty getWinningSquare(int row, int column) {
        return winningBoard[row][column];
    }

    /**
     * Cette fonction ne doit donner le bon résultat que si le jeu
     * est terminé. L’affichage peut être caché avant la fin du jeu.
     *
     * @return résultat du jeu sous forme de texte
     */
    public final StringExpression getEndOfGameMessage() {
        return Bindings.when(winner.isEqualTo(Owner.FIRST)).then("Game over. Le gagnant est le premier joueur.").otherwise(Bindings.when(winner.isEqualTo(Owner.SECOND)).then("Game over. Le gagnant est le second joueur.").otherwise(Bindings.when(noneScore.isEqualTo(0).and(winner.isEqualTo(Owner.NONE))).then("Match nul.").otherwise("")));
    }

    public void setWinner(Owner winner) {
        this.winner.setValue(winner);
    }

    public boolean validSquare(int row, int column) {
        return row>=0 && row<=BOARD_HEIGHT && column>=0 && column<=BOARD_WIDTH;
    }

    public void nextPlayer() {
        turn.setValue(turn.getValue().opposite());
    }

    /**
     * Jouer dans la case (row, column) quand c’est possible.
     */
    public void play(int row, int column) {
        if (legalMove(row, column).getValue()) {
            board[row][column].setValue(turn.getValue());

            if (checkAlignment(row, column)) {
                setWinner(turn.getValue());
            }

            if (turn.getValue().equals(Owner.FIRST)) {
                firstScore.setValue(firstScore.getValue()+1);
            }

            if (turn.getValue().equals(Owner.SECOND)) {
                secondScore.setValue(secondScore.getValue()+1);
            }

            if (!gameOver().getValue()) {
                nextPlayer();
            } else {
                turn.setValue(Owner.NONE);
            }

            noneScore.setValue(noneScore.getValue()-1);
        }
    }

    private boolean checkAlignment(int row, int column) {
        boolean a = checkHandVAlignment(row, column);
        boolean b = checkDiagAlignment(row, column);
        boolean c = checkOtherDiagAlignment(row, column);
        return a || b || c;
    }

    private boolean checkHandVAlignment(int row, int column) {

        // Variables pour l'horizontal
        int hFirst = 0;
        int hLast = 0;
        int hAlignCounter = 0;

        // variables pour la verticale
        int vFirst = 0;
        int vLast = 0;
        int vAlignCounter = 0;

        // compter alignement horizontal (on fixe le numéro de ligne et on fait varier le numéro de colonne)
        boolean b = true;
        for (int i = 0; i < BOARD_WIDTH && b; i++) {
            if (board[row][i].getValue().equals(turn.getValue())) {
                hAlignCounter++;
                if (hAlignCounter == 1) hFirst = i;
                if (hAlignCounter >= WINNING_COUNT) hLast = i;
            } else {
                if (hAlignCounter < WINNING_COUNT) hAlignCounter = 0;
                else b = false;
            }
        }

        // compter alignement vertical (on fixe le numéro de colonne et on fait varier le numéro de ligne)
        b = true;
        for (int i = 0; i < BOARD_HEIGHT && b; i++) {
            if (board[i][column].getValue().equals(turn.getValue())) {
                vAlignCounter++;
                if (vAlignCounter == 1) vFirst = i;
                if (vAlignCounter >= WINNING_COUNT) vLast = i;
            } else {
                if (vAlignCounter < WINNING_COUNT) vAlignCounter = 0;
                else b = false;
            }
        }

        // marquer les pions alignés horizontalement
        if (hAlignCounter >= WINNING_COUNT) {
            for (int i = hFirst; i <= hLast; i++) {
                winningBoard[row][i].setValue(true);
            }
        }

        // marquer les pions alignés verticalement
        if (vAlignCounter >= WINNING_COUNT) {
            for (int i = vFirst; i <= vLast ; i++) {
                winningBoard[i][column].setValue(true);
            }
        }

        return hAlignCounter>=WINNING_COUNT || vAlignCounter>=WINNING_COUNT;
    }

    private boolean checkDiagAlignment(int row, int column) {

        int diagAlignCounter = 0;

        int result = column - row;

        Square first = new Square();
        Square last = new Square();

        if (result >= 0) {
            // Sur la diagonale principale ou au dessus de la diagonale principale
            diagAlignCounter = diagTraversal(0, BOARD_WIDTH-result-1, result,first, last, DiagType.PRINCIPAL);
        } else {
            // En dessous de la diagonale principale
            diagAlignCounter = diagTraversal(-1 * result, BOARD_HEIGHT-1, 0,first, last, DiagType.PRINCIPAL);
        }

        if (diagAlignCounter >= WINNING_COUNT) {
            int j = first.j;
            for (int i = first.i; i <= last.i ; i++) {
                winningBoard[i][j].setValue(true);
                j++;
            }
        }

        return diagAlignCounter >= WINNING_COUNT;

    }

    private boolean checkOtherDiagAlignment(int row, int column) {

        int otherDiagAlignCounter = 0;

        int result = row + column;

        Square first = new Square();
        Square last = new Square();

        if (result < BOARD_HEIGHT) {
            otherDiagAlignCounter = diagTraversal(0, result, result, first, last, DiagType.OTHER);
        } else {
            otherDiagAlignCounter = diagTraversal(result - (BOARD_WIDTH-1), BOARD_WIDTH-1, BOARD_WIDTH-1, first, last, DiagType.OTHER);
        }

        if (otherDiagAlignCounter >= WINNING_COUNT) {
            int j = first.j;
            for (int i = first.i; i <= last.i ; i++) {
                winningBoard[i][j].setValue(true);
                j--;
            }
        }

        return otherDiagAlignCounter >= WINNING_COUNT;

    }

    int diagTraversal (int rowStartIndex, int rowEndIndex, int j, Square first, Square last, DiagType diagType) {
        boolean b = true;
        int counter = 0;

        for (int i = rowStartIndex; i <= rowEndIndex /*&& j <= BOARD_WIDTH-1*/ && b; i++) {
            if (board[i][j].getValue().equals(turn.getValue())) {
                counter++;
                if (counter == 1) {
                    first.i = i;
                    first.j = j;
                }
                if (counter >= WINNING_COUNT) {
                    last.i = i;
                    last.j = j;
                }
            } else {
                if (counter < WINNING_COUNT) counter = 0;
                else b = false;
            }

            if (diagType == DiagType.PRINCIPAL) j++;
            else j--;

        }

        return counter;
    }

    /**
     * @return true s’il est possible de jouer dans la case
     * c’est-à-dire la case est libre et le jeu n’est pas terminé
     */
    public BooleanBinding legalMove(int row, int column) {
        return board[row][column].isEqualTo(Owner.NONE).and(gameOver().not());
    }

    public NumberExpression getScore(Owner owner) {
        NumberExpression score = null;
        switch (owner) {
            case NONE:
                score = noneScore;
                break;

            case FIRST:
                score = firstScore;
                break;

            case SECOND:
                score = secondScore;
                break;
        }
        return score;
    }

    /**
     * @return true si le jeu est terminé
     * (soit un joueur a gagné, soit il n’y a plus de cases à jouer)
     */
    public BooleanBinding gameOver() {
        return winner.isNotEqualTo(Owner.NONE).or(noneScore.isEqualTo(0));
    }

}

enum DiagType {
    PRINCIPAL,
    OTHER
}

class Square {
    int i = 0;
    int j = 0;

    @Override
    public String toString() {
        return "Square{" +
                "i=" + i +
                ", j=" + i +
                '}';
    }
}