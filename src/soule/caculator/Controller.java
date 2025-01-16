package soule.caculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    GridPane gridPane;

    @FXML
    TextField screen;

    Float number1;

    Float number2;

    Operation currentOperation;

    final String errorMessage = "Error";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.getChildren().forEach(node -> {

            Button button = (Button) node;

            button.setOnAction(actionEvent -> {
                onButtonTap(button.getText().trim());
            });

        });
    }

    void onButtonTap(String label) {

        if (screen.getText().trim().equals(errorMessage)) screen.clear();

        switch (label) {

            case "+" :
                doOperation(Operation.ADDITION, label);
                break;

            case "-" :
                doOperation(Operation.SUBTRACTION, label);
                break;

            case "*" :
                doOperation(Operation.MULTIPLICATION, label);
                break;

            case "/" :
                doOperation(Operation.DIVISION, label);
                break;

            case "%" :
                doOperation(Operation.MODULO, label);
                break;

            case "reset" :
                screen.clear();
                number1 = null;
                number2 = null;
                currentOperation = null;
                break;

            case "delete" :
                if (!screen.getText().isEmpty()) {
                    Character lastChar = screen.getText().charAt(screen.getText().length()-1);
                    if (
                            (lastChar.equals(Operation.ADDITION.getChar())
                            || lastChar.equals(Operation.SUBTRACTION.getChar())
                            || lastChar.equals(Operation.DIVISION.getChar())
                            || lastChar.equals(Operation.MULTIPLICATION.getChar())
                            || lastChar.equals(Operation.MODULO.getChar())
                                    && lastChar.equals(currentOperation.getChar()))
                    ) currentOperation = null;

                    if (number1 != null && number2 != null && currentOperation != null && showCorrectly(number2).length()==2 && number2<0) {
                        screen.setText(showCorrectly(number1)+currentOperation.getSymbol());
                    } else if (screen.getText().length() == 2 && Character.valueOf(screen.getText().charAt(0)).equals('-')) screen.clear();
                    else screen.setText(screen.getText().substring(0,screen.getText().length()-1));

                    getValues();
                }
                break;

            case "+/-" :
                if (currentOperation != null && number2 != null && number1 != null) {
                    number2 *= -1;
                    screen.setText(showCorrectly(number1)+currentOperation.getSymbol()+showCorrectly(number2));
                }
                else {
                    number1 *= -1;
                    screen.setText(showCorrectly(number1));
                    if (currentOperation != null) screen.setText(screen.getText()+currentOperation.getSymbol());
                };
                break;

            case "=" :
                equal(label);
                break;

            case "." :
                System.out.println("Function unavailable !");
                break;

            default:
                screen.setText(screen.getText() + label);
                getValues();
                break;

        }

    }

    void getValues() {
        if (!screen.getText().isEmpty()) {
            if (number1 != null && currentOperation != null) {
                String[] expression;
                if (number1 < 0) {
                    String modExp = screen.getText().substring(1);
                    expression = modExp.split(currentOperation.getRegexSymbol(), 2);
                    expression[0] = "-"+expression[0];
                }
                else expression = screen.getText().split(currentOperation.getRegexSymbol(), 2);
                number2 = expression.length==2 && !(expression[1]).isEmpty() ? Float.parseFloat(expression[1]) : null;
            }
            else number1 = Float.parseFloat(screen.getText());
        }
    }

    void equal(String label) {
        if(number1!=null && number2!=null) {

            switch (currentOperation) {
                case ADDITION -> number1 = number1 + number2;
                case SUBTRACTION -> number1 = number1 - number2;
                case MULTIPLICATION -> number1 = number1 * number2;
                case DIVISION -> number1 = number1 / number2;
                case MODULO -> number1 = number1 % number2;
            }

            if ((currentOperation==Operation.DIVISION || currentOperation==Operation.MODULO) && number2==0) {
                number1 = null;
                screen.setText(errorMessage);
                currentOperation = null;
            } else screen.setText(showCorrectly(number1));

            number2 = null;

            currentOperation = (currentOperation!=null && label.equals("=")) ? null : currentOperation;

        }
    }

    void doOperation(Operation newOperation, String label) {
        if(!screen.getText().isEmpty() && number1!=null) {

            if (currentOperation != null) {
                equal(label);
            }

            if (number1 != null) {
                currentOperation = newOperation;

                screen.setText(showCorrectly(number1) + currentOperation.getSymbol());
            }

        }
    }

    String showCorrectly(Float number) {
        Float num = Float.parseFloat(number.intValue()+".0");
        return number.equals(num) ? ""+number.intValue() : number.toString();
    }

}
