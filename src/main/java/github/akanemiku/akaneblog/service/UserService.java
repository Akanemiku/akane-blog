package github.akanemiku.akaneblog.service;

import github.akanemiku.akaneblog.model.User;

public interface UserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 根据用户id获取用户
     * @return
     */
    User getUserById(Integer uid);

    /**
     * 新增或更新用户
     * @param user
     */
    void saveUser(User user);
}
