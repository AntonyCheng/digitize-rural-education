package top.sharehome.springbootinittemplate.service.impl;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.mapper.FileMapper;
import top.sharehome.springbootinittemplate.model.entity.File;
import top.sharehome.springbootinittemplate.service.FileService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

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
    public String uploadFile(MultipartFile file,String fileType) {

        return null;
    }
}
