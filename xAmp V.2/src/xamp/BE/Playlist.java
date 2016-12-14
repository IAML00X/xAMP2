/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.BE;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IAMLUX
 */
public class Playlist {

    private List<Song> songList;
    private String playlistName;
    private int length;

    public Playlist() {
        songList = new ArrayList<Song>();
    }

    public void setPlaylistName() {
        this.playlistName = playlistName;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void setLength(int h, int m, int s) {
        length = (h * 3600 + m * 60 + s);
        if (h == 0) {
            length = (m * 60 + s);
        }
    }

    public int getLength() {
        return length;
    }

    public String getPlaylistName() {
        return playlistName;
    }

}
