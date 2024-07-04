package com.mirror.service;

import com.mirror.dto.UserLoginDTO;
import com.mirror.entity.User;

public interface UserService {
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
