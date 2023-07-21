Drop TABLE if exists `playlist_songs`;
Drop TABLE if exists `songs`;
Drop TABLE if exists `playlists`;
Drop TABLE if exists `singers`;




CREATE TABLE IF NOT EXISTS `singers` (
    singer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    singer_name VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    country VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `songs` (
    song_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    song_title VARCHAR(200) NOT NULL,
    singer_id BIGINT,
    FOREIGN KEY (singer_id) REFERENCES `singers`(singer_id)
);

CREATE TABLE IF NOT EXISTS `playlists` (
    playlist_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    playlist_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS `playlist_songs` (
    playlist_id BIGINT,
    song_id BIGINT,
    FOREIGN KEY (playlist_id) REFERENCES `playlists`(playlist_id),
    FOREIGN KEY (song_id) REFERENCES `songs`(song_id)
);
