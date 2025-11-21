package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Genero;
import models.GestorArchivos;
import models.Persona;

public class FormPrincipalController implements Initializable {

    @FXML
    private ListView<Persona> viewLista;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDni;

    @FXML
    private ToggleGroup tg = new ToggleGroup();

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private RadioButton rbFemenino;

    private List<Persona> lista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.lista = new ArrayList<>();
        rbMasculino.setToggleGroup(tg);
        rbFemenino.setToggleGroup(tg);

    }

    @FXML
    public void guardar(ActionEvent e) {

        try {
            String nombre = this.txtNombre.getText();
            String apellido = this.txtApellido.getText();
            int dni = Integer.parseInt(this.txtDni.getText());
            Genero genero = Genero.valueOf(((RadioButton) tg.getSelectedToggle()).getText());

            if (nombre != null && apellido != null) {

                Persona p = new Persona(nombre, apellido, dni, genero);

                if (!this.lista.contains(p)) {
                    this.lista.add(p);
                }
            }
        } catch (NumberFormatException ex) {
            this.mostrarAlerta("Error", "Ingresar solo numero en el DNI");
        }

        mostrarLista();
    }

    @FXML
    public void cerrar(ActionEvent e) {

        Stage stage = (Stage) this.btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void mostrarLista() {

        for (Persona persona : lista) {
            System.out.println(persona);
        }

        System.out.println("*****************\n");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void guardarCSV() {

        GestorArchivos.guardarCSV(this.lista);

    }

    @FXML
    public void leerCSV() {
        lista = GestorArchivos.leerCSV();

        this.viewLista.getItems().addAll(lista);
    }

    @FXML
    public void leerSerial() {
        lista = GestorArchivos.deserializar();

        this.viewLista.getItems().addAll(lista);
    }

    @FXML
    public void guardarSerial() {

        try {

            GestorArchivos.serializarLista(this.lista);
        } catch (IOException e) {
            this.mostrarAlerta("Error", "Error al serializar");
        }
    }

    @FXML
    public void modificar() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formModificacion.fxml"));
        Scene scene = new Scene(loader.load());

        FormModificacionController controller = loader.getController();
        controller.setPersona(this.viewLista.getSelectionModel().getSelectedItem());

        Stage stageModificar = new Stage();
        stageModificar.setTitle("Modificacion");
        stageModificar.initModality(Modality.APPLICATION_MODAL);

        stageModificar.setScene(scene);
        stageModificar.setTitle("App Alumno");
        stageModificar.showAndWait();

        this.viewLista.refresh();
    }

}
