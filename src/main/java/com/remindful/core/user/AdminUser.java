package com.remindful.core.user;

public class AdminUser extends UserDecorator {

  @Override
  public boolean updateEmail(String email) {
    return false;
  }

  @Override
  public boolean updatePassword(String password) {
    return false;
  }

  @Override
  public boolean updateAddress(String address) {
    return false;
  }
}
