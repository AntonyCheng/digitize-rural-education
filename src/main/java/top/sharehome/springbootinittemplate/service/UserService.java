package top.sharehome.springbootinittemplate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.sharehome.springbootinittemplate.model.dto.user.UserPageDto;
import top.sharehome.springbootinittemplate.model.dto.user.UserResetDto;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.vo.user.UserPageVo;

/**
 * 用户服务接口
 *
 * @author AntonyCheng
 */

public interface UserService {
    /**
     * 根据ID删除用户
     */
    void deleteById(Long id);

    /**
     * 根据ID重置用户新密码
     */
    void resetPwdById(UserResetDto userResetDto);

    /**
     * 分页获取用户文件信息
     */
    Page<UserPageVo> pageUser(UserPageDto userPageDto);
}
