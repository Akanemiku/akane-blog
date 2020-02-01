package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.User;

public interface UserService {
    User login(String username,String password);
}
