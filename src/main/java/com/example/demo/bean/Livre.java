package com.example.demo.bean;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Livres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String isbn;
    String titre;
    String auteur;
}
