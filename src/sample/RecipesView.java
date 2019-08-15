package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RecipesView  {
    private static GridPane root = new GridPane();
    private static Stage secondaryStage = new Stage();

    public static void view() {

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Scene scene = new Scene(root, 300, 180);
        secondaryStage.setTitle("FFW");
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }


}
