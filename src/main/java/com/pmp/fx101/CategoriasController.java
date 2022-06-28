/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pmp.fx101;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import com.pmp.dao.Categoria;
import com.pmp.dao.CategoriaDao;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author obetancourth
 */
public class CategoriasController implements Initializable {


    @FXML
    private TableView<Categoria> tblCategorias;
    @FXML
    private Button btnVisualizar;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    
    private Categoria selectedCategoria;
    private ArrayList<Categoria> categorias;
    @FXML
    private TableColumn<Categoria, Integer> tdCodigo;
    @FXML
    private TableColumn<Categoria, String> tdNombre;
    @FXML
    private TableColumn<Categoria, String> tdEstado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategoriaDao.setup();
        tdCodigo.setCellValueFactory(new PropertyValueFactory("codigo")); //getCodigo 
        tdNombre.setCellValueFactory(new PropertyValueFactory("nombre")); //getNombre
        tdEstado.setCellValueFactory(new PropertyValueFactory("estado")); //getEstado
        
        this.loadCategorias();

    }
    
    private void loadCategorias() {
        categorias = CategoriaDao.obtenerTodos();
        tblCategorias.setItems(FXCollections.observableArrayList(categorias));
    }
    
    private Categoria loadCategoryForm(Categoria categoria, String mode) throws IOException {
        try {
            FXMLLoader modal = App.getFXMLLoader("categoria");
            Parent modalObject = modal.load();
            CategoriaController modalForm = (CategoriaController) modal.getController();
            modalForm.setCategoria(categoria);
            modalForm.setMode(mode);
            App.loadFXMLModal(modalObject);
            // Post Cierre
            if (modalForm.getIsConfirmed()) {
                //Dependiendo de la accion a realizar se debe sincronizar con la db
                
                switch (mode) {
                    case "INS":
                        CategoriaDao.agregarNuevo(categoria);
                        break;
                    case "UPD":
                        CategoriaDao.actualizar(categoria);
                        break;
                    case "DEL":
                        CategoriaDao.eliminarCategoria(categoria.getCodigo());
                        break;
                }
                this.loadCategorias();
            }
            return null;
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }
    
    private Categoria getSelectedCategoria(){
        return (Categoria) tblCategorias.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void btnVisualizar_onclick(ActionEvent event) {
        // Sacar el objetico sellecionado del TbleView
        try {
            Categoria selectedCategoria = getSelectedCategoria();
            this.loadCategoryForm(selectedCategoria, "DSP");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void btnNuevo_onclick(ActionEvent event) {
        try {
            Categoria newCategoria = new Categoria();
            newCategoria.setEstado("ACT");
            newCategoria.setNombre("");
            this.loadCategoryForm(newCategoria, "INS");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void btnEditar_onclick(ActionEvent event) {
        try {
            Categoria selectedCategoria = getSelectedCategoria();
            this.loadCategoryForm(selectedCategoria, "UPD");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void btnEliminar_onclick(ActionEvent event) {
        try {
            Categoria selectedCategoria = getSelectedCategoria();
            this.loadCategoryForm(selectedCategoria, "DEL");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
 