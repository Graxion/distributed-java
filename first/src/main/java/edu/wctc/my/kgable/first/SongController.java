package edu.wctc.my.kgable.first;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SongController
{
	@RequestMapping("/songs")

	public List<Song> getAllSongs()
	{
		List<Song> songs = new ArrayList<>();

		songs.add(new Song("Thriller", "Micheal Jackson"));
		songs.add(new Song("Like a Prayer", "Madonna"));
		songs.add(new Song("When Doves Cry", "Prince"));
		songs.add(new Song("Billie Jean", "Micheal Jackson"));
		songs.add(new Song("Don't Stop Believin", "Journey"));

		return songs;
	}
}
