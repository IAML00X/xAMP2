/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.GUI;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import xamp.BE.Song;
import xamp.BLL.PlaylistManager;
import xamp.GUI.model.SongModel;

/**
 * FXML Controller class
 *
 * @author MissJ
 */
public class AddSongViewController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private TextField songTitle;
    @FXML
    private TextField songArtist;
    @FXML
    private Button saveSong;
    @FXML
    private TextField songAlbum;

    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private TextField songPath;
    @FXML
    private ComboBox<String> chooseGenreBox;

    private SongModel songmodel;
    private PlaylistManager manager;

    ObservableList<String> chooseGenreList = FXCollections.observableArrayList(
            "Soul", "Opera", "Classic", "Dubstep", "Techno", "Blues", "Country", "Reggae", "Metal", "Pop", "Hip Hop", "RnB", "Jazz", "Rock");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chooseGenreBox.setItems(chooseGenreList);
        chooseGenreBox.getSelectionModel().selectFirst();
    }

    public void addSong() {
        String path = songPath.getText();
    }

    /*
    * This opens explorer and gets the choosen file's string in the txt field. 
     */
    @FXML
    private void chooseFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            songPath.setText(file.getPath());
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /*
    * Save the song that's choosen.
     */
    @FXML
    public void saveSong(ActionEvent event) {
        Song song = new Song(songTitle.getText(), songArtist.getText(), songAlbum.getText(), 0, songPath.getText());
        songmodel.addSong(song);
        manager.writeSongs(songmodel.getSongs());
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

    /*
     * Closes the window when you press the close button. 
     */
    @FXML
    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize(ActionEvent event) {
    }

    public void setModel(SongModel model) {
        songmodel = model;
    }

    void setManager(PlaylistManager playlistManager) {
        manager = playlistManager;
    }

}
