package com.example.demo.bean;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Livres")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull
    String isbn;
    @NonNull
    String titre;
    @NonNull
    String auteur;


}
