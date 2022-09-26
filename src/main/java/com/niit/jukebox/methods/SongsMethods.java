package com.niit.jukebox.methods;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongsMethods {

    public static boolean isSongAvailable(String name) throws Exception {
        boolean flag = false;
        PreparedStatement selectStatement = SongsConnection.getSongsConnection().prepareStatement("Select name from songs where name=?");
        selectStatement.setString(1, name);
        ResultSet rs = selectStatement.executeQuery();
        if (rs.next()) {
            flag = true;
        }
        return flag;
    }

    public void insertSong(Songs song) throws Exception {
        if (isSongAvailable(song.getSongName())) {
            System.out.println("Song already available");
        } else {
            int res = 0;
            if (song.getSongId() != 0) {
                PreparedStatement selectStatement = SongsConnection.getSongsConnection().prepareStatement("insert into songs(songId,songName,artist,genre,album,duration) values(?,?,?,?,?,?);");
                selectStatement.setInt(1, song.getSongId());
                selectStatement.setString(2, song.getSongName());
                selectStatement.setString(3, song.getArtist());
                selectStatement.setString(4, song.getGenre());
                selectStatement.setString(5, song.getAlbum());
                selectStatement.setFloat(6, song.getDuration());
                res = selectStatement.executeUpdate();
            } else {
                PreparedStatement selectStatement = SongsConnection.getSongsConnection().prepareStatement("insert into songs(songName,artist,genre,album,duration) values(?,?,?,?,?);");
                selectStatement.setString(1, song.getSongName());
                selectStatement.setString(2, song.getArtist());
                selectStatement.setString(3, song.getGenre());
                selectStatement.setString(4, song.getAlbum());
                selectStatement.setFloat(5, song.getDuration());
                res = selectStatement.executeUpdate();
            }

            String str = res == 1 ? "Song inserted successfully" : "Something went wrong";
            System.out.println(str);
        }
    }

    public List<Songs> displayCatlog() throws Exception {
        List<Songs> allSongs = null;
        Statement selectStatement = SongsConnection.getSongsConnection().createStatement();
        ResultSet rs = selectStatement.executeQuery("select * from songs");
        if (rs.isBeforeFirst()) {
            allSongs = new ArrayList<>();
            while (rs.next()) {
                Songs song = new Songs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getFloat(6));
                allSongs.add(song);
            }
        }
        return allSongs;
    }
}