package edu.wctc.my.kgable.first;

public class Song
{
	private String name;
	private String artist;

	public Song(String name, String artist)
	{
		this.name = name;
		this.artist = artist;
	}

	public String getName()
	{
		return name;
	}

	public String getArtist()
	{
		return artist;
	}
}
