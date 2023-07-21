INSERT INTO `singers` (singer_name, genre, country)
VALUES
    ('Zhannurkhan Arystambek', 'Pop', 'Kazakhstan'),
    ('Childish Gambino', 'Hip-hop', 'USA'),
    ('Tame Impala', ' Indie rock', 'USA');


INSERT INTO `songs` (song_title, singer_id)
VALUES
    ('Zhazda apamnyn auylynda', 1),
    ('Aspanga qaraimyn', 1),
    ('Redbone', 2),
    ('Borderline', 3);


INSERT INTO `playlists` (playlist_name)
VALUES
    ('My Fav'),
    ('Top'),
    ('Tema musictar');


INSERT INTO `playlist_songs` (playlist_id, song_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 3),
    (3, 3),
    (3, 4);
