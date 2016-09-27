package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.User;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping(value = "/controller/user", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    private static final String LIST_USER_ROLE = "user_roles";

    @Autowired
    private ControllerUtils<User> utils;

    @Autowired
    private CommonService<User> userService;

    @Autowired
    private CommonService<UserRole> userRoleService;

    @PostConstruct
    public void init() {
        utils.init("path.page.edit_user", "path.page.users", User.class, userService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(LIST_USER_ROLE, userRoleService.getAll(UserRole.class));
        model.addAttribute(ControllerUtils.ENTITY, updateEntity(utils.findById(paramMap, model), paramMap));
        return utils.getPage(paramMap, model);
    }

    protected User updateEntity(User user, Map<String, String> paramMap) {
        if (user == null)
            user = new User();
        if (paramMap.containsKey(User.NAME)) user.setName(paramMap.get(User.NAME).trim());
        if (paramMap.containsKey(User.SURNAME)) user.setSurname(paramMap.get(User.SURNAME).trim());
        if (paramMap.containsKey(User.LOGIN)) user.setLogin(paramMap.get(User.LOGIN).trim());
        if (paramMap.containsKey(User.PASSWORD)) user.setPassword(paramMap.get(User.PASSWORD).trim());
        if (paramMap.containsKey(User.USER_ROLE_ID))
            user.setRole(userRoleService.getById(UserRole.class, utils.getParamIntDef(paramMap, User.USER_ROLE_ID, 1)));
        return user;
    }
}
