package ru.sberbank.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositMoneyDto {
    private String numberAccount;
    private BigDecimal money;
}
