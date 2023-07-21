package org.example.mapper;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SongMapper {
    private long songId;
    private String songTitle;
    private long singerId;
}
