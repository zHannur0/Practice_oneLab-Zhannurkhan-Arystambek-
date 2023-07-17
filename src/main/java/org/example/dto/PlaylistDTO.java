package org.example.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Builder
public class PlaylistDTO {
    Long id;
    String name;
}
