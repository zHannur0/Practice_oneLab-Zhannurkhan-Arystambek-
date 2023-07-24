package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "singers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "singer_id")
    private Long singerId;

    @Column(name = "singer_name", nullable = false, length = 100)
    private String singerName;

    @Column(name = "genre", length = 50)
    private String genre;

    @Column(name = "country", length = 50)
    private String country;
}
