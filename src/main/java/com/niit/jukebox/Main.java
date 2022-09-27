package com.niit.jukebox;

import com.niit.jukebox.Service.PlaylistContentService;
import com.niit.jukebox.Service.PlaylistService;
import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.dao.PlayListContentDAO;
import com.niit.jukebox.dao.PlayListDAO;
import com.niit.jukebox.dao.SongsDAO;
import com.niit.jukebox.model.Songs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
           Songs s1=new Songs("ancingque","b","c","d",2.3f);
            Songs s2=new Songs("ancigque","b","c","d",2.3f);
//            System.out.println(SongsDAO.insertSong(s1));
//            System.out.println(SongsService.addSong(s1));
//            System.out.println(SongsDAO.displayCatlog());
//            List<Songs> list;
//            list= SongsDAO.displayCatlog();
//            System.out.println(SongsService.getOneSong(list,"Dancing Queen"));
//            System.out.println(SongsService.songsByAlbum(list,"sal"));
//            System.out.println(SongsService.songsByArtist(list,"Big"));
//            System.out.println(SongsService.songsByGenre(list,"jazz"));
//            System.out.println(SongsService.isSongAvailable(list,s1));
//            SongsService.selectAll(SongsService.songsByGenre(list,"Jazz"));
//            SongsService.selectAll(list);
//            System.out.println(SongsService.addSong(s1));
//            System.out.println(SongsService.addSong(s2));
//            SongsService.selectAll(list);
//            list= SongsDAO.displayCatlog();
//            SongsService.selectAll(list);

//            System.out.println(PlayListDAO.createPlayList("firstPlaylist"));
//            System.out.println(PlayListDAO.viewPlaylist());
//            System.out.println(PlayListContentDAO.addSongToAPlaylist(1,1));
//            System.out.println(PlayListContentDAO.viewSongsInAPlaylist(1));
//            System.out.println(PlaylistService.getAllPlaylist());
            System.out.println(PlaylistContentService.playlistContent("firstPlaylist", PlaylistService.getAllPlaylist(), (ArrayList)SongsDAO.displayCatlog()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
