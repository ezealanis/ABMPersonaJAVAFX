/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import models.Genero;
import models.Persona;

public class FormModificacionController implements Initializable {
    
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
    
    private Persona personaModificable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rbMasculino.setToggleGroup(tg);
        rbFemenino.setToggleGroup(tg);

    }

    public void setPersona(Persona p) {

        this.personaModificable = p;
        
        this.txtNombre.setText(p.getNombre());
        this.txtApellido.setText(p.getApellido());
        this.txtDni.setText(String.valueOf(p.getDni()));

        if (p.getGenero() == Genero.FEMENINO) {

            this.tg.selectToggle(rbFemenino);
        } else {
            this.tg.selectToggle(rbMasculino);
        }
        
    }

    public void guardarModificacion() {
        try {
            String nombre = this.txtNombre.getText();
            String apellido = this.txtApellido.getText();
            int dni = Integer.parseInt(this.txtDni.getText());
            Genero genero = Genero.valueOf(((RadioButton) tg.getSelectedToggle()).getText());

            if (!nombre.isEmpty() && !apellido.isEmpty()) {

                personaModificable.setNombre(nombre);
                personaModificable.setApellido(apellido);
                personaModificable.setDni(dni);
                personaModificable.setGenero(genero);
                
            }else{
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException ex) {
            this.mostrarAlerta("Error", "Ingresar solo numero en el DNI");
        } catch (IllegalArgumentException ex){
            this.mostrarAlerta("Error", "Se deben completar todos los campos.");
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
