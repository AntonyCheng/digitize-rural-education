package top.sharehome.springbootinittemplate.model.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import top.sharehome.springbootinittemplate.common.validate.PostGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文件上传Dto类
 *
 * @author AntonyCheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileUploadDto implements Serializable {

    private static final long serialVersionUID = -411958754351121818L;

    /**
     * 文件体
     */
    @NotNull(message = "上传文件不能为空", groups = {PostGroup.class})
    private MultipartFile multipartFile;

    /**
     * 文件模块类型（share：共享资源，fourC：4C微课，customize：定制微课；avatar：用户头像）
     */
    @NotEmpty(message = "上传文件所属模块不能为空", groups = {PostGroup.class})
    private String fileType;

}