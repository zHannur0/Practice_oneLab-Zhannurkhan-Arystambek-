package org.example.mapper;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SingerMapper {
     private long singerId;
     private String singerName;
     private String genre;
     private String country;

}
