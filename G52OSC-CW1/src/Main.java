import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static final int WIDTH = 700;
	public static final int HEIGHT = 400;
	
	public static void main(String[] args) {		
		launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		Stage window = new Stage();
		GridPane pane = new GridPane();
		HBox hbox = new HBox(10);
		
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(15, 15, 15, 15));
      		
		Text sceneTitle = new Text("All Processes");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		
		
		for(int i=0; i<10; i++) {
			Label AT1 = new Label("Arrival Time 1:");
			pane.add(AT1, 0, i++);
			TextField inputAT1 = new TextField();
			pane.add(inputAT1, 1, i++);
			
			Label BT1 = new Label("Burst Time 1:");
			pane.add(BT1, 2, i++);
			TextField inputBT1 = new TextField();
			pane.add(inputBT1, 3, i++);
			
			Label P1 = new Label("Priority 1:");
			pane.add(P1, 4, i++);
			TextField inputP1 = new TextField();
			pane.add(inputP1, 5, i++);
		}
		

		Button calculateButton = new Button("Calculate");        
      
        
        
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(calculateButton);
		pane.add(hbox, 1, 4);
		final Text taxMessage = new Text();
		pane.add(taxMessage, 1, 6);
				
		window.setTitle("CPU Algorithms");
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setMaximized(false);
        window.setResizable(false);
        window.show();
      
	}
	
}
