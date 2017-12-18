import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 600;
	
	public static void main(String[] args) {		
		launch();
	}

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("CPU Algorithms");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(16.66666);
		
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(16.66666);
	   
	    ColumnConstraints column3 = new ColumnConstraints();
	    column3.setPercentWidth(16.66666);
		
	    ColumnConstraints column4 = new ColumnConstraints();
	    column4.setPercentWidth(16.66666);
	  
	    ColumnConstraints column5 = new ColumnConstraints();
	    column5.setPercentWidth(16.66666);
		
	    ColumnConstraints column6 = new ColumnConstraints();
	    column6.setPercentWidth(16.66666);

	    pane.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6); 

	    int l = 10; 
	    Label[] ATs = new Label[l];
	    TextField[] inputATs = new TextField[l];
	    Label[] BTs = new Label[l];
	    TextField[] inputBTs = new TextField[l];
	    Label[] Ps = new Label[l];
	    TextField[] inputPs = new TextField[l];
	    
	    Label ProcessInfo = new Label("1. Insert info of all processes");
		pane.setConstraints(ProcessInfo, 0, 0, 2, 1);
	    pane.getChildren().add(ProcessInfo);
		
	    for(int i=0; i<l; i++) {
	    	
	    	//Arrival Time
	    	ATs[i] = new Label("Arrival Time: ");
				pane.setConstraints(ATs[i], 0, i+1);
	    	inputATs[i] = new TextField();
				pane.setConstraints(inputATs[i], 1, i+1);
			
			//Burst Time
			BTs[i] = new Label("Burst Time: ");
				pane.setConstraints(BTs[i], 2, i+1);
	    	inputBTs[i] = new TextField();
				pane.setConstraints(inputBTs[i], 3, i+1);
				
			//Priority Time	
		    Ps[i] = new Label("Priority: ");
				pane.setConstraints(Ps[i], 4, i+1);
		    inputPs[i] = new TextField();
				pane.setConstraints(inputPs[i], 5, i+1);			
				
			pane.getChildren().addAll(ATs[i], inputATs[i], BTs[i], inputBTs[i], Ps[i], inputPs[i]);
	    }
 
	    Button FCFS = new Button("FCFS");
	    FCFS.setOnAction(e->{
			try {
				new FCFS();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		pane.setConstraints(FCFS, 0, 13);
	    Button PrioSched = new Button("PrioSched");
	    PrioSched.setOnAction(e->{
			try {
				new PrioSched();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		pane.setConstraints(PrioSched, 0, 14);
	    Button RR = new Button("RR");
	    RR.setOnAction(e->{
			try {
				new RR();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		pane.setConstraints(RR, 0, 15);
	    Button SJF = new Button("SJF");
	    SJF.setOnAction(e->{
			try {
				new SJF();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		pane.setConstraints(SJF, 0, 16);
		pane.getChildren().addAll(FCFS, PrioSched, RR, SJF);
	    
	    
    	//Average waiting Time
    	Label avewtLabel = new Label("Average waiting Time: ");
			pane.setConstraints(avewtLabel, 1, 13, 2, 1);
			TextField avewt = new TextField();
			pane.setConstraints(avewt, 3, 13, 1, 1);
	    //Average waiting Time
	    Label avetatLabel = new Label("Average turn around time: ");
			pane.setConstraints(avetatLabel, 1, 14, 2, 1);
			TextField avetat = new TextField();
			pane.setConstraints(avetat, 3, 14, 1, 1);	
		pane.getChildren().addAll(avewtLabel, avewt, avetatLabel, avetat);
			
			
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		window.setScene(scene);
		window.show();
	}
	
}
