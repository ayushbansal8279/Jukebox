package com.songtest;

import com.niit.jukebox.dao.SongsDAO;
import com.niit.jukebox.model.Songs;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class selectAllTest {

    List<Songs> songList;

    @Before
    public void setUp() {
        songList = new ArrayList<>();
    }

    @After
    public void tearDown() {
        songList = null;
    }

    @Test
    public void selectAllSuccess() {
        try {
            songList = SongsDAO.displayCatlog();
            Assert.assertEquals(7, songList.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void selectAllNotNull() {
        try {
            songList = SongsDAO.displayCatlog();
            Assert.assertNotNull(songList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
