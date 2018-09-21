/**
 * This is a class.
 *
 * @author Erik
 */
package com.tnesoftware.rsquestfilter.ui;

import com.tnesoftware.rsquestfilter.Player;
import com.tnesoftware.rsquestfilter.impl.PlayerImpl;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QuestHelper extends Application {

   public static Player player;
   public static HostServices openURL;

   @Override
   public void start(Stage pStage) throws Exception {

      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument1.fxml"));
      openURL = getHostServices();
      Scene scene = new Scene(root);
      pStage.setScene(scene);
      pStage.show();
      pStage.setResizable(false);
      pStage.setTitle("Quest Helper");
      pStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Icon.png")));

   }
   
   @Override
   public void stop() 
   {
	   PlayerSerializer.savePlayer( QuestHelper.player );
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      launch(args);
   }

}
