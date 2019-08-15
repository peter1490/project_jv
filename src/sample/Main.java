package sample;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;

public class Main extends Application {

    private static Label labelError = new Label();
    private static TextField fieldUserName = new TextField();
    private static PasswordField fieldPassword = new PasswordField();
    private static GridPane root = new GridPane();
    public static User user = new User();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        List <Recipes> list = Controller.getRecipes();
        Recipes rec = Controller.getRecipe(1);
        System.out.println(rec.getName());


        Iterator i = list.iterator();

        while(i.hasNext()){
            Object  p = (Recipes) i.next();
            List<Foods> foods = ((Recipes) p).getFoods();
            Iterator j = foods.iterator();
           while(j.hasNext()){
               Object  f = (Foods) j.next();
               System.out.println(((Foods) f).getName());
           }
        }

        System.out.println(Controller.getUser("peterbalivet@gmail.om").getPassword());
        */

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        root.add(labelError, 0, 3, 2,1);

        Label labelTitle = new Label("Enter your user name and password!");

        // Put on cell (0,0), span 2 column, 1 row.
        root.add(labelTitle, 0, 0, 2, 1);

        Label labelUserName = new Label("User Name");

        Label labelPassword = new Label("Password");

        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> labelError.setText(" "));
        loginButton.setOnAction(e -> userLogin(primaryStage));

        GridPane.setHalignment(labelUserName, HPos.RIGHT);

        // Put on cell (0,1)
        root.add(labelUserName, 0, 1);


        GridPane.setHalignment(labelPassword, HPos.RIGHT);
        root.add(labelPassword, 0, 2);

        // Horizontal alignment for User Name field.
        GridPane.setHalignment(fieldUserName, HPos.LEFT);
        root.add(fieldUserName, 1, 1);

        // Horizontal alignment for Password field.
        GridPane.setHalignment(fieldPassword, HPos.LEFT);
        root.add(fieldPassword, 1, 2);

        // Horizontal alignment for Login button.
        GridPane.setHalignment(loginButton, HPos.RIGHT);
        root.add(loginButton, 1, 3);


        Scene scene = new Scene(root, 300, 180);
        primaryStage.setTitle("FFW");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void userLogin(Stage stage){
        String email = fieldUserName.getText();
        String pass = fieldPassword.getText();

        user = Controller.getUser(email);

        if ( user.getEmail() !=  null ) {
            String passReal = user.getPassword();
            BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), passReal);

            if (result.verified){
                labelError.setText("Success !");
                stage.close();
                RecipesView.view();
            }else{
                fieldPassword.clear();
                labelError.setText("Wrong user password !");
            }

        }else{
            fieldUserName.clear();
            fieldPassword.clear();

            labelError.setText("Wrong user email !");
            //labelError.setStyle("-fx-background-color:RED");

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
