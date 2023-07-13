package org.example.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SongDTO {
    private Long id;
    private String name;
    private SingerDTO singer;
}
