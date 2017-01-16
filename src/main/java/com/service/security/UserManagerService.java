package com.service.security;

import com.domain.request.FullUserReq;
import com.repository.UserAuthorityRepository;
import com.repository.UserRepository;
import com.domain.security.Role;
import com.domain.security.User;
import com.domain.security.UserAuthority;
import com.domain.request.UserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * Created by tang.cheng on 2017/1/14.
 */
@Service
public class UserManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerService.class);
    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 第一个创建的一定是管理员
     *
     * @return
     */
    public boolean hasAdminAccount() {
        Long count = userAuthorityRepository.countByAuthorityId(Role.ADMIN.getId());
        LOGGER.info("admin count:{}", count);
        return count > 0;
    }

    public void create(UserReq req, Number authorityId) {
        User user = new User();
        user.setUsername(req.getUsername());
        String encodePwd = passwordEncoder.encode(req.getPassword());
        LOGGER.info("{}", encodePwd);
        user.setPassword(encodePwd);
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);

        UserAuthority authority = new UserAuthority();
        authority.setUserId(user.getId());
        authority.setAuthorityId((Long) authorityId);
        userAuthorityRepository.save(authority);
    }

    public void create(FullUserReq fullUserReq) {
        create(fullUserReq, fullUserReq.getRole());
    }

    public void manager(String username, Boolean status) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.warn("{} not exists", username);
            return;
        }
        if (Objects.equals(status, user.getEnabled())) {
            LOGGER.warn("{}, no changes.Nothing to do ", username);
            return;
        }
        user.setEnabled(status);
        userRepository.saveAndFlush(user);
    }
}
