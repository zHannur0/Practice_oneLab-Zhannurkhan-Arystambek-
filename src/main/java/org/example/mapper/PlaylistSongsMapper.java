package org.example.mapper;


import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor

public class PlaylistSongsMapper {
    Long playlistId;
    Long songId;
}
