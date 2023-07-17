package org.example.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SingerDTO {
     private Long id;
     private String name;
     private String songName;

}
