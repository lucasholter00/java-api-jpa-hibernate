package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private String publisher;

    @Column
    private String developer;

    @Column
    private int releaseYear;

    @Column
    private boolean isEarlyAccess;

}
