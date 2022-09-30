package com.niit.jukebox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayListContentDAO {

    public static boolean addSongToAPlaylist(int playlistId,int songId) throws Exception{
        PreparedStatement selectStatement = JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into playlistContent(playlistId,songId) values(?,?);");
        selectStatement.setInt(1,playlistId);
        selectStatement.setInt(2,songId);
        return (selectStatement.executeUpdate()==1);
    }

    public static ArrayList<Integer> viewSongsInAPlaylist(int playlistId) throws Exception{
        ArrayList<Integer> songIdList=new ArrayList<>();
        PreparedStatement selectStatement = JukeBoxConnection.getJukeBoxConnection().prepareStatement("Select songId from playlistContent where playlistId=?;");
        selectStatement.setInt(1,playlistId);
        ResultSet rs=selectStatement.executeQuery();
        while (rs.next()){
            songIdList.add(rs.getInt(1));
        }
        return songIdList;
    }
}
