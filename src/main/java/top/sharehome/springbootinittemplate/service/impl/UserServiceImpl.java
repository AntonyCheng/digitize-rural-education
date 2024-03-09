package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeTransactionException;
import top.sharehome.springbootinittemplate.mapper.UserMapper;
import top.sharehome.springbootinittemplate.model.dto.user.UserPageDto;
import top.sharehome.springbootinittemplate.model.dto.user.UserResetDto;
import top.sharehome.springbootinittemplate.model.entity.User;
import top.sharehome.springbootinittemplate.model.vo.user.UserPageVo;
import top.sharehome.springbootinittemplate.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public void deleteById(Long id) {
        if (Objects.isNull(userMapper.selectById(id))) {
            throw new CustomizeReturnException(ReturnCode.DATA_IS_NOT_AVAILABLE_IN_THE_DATABASE);
        }
        int deleteResult = userMapper.deleteById(id);
        if (deleteResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
    }

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public void resetPwdById(UserResetDto userResetDto) {
        if (Objects.isNull(userMapper.selectById(userResetDto.getId()))) {
            throw new CustomizeReturnException(ReturnCode.DATA_IS_NOT_AVAILABLE_IN_THE_DATABASE);
        }
        User user = new User();
        user.setId(userResetDto.getId());
        user.setPassword(userResetDto.getNewPassword());
        int updateResult = userMapper.updateById(user);
        if (updateResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = CustomizeTransactionException.class)
    public Page<UserPageVo> pageUser(UserPageDto userPageDto) {
        Page<User> page = new Page<>(userPageDto.getPage(), userPageDto.getSize());
        Page<UserPageVo> res = new Page<>(userPageDto.getPage(), userPageDto.getSize());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 处理用户账号查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(userPageDto.getAccount()), User::getAccount, userPageDto.getAccount());
        // 处理用户昵称查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(userPageDto.getName()), User::getName, userPageDto.getName());
        // 处理用户角色查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(userPageDto.getRole()), User::getRole, userPageDto.getRole());
        // 处理用户学校查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(userPageDto.getSchool()), User::getSchool, userPageDto.getSchool());

        userMapper.selectPage(page, queryWrapper);
        List<UserPageVo> userPageVoList = page.getRecords().stream().map(user -> {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtils.copyProperties(user, userPageVo);
            return userPageVo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(page, res, "records");
        res.setRecords(userPageVoList);
        return res;
    }
}
