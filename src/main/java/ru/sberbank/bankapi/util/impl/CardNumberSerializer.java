package ru.sberbank.bankapi.util.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * formatted number of card to JSON
 */
public class CardNumberSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            IOException {
        StringBuilder result = new StringBuilder(value);
        int count = 0;
        for (int i = 4; i < value.length(); i += 4) {
            result.insert(i + count, " ");
            count++;
        }
        jgen.writeString(result.toString());
    }
}
