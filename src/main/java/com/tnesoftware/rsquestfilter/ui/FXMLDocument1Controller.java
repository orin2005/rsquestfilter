/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tnesoftware.rsquestfilter.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.tnesoftware.rsquestfilter.Player;
import com.tnesoftware.rsquestfilter.impl.PlayerImpl;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Erik
 */
public class FXMLDocument1Controller implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private TextField usernameTF;

    @FXML
    private Label error;

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    public void onEnter(ActionEvent ae) throws IOException {
        button1Action();
        System.out.println("test");
    }

    public void button1Action() throws IOException {
        error.setVisible(false);
        if (usernameTF.getText().length() > 0 && usernameTF.getText().length() <= 12) {

            try {

                QuestHelper.player = PlayerSerializer.loadPlayer(usernameTF.getText());
                QuestHelper.player.calculateQuestPoints();

            } catch (Exception e) {
                error.setVisible(true);
                QuestHelper.player = new PlayerImpl(usernameTF.getText());

            }
            try {
                stage = (Stage) btn1.getScene().getWindow();
                root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument2.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                error.setVisible(true);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
