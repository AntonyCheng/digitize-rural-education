package top.sharehome.springbootinittemplate.controller.user;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.service.ResourceService;

import javax.annotation.Resource;

/**
 * 用户端资源（share）控制器
 *
 * @author AntonyCheng
 */
@RequestMapping("/user/resource")
@SaCheckRole(Constants.ROLE_USER)
@RestController
public class UserResourceController {

    @Resource
    private ResourceService resourceService;

}
