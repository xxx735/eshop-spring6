package com.xhxy.eshop.validator;

import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.existUsername(user.getUsername())) {
            // 调用Errors类的rejectValue方法校验
            errors.rejectValue("username", "existUsernameError", "用户名已经存在，请换一个新的用户名");
        }
    }
}
