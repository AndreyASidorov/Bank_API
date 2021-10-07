package ru.sberbank.bankapi.domain;

import lombok.*;

import javax.persistence.*;
import java.lang.ref.Reference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@org.hibernate.annotations.NamedQueries({
//        @org.hibernate.annotations.NamedQuery(name = "UserFindByUserName",
//                query = "from User where userName = :username"),
//        @org.hibernate.annotations.NamedQuery(name = "User_save",
//                query = "Update User set userName = :username, password =: password where id = :id")})
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;

}