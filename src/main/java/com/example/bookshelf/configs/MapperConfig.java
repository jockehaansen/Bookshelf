package com.example.bookshelf.configs;

import org.hibernate.collection.spi.PersistentBag;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;
import java.util.List;

public class MapperConfig {

    public static ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom converter to handle PersistentBag to List conversion
        Converter<PersistentBag, List<?>> persistentBagToListConverter =
                new Converter<>() {
                    @Override
                    public List<?> convert(MappingContext<PersistentBag, List<?>> context) {
                        return new ArrayList<>(context.getSource());
                    }
                };

        // Register the custom converter
        modelMapper.addConverter(persistentBagToListConverter);

        return modelMapper;
    }
}