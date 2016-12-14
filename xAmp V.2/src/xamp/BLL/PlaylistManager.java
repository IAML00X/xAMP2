/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.BLL;

import java.util.List;
import xamp.BE.Song;
import xamp.DAL.SongDAO;

/**
 *
 * @author IAMLUX
 */
public class PlaylistManager {

    SongDAO songData = new SongDAO();

    public List<Song> getAll() {
        return songData.read("test.ser");
    }

    public void writeSongs(List<Song> songs) {
        songData.writeObjectData(songs, "test.ser");
    }
}
