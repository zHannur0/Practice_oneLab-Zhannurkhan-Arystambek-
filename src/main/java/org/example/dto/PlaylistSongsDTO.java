package org.example.dto;


import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor

public class PlaylistSongsDTO {
    Long id;
    Long playlistId;
    Long songId;
}
