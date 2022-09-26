package com.niit.jukebox.Service;

import com.niit.jukebox.methods.SongsConnection;
import com.niit.jukebox.methods.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

public class SongsService {

    // to check whether a song is already present in catlog or not
    private static boolean isSongAvailable(Songs song) throws Exception {
        List<Songs> allSongs=SongsDAO.displayCatlog();
        String name=song.getSongName();
        boolean flag = false;
        for (Songs currentSong:allSongs){
            if(currentSong.getSongName().equals(name)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    // to add a song in catlog
    public static boolean addSong(Songs song) throws Exception {
        boolean res = false;
        if (!isSongAvailable(song)) {
            SongsDAO.insertSong(song);
            res=true;
        }
        return res;
    }

    public  static void selectAll()throws Exception{
        List<Songs> allSongs= SongsDAO.displayCatlog();
        if(allSongs!=null){
            System.out.println("---------------------------------------------------------------------------------------------------");
            for(Songs s:allSongs){
                System.out.format("|  %-25s%-20s%-20s%-20s%f  |\n",s.getSongName(),s.getArtist(),s.getGenre(),s.getAlbum(),s.getDuration());
                System.out.println("---------------------------------------------------------------------------------------------------");
        }
        }
    }
}
