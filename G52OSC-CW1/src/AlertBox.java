import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	public static final int WIDTH = 200;
	public static final int HEIGHT = 100;

	private static String timeQuantum;
	
	
	//standard error handling
	public static void handle(){
		
		 //set window
		 Stage window = new Stage();
	     window.initModality(Modality.APPLICATION_MODAL);
	    
	     Label label = new Label();
	     label.setText("Error!");
	     
	     //go back
	     Button back = new Button("Back");
	     back.setOnAction(e -> {
	            window.close();
	     });
	     
	     VBox layout = new VBox(10);

	     //Add buttons
	     layout.getChildren().addAll(label, back);
	     layout.setAlignment(Pos.CENTER);
	     Scene scene = new Scene(layout, WIDTH, HEIGHT);
	     window.setScene(scene);
	     window.showAndWait();
	 }
	
	//setting time quantum (if RR algorithm is chosen)
	public static String timeQuantum() {
		
		//set window
		 Stage window = new Stage();
	     window.initModality(Modality.APPLICATION_MODAL);
	     
	     //Time quantum (if RR chosen)
	     Label tqLabel = new Label("Time Quantum: ");
	     TextField inputTq = new TextField();		
	     inputTq.setMaxWidth(60);
	     //go back
	     Button setTQ = new Button("set");
	     setTQ.setOnAction(e -> {
	    	 	timeQuantum = inputTq.getText();
	            window.close();
	     });
	     
	     VBox layout = new VBox(10);
	     
	     //Add buttons
	     layout.getChildren().addAll(tqLabel, inputTq, setTQ);
	     layout.setAlignment(Pos.CENTER);
	     Scene scene = new Scene(layout, WIDTH, HEIGHT);
	     window.setScene(scene);
	     window.showAndWait();
	     
	     return timeQuantum;
	}	
	
}
