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
	public static final int N = 10;
	float avewt;
	float avetat;
	Request newRequest;
	
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
		
		//set pane column width
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

	    Label[] ATs = new Label[N];
	    TextField[] inputATs = new TextField[N];
	    Label[] BTs = new Label[N];
	    TextField[] inputBTs = new TextField[N];
	    Label[] Ps = new Label[N];
	    TextField[] inputPs = new TextField[N];
	    
	    //display user input section
	    for(int i=0; i<N; i++) {
	    	
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
	    
	    
	    //display calculated average waiting Time
    		Label avewtLabel = new Label("Average waiting Time: ");
			pane.setConstraints(avewtLabel, 1, 13, 2, 1);
			TextField avewtDisplay = new TextField();
			avewtDisplay.setEditable(false);
			pane.setConstraints(avewtDisplay, 3, 13, 1, 1);
	    
		//display calculated turn around Time
			Label avetatLabel = new Label("Average turn around time: ");
			pane.setConstraints(avetatLabel, 1, 14, 2, 1);
			TextField avetatDisplay = new TextField();
			avetatDisplay.setEditable(false);
			pane.setConstraints(avetatDisplay, 3, 14, 1, 1);	
		
		pane.getChildren().addAll(avewtLabel, avewtDisplay, avetatLabel, avetatDisplay);
		
		
		//FCFS choosed
			Button FCFS = new Button("FCFS");
			FCFS.setOnAction(e->{
				try {
					newRequest = new Request(1, inputATs, inputBTs);
					avewt = newRequest.getAvewt();
					avetat = newRequest.getAvetat();
					avewtDisplay.setText(Float.toString(avewt));
					avetatDisplay.setText(Float.toString(avetat));
				}catch(IndexOutOfBoundsException userInputError) {
					AlertBox.handle();
				}
			});
			pane.setConstraints(FCFS, 0, 13);
		
		//SJF choosed
		    Button SJF = new Button("SJF");
		    SJF.setOnAction(e->{
		    	try {
		    		newRequest = new Request(2, inputATs, inputBTs);
					avewt = newRequest.getAvewt();
					avetat = newRequest.getAvetat();
					avewtDisplay.setText(Float.toString(avewt));
					avetatDisplay.setText(Float.toString(avetat));
				}catch(Exception userInputError) {
					userInputError.printStackTrace();
					AlertBox.handle();
				}
				});
			pane.setConstraints(SJF, 0, 14);	
		
		//PrioSched choosed
		    Button PrioSched = new Button("PrioSched");
		    PrioSched.setOnAction(e->{
		    	try {
		    		newRequest = new Request(inputATs, inputBTs, inputPs);
					avewt = newRequest.getAvewt();
					avetat = newRequest.getAvetat();
					avewtDisplay.setText(Float.toString(avewt));
					avetatDisplay.setText(Float.toString(avetat));
				}catch(Exception userInputError) {
					AlertBox.handle();
				}
			});
		    pane.setConstraints(PrioSched, 0, 15);
		
		//RR choosed
		    Button RR = new Button("RR");
		    RR.setOnAction(e->{
	    		String tq = AlertBox.timeQuantum();
	    		if(tq!=null) {
	    			try {
			    		newRequest = new Request(inputATs, inputBTs, tq);
						avewt = newRequest.getAvewt();
						avetat = newRequest.getAvetat();
						avewtDisplay.setText(Float.toString(avewt));
						avetatDisplay.setText(Float.toString(avetat));
					}catch(Exception userInputError) {
						AlertBox.handle();
					}
	    		}
		    });
			pane.setConstraints(RR, 0, 16);

		pane.getChildren().addAll(FCFS, PrioSched, RR, SJF);
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		window.setScene(scene);
        window.setMaximized(false);
        window.setResizable(false);
		window.show();
	}
	
}
