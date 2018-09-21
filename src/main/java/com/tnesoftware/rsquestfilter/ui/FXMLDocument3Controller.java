/**
 * This may just end up being scene3 even though its the 2nd document.
 * Will probably change this later so that everything will make sense
 * Reason for not doing now is because i dont feel like figuring out how to
 *  add checkboxes to the cells.
 */
package com.tnesoftware.rsquestfilter.ui;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.tnesoftware.rsquestfilter.tools.Emailer;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Erik
 */
public class FXMLDocument3Controller implements Initializable {

    @FXML
    private TextArea feedback;
    
    @FXML
    private Label usernameLabel;

    @FXML
    private Button btn3, send;
    

    private Stage stage;
    private Parent root;
    private Scene scene;


    /**
     * Used to switch back to the main screen. Loads different fxml doc and set
     * it up to be a new scene. Then it shows the new scene. Two fxml docs
     * because when you try to reference stuff after creating a new one, upon
     * trying to switch back to any previous scenes, some of the item references
     * are null and it just doesn't know what to do.
     *
     * @throws IOException oh well.
     */
    public void button3Action() throws IOException {
        PlayerSerializer.savePlayer(QuestHelper.player);
        stage = (Stage) btn3.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument2.fxml"));
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void sendAction() {
    	if( feedback.getText().trim().length() > 10 )
    	{
            System.out.println("Can we click more than once?");
            String tempInfo = QuestHelper.player.getName() + ":\n\n" + feedback.getText();
            feedback.setDisable(true);
            send.setDisable(true);
            Emailer.emailFeedback(tempInfo);
            feedback.clear();
    	}
        feedback.setDisable(false);
        send.setDisable(false);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLabel.setText(QuestHelper.player.getName());

    }

}
