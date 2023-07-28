package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@Entity
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "playlistSet"  })
@ToString
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long songId;

    @Column(name = "song_title", nullable = false, length = 200)
    private String songTitle;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "playlist_songs",
        joinColumns = @JoinColumn(name = "song_id"),
        inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private Set<Playlist> playlistSet = new HashSet<>();
}

