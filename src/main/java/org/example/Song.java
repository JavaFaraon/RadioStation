package org.example;

public class Song extends TypeOfBroadcasts {
    String nameOfSinger;
    String nameOfSong;

    @Override
    public int getPrice() {
        return 0;
    }

    public Song(double duration, String nameOfSinger, String nameOfSong) {
        super(duration);
        this.nameOfSinger = nameOfSinger;
        this.nameOfSong = nameOfSong;
    }

    @Override
    public String toString() {
        return "Song of " + nameOfSinger + " -" +
                " '" + nameOfSong + '\'' +
                ", duration: " + duration +
                " мин";
    }
}
