package org.example.mapper;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Builder
public class PlaylistMapper {
    private long playlistId;
    private String playlistName;
}
