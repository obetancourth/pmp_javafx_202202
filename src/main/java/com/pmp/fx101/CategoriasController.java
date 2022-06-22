/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pmp.fx101;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import com.pmp.dao.Categoria;
import com.pmp.dao.CategoriaDao;
import java.util.ArrayList;
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
        categorias = CategoriaDao.obtenerTodos();
        System.out.println(String.valueOf(categorias.size()));
        tdCodigo.setCellValueFactory(new PropertyValueFactory("codigo")); //getCodigo 
        tdNombre.setCellValueFactory(new PropertyValueFactory("nombre")); //getNombre
        tdEstado.setCellValueFactory(new PropertyValueFactory("estado")); //getEstado
        
        
        tblCategorias.setItems(FXCollections.observableArrayList(categorias));

    }    
    
    @FXML
    private void btnVisualizar_onclick(ActionEvent event) {
    }

    @FXML
    private void btnNuevo_onclick(ActionEvent event) {
    }

    @FXML
    private void btnEditar_onclick(ActionEvent event) {
    }

    @FXML
    private void btnEliminar_onclick(ActionEvent event) {
    }

}
