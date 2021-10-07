package ru.sberbank.bankapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQuery(name = "getAllCardsByAccount",
        query = "from Card where account = :account")
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;
    private String number;
}
