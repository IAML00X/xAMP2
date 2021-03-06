/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.BLL;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import xamp.BE.Song;

/**
 *
 * @author IAMLUX
 */
public class SongManager 
{

    private Song songPlaying;
    private MediaPlayer mp;

    
    /*
    * Plays the music.
    */
    public void playSong(Song song, boolean overwrite) 
    {
        if(songPlaying == null || overwrite)
        {
            songPlaying = song; 
            File soundFile = new File(songPlaying.getPath());
            Media media = new Media(soundFile.toURI().toString());
            mp = new MediaPlayer(media);
            
        }

        mp.play();
    }

    /*
    * Gets the medeiaplayer
    */
    public MediaPlayer getMediaPlayer() 
    {
        return mp;
    }
    
    /*
    * Calls the mediaplayer and pause the music.
    */
    public void pauseSong() 
    {
        if (songPlaying != null)
        {
        mp.pause();
        }
        
    }
    
    /*
    * Calls the mediaplayer and plays the music.
    */
    public void playSong()
    {
        mp.play();
    }
    
    /*
    * Plays the next song after the one song playing if finished. 
    */
    public void playNextSong(ObservableList<Song> songs) {
        Song nextSong = null;
        for (int i = 0; i < songs.size() - 1; i++) {
            if (songPlaying.getName().equals(songs.get(i).getName()) && i != songs.size()) {
                nextSong = songs.get(i + 1);

            }

            if (nextSong == null) {
                nextSong = songs.get(0);

            }
        }
        pauseSong();
        playSong(nextSong, true);

    }
}