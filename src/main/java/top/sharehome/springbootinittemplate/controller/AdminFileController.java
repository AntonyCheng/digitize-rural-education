package top.sharehome.springbootinittemplate.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.mapper.FileMapper;

import javax.annotation.Resource;

/**
 * 管理端文件控制器
 *
 * @author AntonyCheng
 * @since 2023/12/6 23:16:31
 */
@RestController
@RequestMapping("/admin_file")
@SaCheckRole(Constants.USER_ROLE_ADMIN)
public class AdminFileController {

    @Resource
    private FileMapper fileMapper;

}