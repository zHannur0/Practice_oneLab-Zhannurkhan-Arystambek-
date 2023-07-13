package org.example.dto;


import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@Builder

public class PlaylistSongsDTO {
    Long id;
    Long playlistId;
    Long songId;
}
