package com.chronos.chronosshop.service;

import com.chronos.chronosshop.entity.Users;

public interface IUserService extends IService<Users,String>{
    public boolean saveUserFromGoogle(Users user);

    public String linkTokenResetPassword(String email);
    public boolean changePassword(String id,String password);

    public boolean changeNewPassword(String tokenKey,String tokenValue,String password);
}
