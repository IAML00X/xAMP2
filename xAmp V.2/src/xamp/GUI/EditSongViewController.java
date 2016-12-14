/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xamp.BE.Song;
import xamp.GUI.model.SongModel;

/**
 * FXML Controller class
 *
 * @author MissJ
 */
public class EditSongViewController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField artistTxt;
    @FXML
    private TextField albumTxt;
    @FXML
    private Button closeButton;
    @FXML
    private Button saveSong;
    @FXML
    private ComboBox<String> chooseGenreBox;
    private TextField songPath;

    ObservableList<String> chooseGenreList = FXCollections.observableArrayList(
            "Soul", "Opera", "Classic", "Dubstep", "Techno", "Blues", "Country", "Reggae", "Metal", "Pop", "Hip Hop", "RnB", "Jazz", "Rock");

    SongModel songmodel;
    Song selectedSong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chooseGenreBox.setItems(chooseGenreList);
        chooseGenreBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveSong(ActionEvent event) {

        Song song = new Song(titleTxt.getText(), artistTxt.getText(), albumTxt.getText(), 0, selectedSong.getPath());
        songmodel.addSong(song);
        songmodel.getSongs().remove(selectedSong);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void initialize(ActionEvent event) {
    }

    void setModel(SongModel model) {
        songmodel = model;
    }

    void setInfoSong(Song selectedSong) {
        this.albumTxt.setText(selectedSong.getAlbum());
        this.artistTxt.setText(selectedSong.getArtist());
        this.titleTxt.setText(selectedSong.getName());
    }

    public void setSelectedSong(Song selectedSong) {
        this.selectedSong = selectedSong;
    }

}
