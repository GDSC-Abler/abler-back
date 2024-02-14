package com.abler.user;

import com.abler.util.BaseResponseStatus;
import com.abler.user.model.User;
import com.abler.util.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService3 {

    private final UserProvider userProvider;
    private final UserDao userDao;

    @Autowired
    public UserService3(UserProvider userProvider, UserDao userDao) {
        this.userProvider = userProvider;
        this.userDao = userDao;
    }

    public User createUser(User user) throws BaseException {
        if (userProvider.checkEmail(user.getEmail()) == 1)
            throw new BaseException(BaseResponseStatus.POST_USERS_EXISTS_EMAIL);
        try {
            return this.userDao.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }

}
