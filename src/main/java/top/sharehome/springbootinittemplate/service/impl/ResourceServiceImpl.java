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
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAddDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceAdminPageDto;
import top.sharehome.springbootinittemplate.model.dto.resource.ResourceUserPageDto;
import top.sharehome.springbootinittemplate.model.entity.File;
import top.sharehome.springbootinittemplate.model.entity.Resource;
import top.sharehome.springbootinittemplate.model.entity.User;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceAdminPageVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceDetailVo;
import top.sharehome.springbootinittemplate.model.vo.recource.ResourceUserPageVo;
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

    /**
     * 2^10
     */
    private static final long TWO_10 = 1024;

    /**
     * 2^20
     */
    private static final long TWO_20 = 1024 * 1024;

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
    public Page<ResourceAdminPageVo> pageAdminResource(ResourceAdminPageDto resourceAdminPageDto, String fileType) {
        Page<Resource> page = new Page<>(resourceAdminPageDto.getPage(), resourceAdminPageDto.getSize());
        Page<ResourceAdminPageVo> res = new Page<>(resourceAdminPageDto.getPage(), resourceAdminPageDto.getPage());

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceAdminPageDto.getTitle()), Resource::getTitle, resourceAdminPageDto.getTitle());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceAdminPageDto.getInfo()), Resource::getInfo, resourceAdminPageDto.getInfo());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceAdminPageDto.getFileType()), Resource::getFileType, resourceAdminPageDto.getFileType());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getAccount, resourceAdminPageDto.getUserAccount());
        List<Long> userIdList = userMapper.selectList(userLambdaQueryWrapper).stream().map(User::getId).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(resourceAdminPageDto.getUserAccount()) && userIdList.isEmpty()) {
            return new Page<>(resourceAdminPageDto.getPage(), resourceAdminPageDto.getSize());
        }
        queryWrapper.lambda().in(!userIdList.isEmpty(), Resource::getUserId, userIdList);
        queryWrapper.lambda().eq(Resource::getFileType, fileType);

        resourceMapper.selectPage(page, queryWrapper);
        List<ResourceAdminPageVo> resourceAdminPageVoList = page.getRecords().stream().map(resource -> {
            ResourceAdminPageVo resourceAdminPageVo = new ResourceAdminPageVo();
            String userAccount = userMapper.selectAccountById(resource.getUserId());
            resourceAdminPageVo.setUserAccount(userAccount);
            File file = fileMapper.selectById(resource.getFileId());
            resourceAdminPageVo.setFileName(file.getName());
            resourceAdminPageVo.setFileUrl(file.getUrl());
            BeanUtils.copyProperties(resource, resourceAdminPageVo);
            return resourceAdminPageVo;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(page, res, "records");
        res.setRecords(resourceAdminPageVoList);
        return res;
    }

    @Override
    public Page<ResourceUserPageVo> pageUserResource(ResourceUserPageDto resourceUserPageDto, String fileType) {
        Page<Resource> page = new Page<>(resourceUserPageDto.getPage(), resourceUserPageDto.getSize());
        Page<ResourceUserPageVo> res = new Page<>(resourceUserPageDto.getPage(), resourceUserPageDto.getPage());

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceUserPageDto.getTitle()), Resource::getTitle, resourceUserPageDto.getTitle());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceUserPageDto.getInfo()), Resource::getInfo, resourceUserPageDto.getInfo());
        queryWrapper.lambda().like(StringUtils.isNotEmpty(resourceUserPageDto.getFileType()), Resource::getFileType, resourceUserPageDto.getFileType());
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getAccount, resourceUserPageDto.getUserAccount());
        List<Long> userIdList = userMapper.selectList(userLambdaQueryWrapper).stream().map(User::getId).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(resourceUserPageDto.getUserAccount()) && userIdList.isEmpty()) {
            return new Page<>(resourceUserPageDto.getPage(), resourceUserPageDto.getSize());
        }
        queryWrapper.lambda().in(!userIdList.isEmpty(), Resource::getUserId, userIdList);
        queryWrapper.lambda().eq(Resource::getFileType, fileType);

        resourceMapper.selectPage(page, queryWrapper);
        List<ResourceUserPageVo> resourceUserPageVoList = page.getRecords().stream().map(resource -> {
            ResourceUserPageVo resourceUserPageVo = new ResourceUserPageVo();
            String userAccount = userMapper.selectAccountById(resource.getUserId());
            resourceUserPageVo.setUserAccount(userAccount);
            BeanUtils.copyProperties(resource, resourceUserPageVo);
            return resourceUserPageVo;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(page, res, "records");
        res.setRecords(resourceUserPageVoList);
        return res;
    }

    @Override
    public ResourceDetailVo detailById(Long id) {
        ResourceDetailVo resourceDetailVo = new ResourceDetailVo();
        Resource resource = resourceMapper.selectById(id);
        User user = userMapper.selectById(resource.getUserId());
        File file = fileMapper.selectById(resource.getFileId());
        resourceDetailVo.setId(resource.getId());
        resourceDetailVo.setTitle(resource.getTitle());
        resourceDetailVo.setInfo(resource.getInfo());
        resourceDetailVo.setUserId(user.getId());
        resourceDetailVo.setUserAccount(user.getAccount());
        resourceDetailVo.setUserAvatar(user.getAvatar());
        resourceDetailVo.setFileId(file.getId());
        resourceDetailVo.setFileName(file.getName() + "." + file.getSuffix());
        resourceDetailVo.setFileUrl(file.getUrl());
        String fileSize = "";
        if (file.getSize() < TWO_10) {
            fileSize = file.getSize() + ModelConstant.FILE_UNIT_B;
        } else if (file.getSize() < TWO_20 * 10) {
            fileSize = (file.getSize() / TWO_10) + ModelConstant.FILE_UNIT_KB;
        } else {
            fileSize = (file.getSize() / TWO_20) + ModelConstant.FILE_UNIT_MB;
        }
        resourceDetailVo.setFileSize(fileSize);
        resourceDetailVo.setCreateTime(resource.getCreateTime());
        return resourceDetailVo;
    }

}
