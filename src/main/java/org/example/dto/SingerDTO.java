package org.example.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SingerDTO {
     private Long id;
     private String name;
     private List<SongDTO> songs;

     public void addSongs(SongDTO song) {
          songs.add(song);
     }
}
