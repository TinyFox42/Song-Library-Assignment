package app;

import java.io.File;
import control.SongController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application {

	@Override
	public void start(Stage ps) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/control/songLibLayout.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		SongController control = loader.getController();
		
		File f = new File("songs.csv");
		if(f.exists()==false) {
			f.createNewFile();
		}
		SongList sl = new SongList();
		sl.songList = LoadFile.readFile(f.getName());
		control.start(sl);
		
		Scene scene = new Scene(root);
		ps.setScene(scene);
		ps.setTitle("Song Library");
		ps.setResizable(false);
		ps.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
