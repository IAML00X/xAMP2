/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.BE;

import java.io.Serializable;



/**
 *
 * @author IAMLUX
 */
public class Song implements Serializable{
    
 
  
    private String path;
    public String name;
    public String artist;
    public String album;
   
    


    public Song(String songName, String artistName, String albumName, int trackLength, String path) {
        this.name = songName;
        this.artist = artistName;
        this.album = albumName;
        this.path = path;
      
    }

    public void setName(String songName) {
        name = songName;
    }

    public String getName() {
        return name;
    }

    public void setArtist(String artistName) {
        artist = artistName;
    }

    public String getArtist() {
        return artist;
    }

    public void setAlbumName(String albumName) {
        album = albumName;
    }

    public String getAlbum() {
        return album;
    }


    public String toString() {
        return "Title: " + getName() + ", Artist: " + getArtist() + ", Album: " + getAlbum();
    }
    
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
