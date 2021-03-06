package it.polito.tdp.provaLaurea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.provaLaurea.model.CostVarianceData;
import it.polito.tdp.provaLaurea.model.Event;
import it.polito.tdp.provaLaurea.model.Event.EventType;
import it.polito.tdp.provaLaurea.model.Item;
import it.polito.tdp.provaLaurea.model.Model;
import it.polito.tdp.provaLaurea.model.NewOrder;
import it.polito.tdp.provaLaurea.model.NotFoundPriceException;
import it.polito.tdp.provaLaurea.model.PriceTrend;
import it.polito.tdp.provaLaurea.model.Probability;
import it.polito.tdp.provaLaurea.model.Simulator;
import it.polito.tdp.provaLaurea.model.SimulatorItem;
import it.polito.tdp.provaLaurea.model.SimulatorItem.State;
import it.polito.tdp.provaLaurea.model.SimulatorRow;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FXMLSimulatorController {

	private Model model;
	private Stage stage;
	private Simulator simulator;
	private ToggleGroup newItemGroup;
    private ToggleGroup bomGroup;
    private ToggleGroup probabilityGroup;
    private ToggleGroup trendGroup;
    /*private List<SimulatorItem> actual;
    private Map<String, Integer> bom;
    private Map<String, Probability> probabilities;
    private Map<String , List<PriceTrend>> trendsByItem;
    private LocalDate startDate;*/
	
    @FXML
    private TextField finalDateText;

    @FXML
    private TextField totalPriceText;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField annulmentText;

    @FXML
    private Button bomEnterButton;

    @FXML
    private RadioButton bomFileButton;

    @FXML
    private TextField bomIdText;

    @FXML
    private RadioButton bomManuallyButton;

    @FXML
    private TextField bomQuantityText;

    @FXML
    private TextField dayText;

    @FXML
    private TextField delayText;

    @FXML
    private TextField monthText;

    @FXML
    private TextField newDateText;

    @FXML
    private RadioButton newItemFileButton;

    @FXML
    private TextField newItemIdText;

    @FXML
    private RadioButton newItemManuallyButton;

    @FXML
    private TextField newItemPriceText;

    @FXML
    private TextField newItemQuantityText;

    @FXML
    private RadioButton probabilityFileButton;

    @FXML
    private TextField probabilityIdText;

    @FXML
    private RadioButton probabilityManuallyButton;

    @FXML
    private TextField trendDateText;

    @FXML
    private RadioButton trendFileButton;

    @FXML
    private TextField trendIdText;

    @FXML
    private RadioButton trendManuallyButton;

    @FXML
    private TextField trendPriceText;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField yearText;
    
    @FXML
    private TableColumn<SimulatorRow, LocalDate> columnArrivalDate;

    @FXML
    private TableColumn<SimulatorRow, LocalDate> columnDate;

    @FXML
    private TableColumn<SimulatorRow, EventType> columnEventType;

    @FXML
    private TableColumn<SimulatorRow, SimulatorItem> columnItem;

    @FXML
    private TableColumn<SimulatorRow, String> columnItemId;

    @FXML
    private TableColumn<SimulatorRow, State> columnItemState;

    @FXML
    private TableColumn<SimulatorRow, Integer> columnLotId;

    @FXML
    private TableColumn<SimulatorRow, Double> columnPrice;

    @FXML
    private TableColumn<SimulatorRow, Integer> columnQuantity;

    @FXML
    private TableView<SimulatorRow> simulatorTable;
    
    @FXML
    void handleBomEnterButton(ActionEvent event) {
    	if(bomGroup.getSelectedToggle().equals(bomFileButton)) {
    		//gestsci file
    		if(model.handleBomFile())
    			txtArea.setText("Successful bom file insertion!");
    	}else {
    		String itemId = bomIdText.getText();
    		int quantity = Integer.parseInt(bomQuantityText.getText());
    		model.enterInBom(itemId, quantity);
    		bomIdText.clear();
        	bomQuantityText.clear();
        	txtArea.setText("Successful insertion in bom!");
    	}
    }
    
    @FXML
    void handleBomFileButton(ActionEvent event) {
    	bomIdText.clear();
    	bomQuantityText.clear();
    	bomIdText.setDisable(true);
    	bomQuantityText.setDisable(true);
    }

    @FXML
    void handleBomManuallyButton(ActionEvent event) {
    	bomIdText.setDisable(false);
    	bomQuantityText.setDisable(false);
    }

    @FXML
    void handleNewItemEnter(ActionEvent event) {
    	if(newItemGroup.getSelectedToggle().equals(newItemFileButton)) {
			if(model.handleNewItemFile())
				txtArea.setText("Successful newItem file insertion!");
    	}else {
    		String itemId = newItemIdText.getText();
    		int quantity = Integer.parseInt(newItemQuantityText.getText());
    		double price = Double.parseDouble(newItemPriceText.getText());
    		LocalDate date;
    		try {
    			date = LocalDate.parse(newDateText.getText());
    		}catch (Exception e) {
    			txtArea.setText("Enter the date in the format yyyy-mm-dd");
    			return;
    		}
    		model.addInActual(date, price,itemId,quantity);
    		newItemIdText.clear();
        	newItemPriceText.clear();
        	newItemQuantityText.clear();
        	newDateText.clear();
        	txtArea.setText("Successful insertion in actual warehouse!");
    	}
    }

    @FXML
    void handleNewItemFileButton(ActionEvent event) {
    	newItemIdText.clear();
    	newItemPriceText.clear();
    	newItemQuantityText.clear();
    	newDateText.clear();
    	newItemIdText.setDisable(true);
    	newItemPriceText.setDisable(true);
    	newItemQuantityText.setDisable(true);
    	newDateText.setDisable(true);
    }

    @FXML
    void handleNewItemManuallyButton(ActionEvent event) {
    	newItemIdText.setDisable(false);
    	newItemPriceText.setDisable(false);
    	newItemQuantityText.setDisable(false);
    	newDateText.setDisable(false);
    }

    @FXML
    void handleProbabilityEnterButton(ActionEvent event) {
    	if(probabilityGroup.getSelectedToggle().equals(probabilityFileButton)) {
    		if(model.handleProbabilityFile())
    			txtArea.setText("Successful probability file insertion!");
    	}else {
    		String itemId = probabilityIdText.getText();
    		double delay = Double.parseDouble(delayText.getText());
    		double annulment = Double.parseDouble(annulmentText.getText());
    		model.addInProbabilities(itemId, delay, annulment);
    		
    		probabilityIdText.clear();
        	delayText.clear();
        	annulmentText.clear();
        	txtArea.setText("Successful probabilities insertion!");
    	}
    }

    @FXML
    void handleProbabilityFileButton(ActionEvent event) {
    	probabilityIdText.clear();
    	delayText.clear();
    	annulmentText.clear();
    	probabilityIdText.setDisable(true);
    	delayText.setDisable(true);
    	annulmentText.setDisable(true);
    }

    @FXML
    void handleProbabilityManuallyButton(ActionEvent event) {
    	probabilityIdText.setDisable(false);
    	delayText.setDisable(false);
    	annulmentText.setDisable(false);
    }

    @FXML
    void handleStartDateButton(ActionEvent event) {
		int year = Integer.parseInt(yearText.getText());
		int month = Integer.parseInt(monthText.getText());
		int day = Integer.parseInt(dayText.getText());
		model.enterStartDate(year, month, day);
		txtArea.setText("Successful start date insertion!");
		yearText.clear();
		dayText.clear();
		monthText.clear();
    }

    @FXML
    void handleTrendEnterButton(ActionEvent event) {
    	if(trendGroup.getSelectedToggle().equals(trendFileButton)) {
    		if(model.handleTrendFile())
    			txtArea.setText("Successful trend file insertion!");
    	}else {
    		String itemId = trendIdText.getText();
    		LocalDate date;
    		try {
				date = LocalDate.parse(trendDateText.getText());
			} catch (Exception e) {
				txtArea.setText("Enter the data in the format yyyy-mm-dd");
				return;
			}
    		double value = Double.parseDouble(trendPriceText.getText());
    		model.addTrend(date, value,itemId);
    		trendIdText.clear();
        	trendDateText.clear();
        	trendPriceText.clear();
        	txtArea.setText("Successful trend insertion!");
    	}
    }

    @FXML
    void handleTrendFileButton(ActionEvent event) {
    	trendIdText.clear();
    	trendDateText.clear();
    	trendPriceText.clear();
    	trendIdText.setDisable(true);
    	trendDateText.setDisable(true);
    	trendPriceText.setDisable(true);
    }

    @FXML
    void handleTrendManuallyButton(ActionEvent event) {
    	trendIdText.setDisable(false);
    	trendDateText.setDisable(false);
    	trendPriceText.setDisable(false);
    }
    
    @FXML
    void handleSimulation(ActionEvent event) {
    	
    	
    	if(model.getStartDate() == null) {
    		txtArea.setText("Enter the start date");
    		return;
    	}
    	try {
    		model.simulate();
//    		txtArea.setText(model.getSimulatorEventString());
    		simulatorTable.setItems(FXCollections.observableArrayList(model.getSimulatorRowList()));
//    		return simulator.getEventString();
//    		this.TotalPriceText.setText(simulator.getTotalPrice()+"");
    		this.totalPriceText.setText(model.getSimulatorTotalPrice());
    		this.finalDateText.setText(model.getSimulatorSaleDate());
		} catch (NotFoundPriceException e) {
			// TODO: handle exception
			txtArea.setText("No purchase price found for : " + e.getNotFound());
		}
    	
    	/*simulator.setActualWarehouse(this.actual);
		simulator.setBom(this.bom);
		simulator.setStartTime(this.startDate);
		simulator.setEndTime(this.startDate.plusYears(1));*/
//		Map<String, CostVarianceData> cvdByItemMap = new HashMap<String, CostVarianceData>();
//		model.init(LocalDate.of(2022, 1, 1));  
		/*for(Item toExtract:  model.getCurrentCVDByItemMap().keySet()) {
			cvdByItemMap.put(toExtract.getItemId(), model.getCurrentCVDByItemMap().get(toExtract));
		}*/
		
		
    }
    
    @FXML
    void handleReset(ActionEvent event) {
    	model.reset();
    	/*this.simulator = new Simulator();
		actual = new ArrayList<SimulatorItem>();
		bom = new HashMap<String, Integer>();
		probabilities = new HashMap<String, Probability>();
		trendsByItem = new HashMap<String, List<PriceTrend>>();
		startDate = null;*/
		this.txtArea.clear();
		bomIdText.clear();
    	bomQuantityText.clear();
    	newItemIdText.clear();
    	newItemPriceText.clear();
    	newItemQuantityText.clear();
    	newDateText.clear();
    	probabilityIdText.clear();
    	delayText.clear();
    	annulmentText.clear();
    	trendIdText.clear();
    	trendDateText.clear();
    	trendPriceText.clear();
    	finalDateText.clear();
    	totalPriceText.clear();
    	simulatorTable.getItems().clear();
    }
    
    @FXML
    void goMenu(ActionEvent event) {
    	try {
	    	System.out.println("Apri Menu Scene");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuScene.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
	        scene.getStylesheets().add("/styles/Styles.css");
	        scene.getRoot().setStyle("-fx-font-family: 'serif'");
			
			FXMLMenuController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			stage.show();
	    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
    }

    
    public void setModel(Model model, Stage stage) {
		this.model = model;
		this.stage = stage;
		/*this.simulator = new Simulator();
		actual = new ArrayList<SimulatorItem>();
		bom = new HashMap<String, Integer>();
		probabilities = new HashMap<String, Probability>();
		trendsByItem = new HashMap<String, List<PriceTrend>>();*/
    }	

    @FXML
    void initialize() {
    	assert annulmentText != null : "fx:id=\"annulmentText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert bomEnterButton != null : "fx:id=\"bomEnterButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert bomFileButton != null : "fx:id=\"bomFileButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert bomIdText != null : "fx:id=\"bomIdText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert bomManuallyButton != null : "fx:id=\"bomManuallyButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert bomQuantityText != null : "fx:id=\"bomQuantityText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnArrivalDate != null : "fx:id=\"columnArrivalDate\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnDate != null : "fx:id=\"columnDate\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnEventType != null : "fx:id=\"columnEventType\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnItem != null : "fx:id=\"columnItem\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnItemId != null : "fx:id=\"columnItemId\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnItemState != null : "fx:id=\"columnItemState\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnLotId != null : "fx:id=\"columnLotId\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnPrice != null : "fx:id=\"columnPrice\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert columnQuantity != null : "fx:id=\"columnQuantity\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert dayText != null : "fx:id=\"dayText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert delayText != null : "fx:id=\"delayText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert finalDateText != null : "fx:id=\"finalDateText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert monthText != null : "fx:id=\"monthText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newDateText != null : "fx:id=\"newDateText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newItemFileButton != null : "fx:id=\"newItemFileButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newItemIdText != null : "fx:id=\"newItemIdText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newItemManuallyButton != null : "fx:id=\"newItemManuallyButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newItemPriceText != null : "fx:id=\"newItemPriceText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert newItemQuantityText != null : "fx:id=\"newItemQuantityText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert probabilityFileButton != null : "fx:id=\"probabilityFileButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert probabilityIdText != null : "fx:id=\"probabilityIdText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert probabilityManuallyButton != null : "fx:id=\"probabilityManuallyButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert simulatorTable != null : "fx:id=\"simulatorTable\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert totalPriceText != null : "fx:id=\"totalPriceText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert trendDateText != null : "fx:id=\"trendDateText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert trendFileButton != null : "fx:id=\"trendFileButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert trendIdText != null : "fx:id=\"trendIdText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert trendManuallyButton != null : "fx:id=\"trendManuallyButton\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert trendPriceText != null : "fx:id=\"trendPriceText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SimulatorScene.fxml'.";
        assert yearText != null : "fx:id=\"yearText\" was not injected: check your FXML file 'SimulatorScene.fxml'.";

        this.newItemGroup = new ToggleGroup();
        this.bomGroup = new ToggleGroup();
        this.probabilityGroup = new ToggleGroup();
        this.trendGroup = new ToggleGroup();
        
        newItemFileButton.setToggleGroup(newItemGroup);
        newItemFileButton.setSelected(true);
        newItemManuallyButton.setToggleGroup(newItemGroup);
        bomFileButton.setToggleGroup(bomGroup);
        bomFileButton.setSelected(true);
        bomManuallyButton.setToggleGroup(bomGroup);
        probabilityFileButton.setToggleGroup(probabilityGroup);
        probabilityFileButton.setSelected(true);
        probabilityManuallyButton.setToggleGroup(probabilityGroup);
        trendFileButton.setToggleGroup(trendGroup);
        trendFileButton.setSelected(true);
        trendManuallyButton.setToggleGroup(trendGroup);
        
        newItemIdText.setDisable(true);
    	newItemPriceText.setDisable(true);
    	newItemQuantityText.setDisable(true);
    	newDateText.setDisable(true);
        bomIdText.setDisable(true);
    	bomQuantityText.setDisable(true);
    	probabilityIdText.setDisable(true);
    	delayText.setDisable(true);
    	annulmentText.setDisable(true);
    	trendIdText.setDisable(true);
    	trendDateText.setDisable(true);
    	trendPriceText.setDisable(true);
    	
    	//notRepetitiveColumn.setCellValueFactory(new PropertyValueFactory<CostVarianceRow, Double>("notRepetitiveToOutput"));
        columnArrivalDate.setCellValueFactory(new PropertyValueFactory<SimulatorRow, LocalDate>("arrivalDate"));
        columnItemId.setCellValueFactory(new PropertyValueFactory<SimulatorRow, String>("itemId"));
        columnItemState.setCellValueFactory(new PropertyValueFactory<SimulatorRow, State>("state"));
        columnLotId.setCellValueFactory(new PropertyValueFactory<SimulatorRow, Integer>("id"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<SimulatorRow, Double>("purchasePrice"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<SimulatorRow, Integer>("quantity"));
        columnDate.setCellValueFactory(new PropertyValueFactory<SimulatorRow, LocalDate>("time"));
        columnEventType.setCellValueFactory(new PropertyValueFactory<SimulatorRow, EventType>("type"));
        columnItem.setCellValueFactory(new PropertyValueFactory<SimulatorRow, SimulatorItem>("item"));
    }

}
