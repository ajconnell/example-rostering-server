package com.wackywallaby.rosterexample.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class JpaLocalDateTimeConvertor implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        Timestamp timestamp = localDateTime == null ? null : Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp == null ? null : timestamp.toLocalDateTime();
        return localDateTime;
    }
}
