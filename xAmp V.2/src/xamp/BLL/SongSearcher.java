/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xamp.BLL;

import java.util.ArrayList;
import java.util.List;
import xamp.BE.Song;

/**
 * 
 * @author MissJ
 */
public class SongSearcher {

    /*
    * A filter that searches for songs using a new list.  
    */
    public ArrayList<Song> search(List<Song> songs, String searchQuery) {
        ArrayList<Song> result = new ArrayList<>();

        for (Song song : songs) {
            String title = song.getName().trim().toLowerCase();
            String artist = song.getArtist().trim().toLowerCase();
            String genre = song.getAlbum().trim().toLowerCase();

            if (title.contains(searchQuery.toLowerCase().trim())
                    || artist.contains(searchQuery.toLowerCase().trim())
                    || genre.contains(searchQuery.toLowerCase().trim())
                    && !result.contains(song)) {
                result.add(song);
            }
        }

        return result;
    }


}
