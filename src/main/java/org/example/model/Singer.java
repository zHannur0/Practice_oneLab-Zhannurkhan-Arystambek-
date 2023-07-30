package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({ "songs" })
@ToString
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("singerId")
    @Column(name = "singer_id")
    private Long singerId;

    @JsonProperty("singerName")
    @Column(name = "singer_name", nullable = false, length = 100)
    private String singerName;

    @JsonProperty("genre")
    @Column(name = "genre", length = 50)
    private String genre;


    @JsonProperty("country")
    @Column(name = "country", length = 50)
    private String country;

    @ToString.Exclude
    @OneToMany(mappedBy = "singer", cascade = CascadeType.REMOVE)
    private Set<Song> songs = new HashSet<>();
}
