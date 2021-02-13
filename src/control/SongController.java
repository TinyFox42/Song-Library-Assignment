package control;

import java.io.IOException;
import app.LoadFile;
import app.Song;
import app.SongList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SongController {
	@FXML
	Button addB, deleteB, editB, saveB;
	@FXML
	Label titleD, artistD, albumD, yearD;
	@FXML
	TextField titleA, artistA, albumA, yearA;
	@FXML
	ListView<Song> listview = new ListView<>();
	
	SongList sl = new SongList();
	public ObservableList<Song> songs;
	private Song curSong;
	private Stage mainStage;
	LoadFile f = new LoadFile();
	
	public void start(SongList songList) {
		listview.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal) -> showDetails(mainStage));
		listview.getSelectionModel().select(0);
		songs = songList.songList;
		listview.setItems(songs);
		sl.songList = songs;
		listview.getSelectionModel().select(0);
	}
	
	public void showDetails(Stage mainStage) {
		curSong = listview.getSelectionModel().getSelectedItem();
		if(curSong != null) {
			titleD.setText(curSong.getTitle());
			artistD.setText(curSong.getArtist());
			albumD.setText(curSong.getAlbum());
			yearD.setText(curSong.getYear());
		}
	}
	
	public void action(ActionEvent e)
	throws IOException {
		curSong = listview.getSelectionModel().getSelectedItem();
		String title = titleA.getText();
		String artist = artistA.getText();
		String album = albumA.getText();
		String year = yearA.getText();
		Button button = (Button) e.getSource();
		
		if(button == addB) {
			if(titleA.getText().isEmpty() && artistA.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION, "Title and Artist required.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			}
			if(sl.getList().isEmpty()==false && sl.hasSong(titleA.getText(), artistA.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION,"Song already added.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			}
			sl.add(title,artist,album,year);
			listview.setItems(sl.getList());
			listview.getSelectionModel().select(0);
			listview.refresh();
			titleA.clear();
			artistA.clear();
			albumA.clear();
			yearA.clear();
		}
		if(button == deleteB) {
			sl.delete(curSong);
			listview.refresh();
		}
		if(button == editB) {
			if(titleA.getText().isEmpty() && artistA.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION,"Title and Artist required.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			}
			sl.edit(curSong,title,artist,album,year);
			titleD.setText(title);
			artistD.setText(artist);
			albumD.setText(artist);
			yearD.setText(year);
			listview.refresh();
		}
		if(button == saveB) {
			f.updateFile(sl.songList);
		}
	}
}
