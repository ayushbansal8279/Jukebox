package com.niit.jukebox.Service;

import com.niit.jukebox.DAO.PlayListContentDAO;
import com.niit.jukebox.model.JukeException;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class PlaylistContentService {

    public boolean isSongAvailableInPlaylist(String playlistName,String songName,Hashtable<String,Integer> playlist,List<Songs> songlist)throws Exception{
        boolean res=false;
        List<Songs> songInPlaylist=playlistContent(playlistName,playlist,songlist);
        for(Songs song:songInPlaylist){
            if(song.getSongName().equalsIgnoreCase(songName)){
                res=true;
                break;
            }
        }
        return res;
    }

    public boolean addSongToPlaylist(List<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String songName)throws JukeException,Exception{
        boolean res;
        if(isSongAvailableInPlaylist(playlistName,songName,playlist,songList)){
            throw new JukeException("Song already available in playlist");
        }
        else {
            int songId =0;
            if (playlistName != null && songName != null) {

                boolean songAvailable = false;
                for (Songs currentSong : songList) {
                    if (currentSong.getSongName().equalsIgnoreCase(songName)) {
                        songId = currentSong.getSongId();
                        songAvailable = true;
                        break;
                    }
                }

                if (!songAvailable) {
                    throw new JukeException("This song not available");
                } else {
                    int playlistId = playlist.get(playlistName);
                    res = PlayListContentDAO.addSongToAPlaylist(playlistId, songId);
                }
            } else {
                throw new JukeException("Data is null.");
            }
        }
            return res;
    }

    public boolean addAlbumToPlaylist(List<Songs> songList, Hashtable<String,Integer> playlist,String playlistName,String albumName)throws Exception{
        boolean res=false;
        if (playlistName != null && albumName != null) {
            if(playlist.containsKey(playlistName.toLowerCase())) {
                int playlistId=playlist.get(playlistName);

                ArrayList<Songs> songAlbumList = new ArrayList<>();
                Iterator<Songs> ite = songList.iterator();
                while (ite.hasNext()) {
                    Songs song = ite.next();
                    if (song.getAlbum().equalsIgnoreCase(albumName)) {
                            songAlbumList.add(song);
                        }
                    }

                if(songAlbumList.size()==0){
                    throw new JukeException("Album not found");
                }
                else {
                    Iterator<Songs> ite2 = songAlbumList.iterator();
                    Songs songs;
                    while (ite2.hasNext()) {
                        songs = ite2.next();
                        if (!isSongAvailableInPlaylist(playlistName, songs.getSongName(), playlist, songList)) {
                            PlayListContentDAO.addSongToAPlaylist(playlistId, songs.getSongId());
                        }
                    }
                    res = true;
                }
            }
            else {
                throw new JukeException("Playlist doesn't exist");
            }
        }
        else {
            throw new JukeException("Data is null");
        }
        return res;
    }

    public List<Songs> playlistContent(String playlistName,Hashtable<String,Integer> playlist,List<Songs> songlist)throws Exception{
        List<Songs> songListInPlaylist=new ArrayList();
        if(playlistName!=null){
            if(playlist.containsKey(playlistName.toLowerCase())){
            List<Integer> songIdList=new ArrayList<>();
            int playlistId=playlist.get(playlistName);
            songIdList=PlayListContentDAO.viewSongsInAPlaylist(playlistId);

                for (int id: songIdList){
                    for(Songs song:songlist){
                        if(song.getSongId()==id){
                            songListInPlaylist.add(song);
                            break;
                        }
                    }
                }
            }
            else {
                throw new JukeException("no such playlist found.");
            }
        }
        else {
            throw new JukeException("null value found");
        }
        return songListInPlaylist;
    }
}