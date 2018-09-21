/**
 * This may just end up being scene3 even though its the 2nd document.
 * Will probably change this later so that everything will make sense
 * Reason for not doing now is because i dont feel like figuring out how to
 *  add checkboxes to the cells.
 */
package com.tnesoftware.rsquestfilter.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.tnesoftware.rsquestfilter.Quest;
import com.tnesoftware.rsquestfilter.Skill.Type;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Erik
 */
public class FXMLDocument2Controller implements Initializable {

    @FXML
    private TextArea reqTA, skillReqs, itemReqs;

    @FXML
    private CheckBox hideComplete, onlyDoable;

    @FXML
    private Button btn2, btn3;

    @FXML
    private Group skillGroup, reqGroup;
    
    @FXML
    private TextField questSearch;
    
    @FXML
    private Label rhqGuide, wikiGuide, usernameLabel, showStats, showReqs,
            attack, strength, defence, range, magic, prayer, runecrafting,
            construction, hitpoints, agility, herblore, thieving, crafting,
            fletching, slayer, hunter, mining, smithing, fishing, cooking,
            firemaking, woodcutting, farming, total;

    @FXML
    private TableView<Table> questTable;

    @FXML
    private TableColumn<Table, String> name, length, qp, isComplete;

    @FXML
    private ImageView stats, refresh;

    
    private Stage stage;
    private Parent root;
    private Scene scene;
    private String questName;
    private int rowIndex;

    private ObservableList<Table> data = FXCollections.observableArrayList();

    public void setQuests() {
        List<Quest> s = QuestHelper.player.getQuestManager().getAllQuests();
        for (Quest quest : s) {
            data.add(new Table(quest));
        }

    }
    
    public void searchForQuest() {
        //put trentin logic here
    	List<Quest> s;
    	data.clear();
        
    	s = determineList().stream().filter( q -> q.getName().toLowerCase().contains( questSearch.getText().toLowerCase() ) ).collect(Collectors.toCollection(ArrayList::new));
    	
    	for(Quest quest : s)
    		data.add(new Table(quest));
  
    }

    public void showStats() {
        skillGroup.setVisible(true);
        reqGroup.setVisible(false);

    }

    public void showReqs() {
        skillGroup.setVisible(false);
        reqGroup.setVisible(true);
    }

    public void refreshQuests() {
        List<Quest> s;
        data.clear();
        s = determineList();

        for (Quest quest : s) {
            data.add(new Table(quest));
        }

        questTable.refresh();
    }

    /**
     * Used to switch back to the main screen. Loads different fxml doc and set
     * it up to be a new scene. Then it shows the new scene. Two fxml docs
     * because when you try to reference stuff after creating a new one, upon
     * trying to switch back to any previous scenes, some of the item references
     * are null and it just doesn't know what to do.
     *
     * @throws IOException oh well.
     */
    public void button2Action() throws IOException {
        PlayerSerializer.savePlayer(QuestHelper.player);
        stage = (Stage) btn2.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument1.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void button3Action() throws IOException {
        PlayerSerializer.savePlayer(QuestHelper.player);
        stage = (Stage) btn3.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument3.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void openRHQQuestGuide() {
        questName = "";
        if (rowIndex != -1 ) {            
            questName = questTable.getColumns().get(0).getCellData(rowIndex).toString();
            Quest quest = QuestHelper.player.getQuestManager().findQuest(questName);
            QuestHelper.openURL.showDocument(quest.getURLToRuneHQQuestGuide());  
        }

    }
    
    public void selectQuest() {
        reqTA.clear();
        
        rowIndex = questTable.getSelectionModel().selectedIndexProperty().get();
        questName = "";
        if (rowIndex != -1) {
            
            questName = questTable.getColumns().get(0).getCellData(rowIndex).toString();
            Quest quest = QuestHelper.player.getQuestManager().findQuest(questName);
            
            reqTA.appendText(quest.toString());

        }
        usernameLabel.setText(QuestHelper.player.getName() + " ( " + String.valueOf(QuestHelper.player.getQuestPoints()) + " qp )");
        questTable.refresh();
    }
    
    public void markAsComplete() {
        int rowIndex = questTable.getSelectionModel().selectedIndexProperty().get();
        questName = "";
            questName = questTable.getColumns().get(0).getCellData(rowIndex).toString();
            Quest quest = QuestHelper.player.getQuestManager().findQuest(questName);

            QuestHelper.player.getQuestManager().setQuestComplete(quest, !quest.isComplete());

            recolor();
            
            refreshQuests();
    }

    public void recolor() {
        name.setCellFactory(name -> {
            return new TableCell<Table, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Table> currentRow = getTableRow();

                    if (!isEmpty()) {
                        currentRow.setStyle(QuestHelper.player.getQuestManager().findQuest(item).isComplete()
                                ? "-fx-background-color:lightgreen"
                                : "-fx-background-color:lightcoral");

                    }
                }
            };
        });
    }

    @FXML
    private void updateSkills() {
        QuestHelper.player.updateAllSkills();
        attack.setText(QuestHelper.player.getSkill(Type.Attack).toString());
        strength.setText(QuestHelper.player.getSkill(Type.Strength).toString());
        defence.setText(QuestHelper.player.getSkill(Type.Defence).toString());
        range.setText(QuestHelper.player.getSkill(Type.Ranged).toString());
        prayer.setText(QuestHelper.player.getSkill(Type.Prayer).toString());
        magic.setText(QuestHelper.player.getSkill(Type.Magic).toString());
        runecrafting.setText(QuestHelper.player.getSkill(Type.Runecraft).toString());
        construction.setText(QuestHelper.player.getSkill(Type.Construction).toString());
        hitpoints.setText(QuestHelper.player.getSkill(Type.Hitpoints).toString());
        agility.setText(QuestHelper.player.getSkill(Type.Agility).toString());
        herblore.setText(QuestHelper.player.getSkill(Type.Herblore).toString());
        thieving.setText(QuestHelper.player.getSkill(Type.Thieving).toString());
        crafting.setText(QuestHelper.player.getSkill(Type.Crafting).toString());
        fletching.setText(QuestHelper.player.getSkill(Type.Fletching).toString());
        slayer.setText(QuestHelper.player.getSkill(Type.Slayer).toString());
        hunter.setText(QuestHelper.player.getSkill(Type.Hunter).toString());
        mining.setText(QuestHelper.player.getSkill(Type.Mining).toString());
        smithing.setText(QuestHelper.player.getSkill(Type.Smithing).toString());
        fishing.setText(QuestHelper.player.getSkill(Type.Fishing).toString());
        cooking.setText(QuestHelper.player.getSkill(Type.Cooking).toString());
        firemaking.setText(QuestHelper.player.getSkill(Type.Firemaking).toString());
        woodcutting.setText(QuestHelper.player.getSkill(Type.Woodcutting).toString());
        farming.setText(QuestHelper.player.getSkill(Type.Farming).toString());
        total.setText(QuestHelper.player.getSkill(Type.Overall).toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questTable.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                System.out.println("rclick");
                markAsComplete();
            } else {
                System.out.println("lclick");
                selectQuest();
            }
        });
        updateSkills();
        setQuests();
        usernameLabel.setText(QuestHelper.player.getName() + " ( " + String.valueOf(QuestHelper.player.getQuestPoints()) + " qp )");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        qp.setCellValueFactory(new PropertyValueFactory<>("qp"));
        isComplete.setCellValueFactory(new PropertyValueFactory<>("isComplete"));

        name.setStyle("-fx-alignment: CENTER");
        length.setStyle("-fx-alignment: CENTER");
        qp.setStyle("-fx-alignment: CENTER");
        isComplete.setStyle("-fx-alignment: CENTER");

        questTable.setItems(data);

        recolor();

    }
    
    /**
     * If we add any other filters, we should just make a list of predicates to filter on and then add/remove based
     * on the checkboxes.
     * @return
     */
    private List<Quest> determineList()
    {
    	List<Quest> s;
    	
    	if( hideComplete.isSelected() && onlyDoable.isSelected() )
    		s = QuestHelper.player.getQuestManager().getQuestsCanBeCompletedAndHideComplete();
    	else if( hideComplete.isSelected() )
    		s = QuestHelper.player.getQuestManager().getQuestsNotCompleted();
    	else if( onlyDoable.isSelected() )
    		s = QuestHelper.player.getQuestManager().getQuestsCanBeCompleted();
    	else
    		s = QuestHelper.player.getQuestManager().getAllQuests();
    		
    	return s;
    }

}
