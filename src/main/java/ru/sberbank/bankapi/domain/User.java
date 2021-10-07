package ru.sberbank.bankapi.domain;

import lombok.*;

import javax.persistence.*;
import java.lang.ref.Reference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Version
    private Long version;

}