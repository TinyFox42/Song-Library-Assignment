//Made by Eli Thorpe and Austin Suarez

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
			Alert check = new Alert(AlertType.CONFIRMATION, "Are You Sure You Want to Add This Song?");
			check.setHeaderText("Confirm Option");
			check.showAndWait();
			if(check.getResult().getText().equals("Cancel")) {
				return;
			}
			
			if(!(sl.getList().isEmpty()) && sl.hasSong(titleA.getText(), artistA.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION,"Song already added.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			} else if (titleA.getText().isEmpty() || artistA.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION, "Title and Artist required.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			} else {
				sl.add(title,artist,album,year);
				listview.setItems(sl.getList());
				listview.getSelectionModel().selectLast();
				titleA.clear();
				artistA.clear();
				albumA.clear();
				yearA.clear();
				f.updateFile(sl.songList);
				listview.refresh();
			}
		}
		if(button == deleteB) {
			Alert check = new Alert(AlertType.CONFIRMATION, "Are You Sure You Want to Delete This Song?");
			check.setHeaderText("Confirm Option");
			check.showAndWait();
			if(check.getResult().getText().equals("Cancel")) {
				return;
			}
			sl.delete(curSong);
			f.updateFile(sl.songList);
			listview.refresh();
		}
		if(button == editB) {
			Alert check = new Alert(AlertType.CONFIRMATION, "Are You Sure You Want to Edit This Song?");
			check.setHeaderText("Confirm Option");
			check.showAndWait();
			if(check.getResult().getText().equals("Cancel")) {
				return;
			}
			if(titleA.getText().isEmpty() && artistA.getText().isEmpty()
					&& albumA.getText().isEmpty() && yearA.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION,"At least one field required.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			} else if(!(sl.getList().isEmpty()) && sl.hasSong(titleA.getText(), artistA.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION,"Song already added.", ButtonType.OK);
				alert.setHeaderText("Error");
				alert.showAndWait();
			} else if(!(titleA.getText().isEmpty()) && !(artistA.getText().isEmpty())
					&& albumA.getText().isEmpty() && yearA.getText().isEmpty()) {
				sl.edit(curSong, title, artist, album, year);
				titleD.setText(title);
				artistD.setText(artist);
				albumD.setText(album);
				yearD.setText(year);
			} else if(!(titleA.getText().isEmpty())) {
				sl.edit(curSong, title, curSong.getArtist(), curSong.getAlbum(), curSong.getYear());
				titleD.setText(title);
			} else if(!(artistA.getText().isEmpty())) {
				sl.edit(curSong, curSong.getTitle(), artist, curSong.getAlbum(), curSong.getYear());
				artistD.setText(artist);
			} else if(!(albumA.getText().isEmpty())) {
				sl.edit(curSong, curSong.getTitle(), curSong.getArtist(), album, curSong.getYear());
				albumD.setText(album);
			} else if(!(yearA.getText().isEmpty())) {
				sl.edit(curSong, curSong.getTitle(), curSong.getArtist(), curSong.getAlbum(), year);
				yearD.setText(year);
			} else if(!(titleA.getText().isEmpty()) && !(artistA.getText().isEmpty())
					&& albumA.getText().isEmpty() && yearA.getText().isEmpty()) {
				sl.edit(curSong, title, artist, album, year);
				titleD.setText(title);
				artistD.setText(artist);
				albumD.setText(album);
				yearD.setText(year);
			}
			f.updateFile(sl.songList);
			listview.refresh();
		}
	}
}
