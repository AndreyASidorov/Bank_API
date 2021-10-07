package ru.sberbank.bankapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sberbank.bankapi.util.impl.CardNumberSerializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    @JsonSerialize(using = CardNumberSerializer.class)
    private String number;
    private AccountNumberDto account;
}
