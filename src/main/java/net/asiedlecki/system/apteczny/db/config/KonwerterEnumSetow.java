package net.asiedlecki.system.apteczny.db.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import net.asiedlecki.system.apteczny.model.enumy.TypPracownikaEnum;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class KonwerterEnumSetow implements AttributeConverter<EnumSet<TypPracownikaEnum>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<TypPracownikaEnum> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public EnumSet<TypPracownikaEnum> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return EnumSet.noneOf(TypPracownikaEnum.class);
        }
        String[] values = dbData.split(",");
        Set<TypPracownikaEnum> enumSet = Stream.of(values)
                .map(TypPracownikaEnum::valueOf)
                .collect(Collectors.toSet());
        return EnumSet.copyOf(enumSet);
    }
}