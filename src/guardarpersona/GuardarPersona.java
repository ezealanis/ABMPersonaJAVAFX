
package guardarpersona;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuardarPersona extends Application {

    public static void main(String[] args) {
        
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formPrincipal.fxml"));

        Scene scene = new Scene(loader.load());
        
        stage.setScene(scene);
        stage.setTitle("App Alumno");
        stage.show();
    }
    
}
