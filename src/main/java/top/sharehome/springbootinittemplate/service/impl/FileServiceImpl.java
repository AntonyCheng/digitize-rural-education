package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeTransactionException;
import top.sharehome.springbootinittemplate.mapper.FileMapper;
import top.sharehome.springbootinittemplate.mapper.UserMapper;
import top.sharehome.springbootinittemplate.model.ModelConstant;
import top.sharehome.springbootinittemplate.model.dto.file.FilePageDto;
import top.sharehome.springbootinittemplate.model.entity.File;
import top.sharehome.springbootinittemplate.model.entity.PageModel;
import top.sharehome.springbootinittemplate.model.entity.User;
import top.sharehome.springbootinittemplate.model.vo.file.FilePageVo;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.oss.tencent.TencentUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文件服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private static final long TO_BYTE = 1024 * 1024;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public Long uploadFile(MultipartFile multipartFile, String fileType, Long userId) {
        long size = multipartFile.getSize();
        String fileName = StringUtils.isNotBlank(multipartFile.getOriginalFilename()) ? multipartFile.getOriginalFilename() : multipartFile.getName();
        String suffix = FilenameUtils.getExtension(fileName).toLowerCase();
        File file = new File()
                .setSize(size)
                .setName(fileName)
                .setSuffix(suffix)
                .setType(fileType)
                .setUserId(userId);
        String dataTime = new DateTime().toString("yyyy/MM/dd");
        String rootPath = fileType + "/" + suffix + "/" + dataTime;
        String url = TencentUtils.upload(multipartFile, rootPath);
        file.setUrl(url);
        file.setStatus(ModelConstant.STATUS_ON);
        int insertResult = fileMapper.insert(file);
        if (insertResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
        return file.getId();
    }

    @Override
    public Long uploadAvatar(MultipartFile multipartFile, Long userId) {
        LambdaQueryWrapper<File> fileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fileLambdaQueryWrapper.eq(File::getUserId, userId).eq(File::getType, ModelConstant.FILE_TYPE_AVATAR);
        File avatarInDatabase = fileMapper.selectOne(fileLambdaQueryWrapper);
        if (ObjectUtils.isNotEmpty(avatarInDatabase)) {
            String url = avatarInDatabase.getUrl();
            int deleteResult = fileMapper.deleteById(avatarInDatabase);
            if (deleteResult == 0) {
                throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
            }
            TencentUtils.delete(url);
        }
        long size = multipartFile.getSize();
        String fileName = StringUtils.isNotBlank(multipartFile.getOriginalFilename()) ? multipartFile.getOriginalFilename() : multipartFile.getName();
        String suffix = FilenameUtils.getExtension(fileName).toLowerCase();
        File file = new File()
                .setSize(size)
                .setName(fileName)
                .setSuffix(suffix)
                .setType(ModelConstant.FILE_TYPE_AVATAR)
                .setUserId(userId);
        String dataTime = new DateTime().toString("yyyy/MM/dd");
        String rootPath = ModelConstant.FILE_TYPE_AVATAR + "/" + suffix + "/" + dataTime;
        String url = TencentUtils.upload(multipartFile, rootPath);
        file.setUrl(url);
        file.setStatus(ModelConstant.STATUS_ON);
        int insertResult = fileMapper.insert(file);
        if (insertResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
        return file.getId();
    }

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public void deleteById(Long fileId) {
        File fileInDatabase = fileMapper.selectById(fileId);
        if (ObjectUtils.isEmpty(fileInDatabase)) {
            throw new CustomizeReturnException(ReturnCode.DATA_IS_NOT_AVAILABLE_IN_THE_DATABASE);
        }
        int deleteResult = fileMapper.deleteById(fileId);
        if (deleteResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
        String fileUrl = fileInDatabase.getUrl();
        TencentUtils.delete(fileUrl);
    }

    // todo 更新逻辑，再想想有没有更好的办法，不要使用查询后再筛选的方法，不然Page封装属性可能出现冲突
    @Override
    public Page<FilePageVo> pageFile(PageModel pageModel, FilePageDto filePageDto) {
        Page<File> page = new Page<>(pageModel.getPage(), pageModel.getSize());
        Page<FilePageVo> res = new Page<>(pageModel.getPage(), pageModel.getSize());

        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        // 处理文件名称（表中原有属性）查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(filePageDto.getName()), File::getName, filePageDto.getName());
        // 处理文件后缀（表中原有属性）查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(filePageDto.getSuffix()), File::getSuffix, filePageDto.getSuffix());
        // 处理文件模块类型（表中原有属性）查询条件
        queryWrapper.lambda().like(StringUtils.isNotEmpty(filePageDto.getType()), File::getType, filePageDto.getType());
        // 处理文件大小（表中原有属性）查询条件
        if (ObjectUtils.isNotEmpty(filePageDto.getMinSize()) || ObjectUtils.isNotEmpty(filePageDto.getMaxSize())) {
            Long minSize = filePageDto.getMinSize();
            if (ObjectUtils.isNotEmpty(minSize)) {
                minSize = minSize * TO_BYTE;
            } else {
                minSize = ModelConstant.FILE_MIN_SIZE;
            }
            Long maxSize = filePageDto.getMaxSize();
            if (ObjectUtils.isNotEmpty(maxSize)) {
                maxSize = maxSize * TO_BYTE;
            } else {
                maxSize = ModelConstant.FILE_MAX_SIZE;
            }
            queryWrapper.lambda().ge(File::getSize, minSize).le(File::getSize, maxSize);
        }
        // 处理文件状态（表中原有属性）查询条件
        queryWrapper.lambda().eq(ObjectUtils.isNotEmpty(filePageDto.getStatus()), File::getStatus, filePageDto.getStatus());
        // 先根据表中原有属性进行查询
        fileMapper.selectPage(page, queryWrapper);

        // 根据非表中原有属性进行筛选
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getAccount, filePageDto.getUserAccount());
        List<Long> userIdList = userMapper.selectList(userLambdaQueryWrapper).stream().map(User::getId).collect(Collectors.toList());
        if (userIdList.isEmpty()) {
            BeanUtils.copyProperties(page, res, "records");
            res.setRecords(new LinkedList<>());
            res.setTotal(0);
            return res;
        }
        List<FilePageVo> filePageVoList = page.getRecords().stream().map(file -> {
            if (!userIdList.contains(file.getUserId())) {
                return null;
            }
            FilePageVo filePageVo = new FilePageVo();
            BeanUtils.copyProperties(file, filePageVo);
            return filePageVo;
        }).filter(Objects::isNull).collect(Collectors.toList());

        BeanUtils.copyProperties(page, res, "records");
        res.setRecords(filePageVoList);
        return res;
    }

}