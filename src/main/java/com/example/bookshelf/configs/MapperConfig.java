package com.example.bookshelf.configs;

import org.hibernate.collection.spi.PersistentBag;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperConfig {

    public static ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<PersistentBag<?>, List<Object>> persistentBagToListConverter =
                context -> new ArrayList<>(context.getSource());

        modelMapper.addConverter(persistentBagToListConverter);

        return modelMapper;
    }
}