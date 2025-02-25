package org.zhang.word_backend.service;

import org.springframework.stereotype.Service;
import org.zhang.word_backend.pojo.User;
@Service
public interface UserService {
    Integer registerUser(User user);

    User loginUser(User user);
}
