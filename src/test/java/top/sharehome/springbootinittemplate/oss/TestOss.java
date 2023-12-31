package top.sharehome.springbootinittemplate.oss;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.utils.oss.ali.AliUtils;
import top.sharehome.springbootinittemplate.utils.oss.minio.MinioUtils;
import top.sharehome.springbootinittemplate.utils.oss.tencent.TencentUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试对象存储类
 *
 * @author AntonyCheng
 */
@SpringBootTest
public class TestOss {

    /**
     * 测试腾讯云COS工具类——上传
     */
    @Test
    void testTencentUtilsUpload() throws IOException {
        File file = new File("README.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        System.out.println(TencentUtils.upload(multipartFile, "test/init"));
    }

    /**
     * 测试腾讯云COS工具类——删除
     */
    @Test
    void testTencentUtilsDelete() {
        TencentUtils.delete("https://digitizeeducation-1306588126.cos.ap-beijing.myqcloud.com/test/init/f1ebb673159845c8bb8eadce49c8b93a_README.md");
    }

    /**
     * 测试阿里云OSS工具类——上传
     */
    @Test
    void testAliUtilsUpload() throws IOException {
        File file = new File("README.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        System.out.println(AliUtils.upload(multipartFile, "test/init"));
    }

    /**
     * 测试阿里云OSS工具类——删除
     */
    @Test
    void testAliUtilsDelete() {
        AliUtils.delete("https://antonychengtest.oss-cn-beijing.aliyuncs.com/test/init/33ce4679377b48e9a733d95deaf43975_README.md");
    }

    /**
     * 测试MinIO工具类——上传
     */
    @Test
    void testMinioUtilsUpload() throws IOException {
        File file = new File("README.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
        System.out.println(MinioUtils.upload(multipartFile, "test/init"));
    }

    /**
     * 测试MinIO工具类——删除
     */
    @Test
    void testMinioUtilsDelete() {
        MinioUtils.delete("http://192.168.116.100:39000/xxxxxxxx/test/init/d9f1378cd82a484eb7060fdd48099fd8_README.md");
    }

}