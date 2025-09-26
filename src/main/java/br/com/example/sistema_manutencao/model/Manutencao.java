package br.com.example.sistema_manutencao.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode (of = "id")
@ToString

public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    @NotBlank
    @Column(length = 100, nullable = false)
    private String title;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String setor;

    @Size(min = 3, max = 100)
    @NotBlank
    @Column(length = 100, nullable = false)
    private String urgencia;

    @Size(min = 3, max = 1000)
    @NotBlank
    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDate finishedAt;


}
