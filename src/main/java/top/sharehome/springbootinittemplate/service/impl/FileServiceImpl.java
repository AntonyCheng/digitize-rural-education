package top.sharehome.springbootinittemplate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.common.base.ReturnCode;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeReturnException;
import top.sharehome.springbootinittemplate.exception.customize.CustomizeTransactionException;
import top.sharehome.springbootinittemplate.mapper.FileMapper;
import top.sharehome.springbootinittemplate.model.entity.File;
import top.sharehome.springbootinittemplate.service.FileService;
import top.sharehome.springbootinittemplate.utils.oss.tencent.TencentUtils;

import javax.annotation.Resource;

/**
 * 文件服务实现类
 *
 * @author AntonyCheng
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public void upload(MultipartFile multipartFile, String fileType, Long userId) {
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
        int insert = fileMapper.insert(file);
        if (insert == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
    }

    @Override
    @Transactional(rollbackFor = CustomizeTransactionException.class)
    public void deleteFileById(Long fileId, Long userId) {
        File fileInDatabase = fileMapper.selectById(fileId);
        if (ObjectUtils.notEqual(userId, fileInDatabase.getUserId())) {
            throw new CustomizeReturnException(ReturnCode.ACCESS_UNAUTHORIZED);
        }
        int deleteResult = fileMapper.deleteById(fileId);
        if (deleteResult == 0) {
            throw new CustomizeReturnException(ReturnCode.ERRORS_OCCURRED_IN_THE_DATABASE_SERVICE);
        }
        String fileUrl = fileInDatabase.getUrl();
        TencentUtils.delete(fileUrl);
    }
}
