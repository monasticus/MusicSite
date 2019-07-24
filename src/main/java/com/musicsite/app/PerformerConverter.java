package com.musicsite.app;

import com.musicsite.performer.Performer;
import com.musicsite.performer.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PerformerConverter implements Converter<String, Performer> {

    @Autowired
    PerformerRepository performerRepository;

    @Override
    public Performer convert(String pseudonym) {
        return performerRepository.getFirstPerformerByPseudonymIgnoreCase(pseudonym);
    }
}
