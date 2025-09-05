package com.carbackend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String role;
}
