package com.niit.jukebox.dao;

import com.mysql.cj.xdevapi.SelectStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;

public class PlayListDAO {

    public static boolean createPlayList(String playListName) throws Exception{
        PreparedStatement selectStatement = JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into playlist(playListName) values(?);");
        selectStatement.setString(1,playListName);
        return (selectStatement.executeUpdate()==1);
    }

    public static Hashtable<String,Integer> viewPlaylist() throws Exception{
        Hashtable<String,Integer> playlistTable=new Hashtable<>();
        Statement selectStatement = JukeBoxConnection.getJukeBoxConnection().createStatement();
        ResultSet rs=selectStatement.executeQuery("Select * from playlist;");
        while (rs.next()){
            playlistTable.put(rs.getString(2),rs.getInt(1) );
        }
        return playlistTable;
    }
}
