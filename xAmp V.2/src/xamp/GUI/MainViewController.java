/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import xamp.BE.Song;
import xamp.BLL.PlaylistManager;
import xamp.BLL.SongManager;
import xamp.BLL.SongSearcher;
import xamp.GUI.model.SongModel;

/**
 * FXML Controller class
 *
 * @author MissJ
 */
public class MainViewController implements Initializable {

    private SongManager sm = new SongManager();

    private Song selectedSong;

    private final ObservableList<Song> searchedSongs;

    private final SongSearcher songSearcher;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Button deleteSongButton;

    private MediaPlayer mp;

    private SongModel songmodel;

    ObservableList<Song> songs = FXCollections.observableArrayList();

    @FXML
    private Button addPlaylist;
    @FXML
    private Button editPlaylist;
    @FXML
    private Button deletePlaylist;
    @FXML
    private Button addNewSong;
    @FXML
    private Button editSong;
    @FXML
    private Button closePrg;
    @FXML
    private TextField searchFilter;
    @FXML
    private Label fastBcwButton;
    @FXML
    private Label searchButton;
    @FXML
    private Label fastFrwButton;
    @FXML
    public Label playButton;
    @FXML
    private ImageView searchBtn;
    @FXML
    private TableView<Song> songTbl;
    @FXML
    private Label pauseButton;
    @FXML
    private TableColumn<Song, String> colSong;
    @FXML
    private TableColumn<Song, String> colArtist;
    @FXML
    private TableColumn<Song, String> colAlbum;
    PlaylistManager playlistManager = new PlaylistManager();

    public MainViewController() {
        this.searchedSongs = FXCollections.observableArrayList();
        this.songSearcher = new SongSearcher();
    }

    /*
    * sets the colums under songs. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        songmodel = new SongModel();
        colSong.setCellValueFactory(
                new PropertyValueFactory("name"));
        colArtist.setCellValueFactory(
                new PropertyValueFactory("artist"));
        colAlbum.setCellValueFactory(
                new PropertyValueFactory("album"));
        setSongs();

        sm = new SongManager();
        searchSong();
        List<Song> allSongs = playlistManager.getAll();
        songmodel.setSongs(allSongs);
        songTbl.setItems(songmodel.getSongs());

    }

    /*
    * 
     */
    @FXML
    public void handleEnterPressed(KeyEvent event) {
//        if (event.getCode() == KeyCode.ENTER) {
//            System.out.println("Heya!");
//        }
    }

    private void searchSong() {
        searchFilter.textProperty().addListener((ObservableValue<? extends String> listener, String oldQuery, String newQuery)
                -> {
            searchedSongs.setAll(songSearcher.search(songs, newQuery));
            songTbl.setItems(searchedSongs);
        });
    }

    @FXML
    public void srcBtnClicked(MouseEvent event) {
        System.out.println("OHAI!");
    }

    /*
    * Handle song view
     */
    @FXML
    public void handleOnButtonActionSong(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        AddSongViewController controller = fxmlLoader.getController();
        controller.setModel(songmodel);
        controller.setManager(playlistManager);
        Stage stage = new Stage();
        stage.setTitle("Add Song Window");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    /*
    * Handle playlist view
     */
    @FXML
    public void handleOnButtonActionPlaylist(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPlaylistView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Playlist Window");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    /*
    * plays a song when you press the playButton.
     */
    @FXML
    private void playSongButton() {
        sm.playSong();
    }

    /*
    * pause a song when you press the pauseButton. 
     */
    @FXML
    private void pauseSongButton() {
        sm.pauseSong();
    }

    /*
    * handles the volume slider and sets the value for it. 
     */
    @FXML
    private void handleVolumeSlider(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            sm.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
        }
    }

    @FXML
    private void initialize(MouseEvent event) {
    }

    /*
    * closes the application. 
     */
    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    /*
    * plays a song when mouse is clicked twice.
     */
    @FXML
    public void handleOnMousePressed(MouseEvent event) {
        selectedSong = songTbl.getSelectionModel().getSelectedItem();
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if (selectedSong != null) {
                sm.pauseSong();
                sm.playSong(selectedSong, true);
            }
            sm.playSong();

        }
    }

    private void setSongs() {
        songs = (ObservableList<Song>) songmodel.getSongs();
    }

    @FXML
    private void nextSong(MouseEvent event) {
        sm.playNextSong(songs);

    }

    @FXML
    private void deleteSong(ActionEvent event) {
        songmodel.getSongs().remove(selectedSong);
        songTbl.getItems().remove(selectedSong);
    }

    @FXML
    private void handleOnButtonActionSongEdit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditSongView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        EditSongViewController controller = fxmlLoader.getController();
        controller.setModel(songmodel);
        controller.setInfoSong(selectedSong);
        controller.setSelectedSong(selectedSong);
        Stage stage = new Stage();
        stage.setTitle("Add Song Window");
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
