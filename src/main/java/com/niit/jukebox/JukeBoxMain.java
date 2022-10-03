package com.niit.jukebox;

import com.niit.jukebox.Service.PlayerService;
import com.niit.jukebox.Service.PlaylistContentService;
import com.niit.jukebox.Service.PlaylistService;
import com.niit.jukebox.Service.SongsService;
import com.niit.jukebox.model.JukeException;
import com.niit.jukebox.model.Player;
import com.niit.jukebox.model.Songs;

import javax.sound.sampled.Clip;
import java.util.*;

public class JukeBoxMain {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t\t\t\t---*---WELCOME TO JUKEBOX---*---\n");
        try {
            SongsService songsService = new SongsService();
            PlaylistService playlistService = new PlaylistService();
            PlaylistContentService playlistContentService = new PlaylistContentService();
            PlayerService playerService=new PlayerService();

            List<Songs> allSongsList;
            Hashtable<String, Integer> allPlaylist;
            allSongsList = songsService.getAllSongs();
            allPlaylist = playlistService.getAllPlaylist();

            songsService.selectAll(allSongsList);
            while (true) {
                System.out.println("\nChoose an option for:-");
                System.out.println("\t1-Songs\n\t2-Playlist\n\t3-Player\n\t4-Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        int choice2 = 0;
                        while (choice2 != 6) {
                            try {
                                System.out.println("\nEnter a choice in songs");
                                System.out.println("\t1-To add a song.\n\t2-Search song by name.\n\t3-Search song by album.\n\t4-Search song by artist.\n\t5-Search song by genre.\n\t6-Main Menu.\n\t7-Exit");
                                choice2 = sc.nextInt();
                                switch (choice2) {
                                    case 1: {
                                        sc.nextLine();
                                        System.out.println("Enter song's name");
                                        String songName = sc.nextLine();
                                        System.out.println("Enter song's artist");
                                        String songartist = sc.nextLine();
                                        System.out.println("Enter song's genre");
                                        String songgenre = sc.nextLine();
                                        System.out.println("Enter song's album");
                                        String songalbum = sc.nextLine();
                                        System.out.println("Enter song's duration");
                                        float songduration = sc.nextFloat();
                                        if (songsService.addSong(new Songs(songName, songartist, songgenre, songalbum, songduration), allSongsList)) {
                                            allSongsList = songsService.getAllSongs();
                                            System.out.println("Song added successfully");
                                        } else {
                                            System.out.println("Something gone wrong");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Enter a song name.");
                                        sc.nextLine();
                                        String songName = sc.nextLine();
                                        System.out.println(songsService.getOneSong(allSongsList, songName));
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Enter a album name.");
                                        sc.nextLine();
                                        String albumName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByAlbum(allSongsList, albumName));
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Enter an artist name.");
                                        sc.nextLine();
                                        String artistName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByArtist(allSongsList, artistName));
                                        break;
                                    }
                                    case 5: {
                                        System.out.println("Enter a genre name.");
                                        sc.nextLine();
                                        String genremName = sc.nextLine();
                                        songsService.selectAll(songsService.songsByGenre(allSongsList, genremName));
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Returning to main menu...");
                                        break;
                                    }
                                    case 7:
                                        System.exit(0);
                                    default:
                                        System.out.println("Wrong input");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Wrong Input entered");
                            }

                            catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            if (choice2 != 6) {
                                System.out.println("press\n\t0 to exit\n\t1 to continue\n\t2 for main menu");
                                int choice3 = sc.nextInt();
                                if (choice3 == 2) {
                                    break;
                                } else if (choice3 != 1) {
                                    System.exit(0);
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        int choice2 = 0;
                        while (choice2 != 6) {
                            try {
                                System.out.println("\nEnter a choice in playlist");
                                System.out.println("\t1-Show all playlist.\n\t2-Create a new playlist.\n\t3-Add song to a playlist.\n\t4-Add an album's songs to a playlist.\n\t5-Display a playlist.\n\t6-Main Menu.\n\t7-Exit");
                                choice2 = sc.nextInt();
                                switch (choice2) {
                                    case 1: {
                                        if (allPlaylist.size() == 0) {
                                            System.out.println("no playlist available");
                                        } else {
                                            System.out.println("-------------");
                                            Set<String> playlist = allPlaylist.keySet();
                                            Iterator<String> ite = playlist.iterator();
                                            while (ite.hasNext()) {
                                                System.out.println(ite.next());
                                            }
                                            System.out.println("-------------");
                                        }
                                        break;
                                    }
                                    case 2: {
                                        System.out.println("Enter new playlist name");
                                        sc.nextLine();
                                        String newPlaylistName = sc.nextLine();
                                        if (playlistService.addPlaylist(newPlaylistName, allPlaylist)) {
                                            System.out.println("New playlist created.");
                                            allPlaylist = playlistService.getAllPlaylist();
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 3: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistName = sc.nextLine();
                                        System.out.println("Enter song name");
                                        String songName = sc.nextLine();
                                        if (playlistContentService.addSongToPlaylist(allSongsList, allPlaylist, playlistName, songName)) {
                                            System.out.println("Song added successfully to the playlist");
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistName = sc.nextLine();
                                        System.out.println("Enter album name");
                                        String albumName = sc.nextLine();
                                        if (playlistContentService.addAlbumToPlaylist(allSongsList, allPlaylist, playlistName, albumName)) {
                                            System.out.println("Album added successfully");
                                        } else {
                                            System.out.println("Something gone wrong!");
                                        }
                                        break;
                                    }
                                    case 5: {
                                        System.out.println("Enter playlist name");
                                        sc.nextLine();
                                        String playlistname = sc.nextLine();
                                        songsService.selectAll(playlistContentService.playlistContent(playlistname, allPlaylist, allSongsList));
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Returning to main menu...");
                                        break;
                                    }
                                    case 7: {
                                        System.exit(0);
                                    }
                                    default:
                                        System.out.println("Wrong input");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            if (choice2 != 6) {
                                System.out.println("press\n\t0 to exit\n\t1 to continue\n\t2 for main menu");
                                int choice3 = sc.nextInt();
                                if (choice3 == 2) {
                                    break;
                                } else if (choice3 != 1) {
                                    System.exit(0);
                                }
                            }
                        }
                        break;
                    }

                    case 3: {
                        try {
                            System.out.println("Enter \n\t1-to play a song.\n\t2-to play a playlist\n\t3-back\n\t4-exit.");
                            int choice2 = sc.nextInt();
                            switch (choice2) {
                                case 1: {
                                    int choice3;
                                    do {
                                        System.out.println("Enter song name");
                                        sc.nextLine();
                                        String songName = sc.nextLine();
                                        Songs song = songsService.getOneSong(allSongsList, songName);
                                        if (!songsService.isSongAvailable(allSongsList, song)) {
                                            throw new JukeException("Song not present");
                                        } else {
                                            int songid = song.getSongId();
                                            System.out.println("Now playing- " + song.getSongName() + "...");
                                            Player player = new Player(songid);
                                            playerService.playSong(player);
                                            int choice4 = 0;
                                            do {
                                                System.out.println("Enter\n\t1-Stop\n\t2-Pause\n\t3-play another song");
                                                choice3 = sc.nextInt();
                                                switch (choice3) {
                                                    case 1:
                                                    case 3: {
                                                        playerService.stopSong(player);
                                                        choice4 = 0;
                                                        break;
                                                    }
                                                    case 2: {
                                                        playerService.pauseSong(player);
                                                        System.out.println("enter \n\t1-to resume\n\t2-to get back");
                                                        choice4 = sc.nextInt();
                                                        if (choice4 == 1) {
                                                            playerService.resumeSong(player);
                                                            break;
                                                        } else {
                                                            playerService.stopSong(player);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            while (choice4 == 1);
                                        }
                                    }
                                    while (choice3 == 3);

                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter playlist name");
                                    sc.nextLine();
                                    String playlistName = sc.nextLine();
                                    List<Songs> songsInPlaylist = playlistContentService.playlistContent(playlistName, allPlaylist, allSongsList);
                                    int songid;
                                    for (Songs song : songsInPlaylist) {
                                        songid = song.getSongId();
                                        System.out.println("Now playing- " + song.getSongName() + "...");
                                        Player player = new Player(songid);
                                        playerService.playSong(player);
                                        int choice4 = 0;
                                        int choice3;
                                        do {
                                            System.out.println("Enter\n\t1-Stop song\n\t2-Pause\n\t3-Play next");
                                            choice3 = sc.nextInt();
                                            switch (choice3) {
                                                case 1:
                                                case 3: {
                                                    playerService.stopSong(player);
                                                    choice4 = 0;
                                                    break;
                                                }
                                                case 2: {
                                                    playerService.pauseSong(player);
                                                    System.out.println("enter \n\t1-to resume\n\t2-to get back");
                                                    choice4 = sc.nextInt();
                                                    if (choice4 == 1) {
                                                        playerService.resumeSong(player);
                                                        break;
                                                    } else {
                                                        playerService.stopSong(player);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        while (choice4 == 1);
                                        if (choice3 == 1) {
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    break;
                                }
                                case 4: {
                                    System.exit(0);
                                }
                            }
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        System.exit(0);
                    }
                    default:
                        System.out.println("Wrong input");
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
