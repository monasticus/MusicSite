package com.musicsite.app;

import com.musicsite.entity.Category;
import com.musicsite.entity.User;
import com.musicsite.repository.CategoryRepository;
import com.musicsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<String, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User convert(String id) {
        return userRepository.findOne(Long.parseLong(id));
    }
}
