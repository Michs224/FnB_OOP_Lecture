package fnb_oop;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class animationController{

	@FXML
	private ImageView tree;
	
	public void slide() {
		// TODO Auto-generated method stub
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(tree);
		translate.setDuration(Duration.millis(1000));
		translate.setByX(-250);
		translate.play();

		translate.setOnFinished(event->{
			tree.setVisible(false);
		});
		
	}
	
}
