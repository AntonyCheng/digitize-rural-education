package top.sharehome.springbootinittemplate.model.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文件分页Dto类
 *
 * @author AntonyCheng
 * @since 2023/12/6 22:13:58
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilePageDto implements Serializable {

    private static final long serialVersionUID = -2165108330636419018L;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件后缀（不含小数点）
     */
    private String suffix;

    /**
     * 文件模块类型（share：共享资源，fourC：4C微课，customize：定制微课；avatar：用户头像）
     */
    private String type;

    /**
     * 文件大小下限（单位：MB）
     */
    private Long minSize;

    /**
     * 文件大小上限（单位：MB）
     */
    private Long maxSize;

    /**
     * 文件所属用户的账号
     */
    private String userAccount;

}