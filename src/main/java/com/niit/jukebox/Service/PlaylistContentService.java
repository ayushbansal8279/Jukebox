package com.niit.jukebox.Service;

import com.niit.jukebox.dao.PlayListContentDAO;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class PlaylistContentService {

    public static boolean addSongToPlaylist(ArrayList<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String songName)throws Exception{
        int playlistId=0;
        int songId=0;
        boolean res;
        if(playlistName!=null && songName!= null){
            playlistId=playlist.get(playlistName);
            Iterator<Songs> ite=songList.iterator();
            while (ite.hasNext()){
                Songs song=ite.next();
                if(song.getSongName().equals(songName)){
                    songId=song.getSongId();
                    break;
                }
            }
        }
            if(songId==0 || playlistId==0){
                res=false;
            }
            else {
                res= PlayListContentDAO.addSongToAPlaylist(playlistId,songId);
            }
            return res;
    }

    public static boolean addAlbumTOPlaylist(ArrayList<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String albumName)throws Exception{
        int playlistId=0;
        int songId=0;
        boolean res=false;
        ArrayList<Integer> songIdList =new ArrayList<>();
        if(playlistName!=null && albumName!= null){
            playlistId=playlist.get(playlistName);
            if(songList.isEmpty() || playlistId==0){
                res=false;
            }
            else {

                Iterator<Songs> ite=songList.iterator();
                while (ite.hasNext()){
                    Songs song=ite.next();
                    if(song.getAlbum().equals(albumName)){
                        songIdList.add(song.getSongId());
                    }
                }
            }
            Iterator<Integer> ite2=songIdList.iterator();
            while (ite2.hasNext()){
                songId= ite2.next();
                PlayListContentDAO.addSongToAPlaylist(playlistId,songId);
            }
            res=true;
        }
        return res;
    }

    public static List<Songs> playlistContent(String playlistName,Hashtable<String,Integer> playlist,ArrayList<Songs> songlist)throws Exception{
        List<Songs> songListInPlaylist=new ArrayList();
        if(playlistName!=null){
            List<Integer> songIdList=new ArrayList<>();
            int playlistId=0;
            playlistId=playlist.get(playlistName);
            if (playlistId!=0){
                songIdList=PlayListContentDAO.viewSongsInAPlaylist(playlistId);
            }
            for (int id: songIdList){
                for(Songs song:songlist){
                    if(song.getSongId()==id){
                        songListInPlaylist.add(song);
                    break;
                    }
                }
            }
        }
        return songListInPlaylist;
    }

}
