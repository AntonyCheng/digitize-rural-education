package top.sharehome.springbootinittemplate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sharehome.springbootinittemplate.service.FileService;

import javax.annotation.Resource;

/**
 * 文件控制器
 *
 * @author AntonyCheng
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

}
