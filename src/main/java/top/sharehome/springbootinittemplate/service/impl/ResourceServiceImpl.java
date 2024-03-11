package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeTransactionException;
import top.sharehome.springbootinittemplate.mapper.FileMapper;
import top.sharehome.springbootinittemplate.mapper.ResourceMapper;
import top.sharehome.springbootinittemplate.mapper.UserMapper;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourcePageDto;
import top.sharehome.springbootinittemplate.model.entity.File;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.model.entity.User;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourcePageVo;
import top.sharehome.springbootinittemplate.service.ResourceService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @javax.annotation.Resource
    private UserMapper userMapper;

    @javax.annotation.Resource
    private FileMapper fileMapper;

    @Override
    public void addResource(Long userId, ResourceAddDto resourceAddDto, String fileTypeShare) {
        Resource resource = new Resource()
                .setTitle(resourceAddDto.getTitle())
                .setInfo(resourceAddDto.getInfo())
                .setFileId(resourceAddDto.getFileId())
                .setUserId(userId)
                .setStatus(0)
                .setFileType(fileTypeShare);
        int insertResult = resourceMapper.insert(resource);
        if (insertResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = CustomizeTransactionException.class)
    public Page<ResourcePageVo> pageResource(ResourcePageDto resourcePageDto, String fileTypeShare) {
        Page<Resource> page = new Page<>(resourcePageDto.getPage(), resourcePageDto.getSize());
        Page<ResourcePageVo> res = new Page<>(resourcePageDto.getPage(), resourcePageDto.getPage());

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourcePageDto.getTitle()), Resource::getTitle, resourcePageDto.getTitle());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourcePageDto.getInfo()), Resource::getInfo, resourcePageDto.getInfo());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourcePageDto.getFileType()), Resource::getFileType, resourcePageDto.getFileType());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getAccount, resourcePageDto.getUserAccount());
        List<Long> userIdList = userMapper.selectList(userLambdaQueryWrapper).stream().map(User::getId).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(resourcePageDto.getUserAccount()) && userIdList.isEmpty()) {
            return new Page<>(resourcePageDto.getPage(), resourcePageDto.getSize());
        }
        queryWrapper.lambda().in(!userIdList.isEmpty(), Resource::getUserId, userIdList);
        queryWrapper.lambda().eq(Resource::getFileType, fileTypeShare);

        resourceMapper.selectPage(page, queryWrapper);
        List<ResourcePageVo> resourcePageVoList = page.getRecords().stream().map(resource -> {
            ResourcePageVo resourcePageVo = new ResourcePageVo();
            String userAccount = userMapper.selectAccountById(resource.getUserId());
            resourcePageVo.setUserAccount(userAccount);
            File file = fileMapper.selectById(resource.getFileId());
            resourcePageVo.setFileName(file.getName());
            BeanUtils.copyProperties(resource, resourcePageVo);
            return resourcePageVo;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(page, res, "records");
        res.setRecords(resourcePageVoList);
        return res;
    }

}
