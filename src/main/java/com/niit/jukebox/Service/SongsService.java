package com.niit.jukebox.Service;

import com.niit.jukebox.methods.SongsConnection;
import com.niit.jukebox.methods.SongsMethods;
import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

public class SongsService {

    private static boolean isSongAvailable(Songs song) throws Exception {
        String name=song.getSongName();
        boolean flag = false;
        PreparedStatement selectStatement = SongsConnection.getSongsConnection().prepareStatement("Select songname from songs where songname=?");
        selectStatement.setString(1, name);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            flag = true;
        }
        return flag;
    }

    public static int addSong(Songs song) throws Exception {
        int res = 0;
        if (isSongAvailable(song)) {
            System.out.println("Song already available");
        }
        else {
            res=SongsMethods.insertSong(song);
            System.out.println("Song Successfully added");
        }
        return res;
    }

    public  static void selectAll()throws Exception{
        List<Songs> allSongs=SongsMethods.displayCatlog();
        Iterator<Songs> ite= allSongs.iterator();
        while (ite.hasNext()){
            System.out.println(ite.next());
        }
    }
}
