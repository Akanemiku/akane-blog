package github.akanemiku.akaneblog.service.impl;

import github.akanemiku.akaneblog.enums.ErrorEnum;
import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.User;
import github.akanemiku.akaneblog.repository.UserRepository;
import github.akanemiku.akaneblog.service.UserService;
import github.akanemiku.akaneblog.utils.SpecialUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw new InternalException(ErrorEnum.USERNAME_PASSWORD_IS_EMPTY);
        password = SpecialUtil.MD5encode(password);
        User user = userRepository.findByUsername(username);
        System.out.println("login: " + user.toString());
        if (null == user)
            throw new InternalException(ErrorEnum.USERNAME_PASSWORD_ERROR);
        if (password != null && password.equals(user.getPassword())) {
            return user;
        } else {
            throw new InternalException(ErrorEnum.USERNAME_PASSWORD_ERROR);
        }
    }

    @Override
    public User getUserById(Integer uid) {
        Optional<User> user = userRepository.findById(uid);
        return user.get();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user == null)
            throw new InternalException(ErrorEnum.PARAM_IS_EMPTY);
        userRepository.save(user);
    }
}
