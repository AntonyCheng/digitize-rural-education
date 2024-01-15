package top.sharehome.springbootinittemplate.controller.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sharehome.springbootinittemplate.common.base.Constants;
import top.sharehome.springbootinittemplate.common.base.R;
import top.sharehome.springbootinittemplate.common.validate.GetGroup;
import top.sharehome.springbootinittemplate.common.validate.PutGroup;
import top.sharehome.springbootinittemplate.model.dto.user.UserPageDto;
import top.sharehome.springbootinittemplate.model.dto.user.UserResetDto;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.vo.user.UserPageVo;
import top.sharehome.springbootinittemplate.service.UserService;
import top.sharehome.springbootinittemplate.utils.satoken.LoginUtils;

import javax.annotation.Resource;

/**
 * 管理端用户控制器
 *
 * @author AntonyCheng
 * @since 2024/1/15 23:16:28
 */
@RequestMapping("/admin/user")
@SaCheckRole(Constants.USER_ROLE_ADMIN)
@RestController
public class AdminUserController {

    @Resource
    private UserService userService;

    /**
     * 根据用户ID删除数据
     *
     * @param id 用户id
     * @return 返回结果
     */
    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return R.ok("删除成功");
    }

    /**
     * 根据用户ID重置用户密码（管理员指定）
     */
    @PutMapping("/reset/new")
    public R<String> resetNew(@Validated({PutGroup.class}) UserResetDto userResetDto) {
        userService.resetPwdById(userResetDto);
        LoginUtils.logout(userResetDto.getId());
        return R.ok("重置密码成功，新密码为：" + userResetDto.getNewPassword());
    }

    /**
     * 根据用户ID重置用户密码（默认123456）
     */
    @PutMapping("/reset/default/{id}")
    public R<String> resetDefault(@PathVariable("id") Long id) {
        UserResetDto userResetDto = new UserResetDto();
        userResetDto.setId(id);
        userResetDto.setNewPassword("123456");
        userService.resetPwdById(userResetDto);
        LoginUtils.logout(userResetDto.getId());
        return R.ok("重置密码成功，新密码为：123456");
    }

    /**
     * 分页获取用户信息
     *
     * @param pageModel   分页实体类
     * @param userPageDto 用户分页Dto类
     * @return 返回分页数据
     */
    @GetMapping("page")
    public R<Page<UserPageVo>> pageUser(@Validated(GetGroup.class) PageModel pageModel, UserPageDto userPageDto) {
        Page<UserPageVo> res = userService.pageUser(pageModel, userPageDto);
        return R.ok(res);
    }

}
