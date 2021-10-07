package ru.sberbank.bankapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "AccountFindByNumber",
                query = "from Account where number = :number"),
        @org.hibernate.annotations.NamedQuery(name = "depositMoneyToAccount",
                query = "Update Account set balance = :balance where id = :id")})
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
    private String number;
    private BigDecimal balance;
    @Version
    private Long version;
}
