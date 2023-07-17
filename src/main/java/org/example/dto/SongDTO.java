package org.example.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SongDTO {
    private Long id;
    private String name;
    private String singer;
}
