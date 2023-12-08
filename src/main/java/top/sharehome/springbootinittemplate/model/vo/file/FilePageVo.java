package top.sharehome.springbootinittemplate.model.vo.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件分页Vo类
 *
 * @author AntonyCheng
 * @since 2023/12/6 22:12:20
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilePageVo implements Serializable {

    private static final long serialVersionUID = 2870300955292715572L;

    /**
     * 文件ID
     */
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件模块类型（share：共享资源，fourC：4C微课，customize：定制微课；avatar：用户头像）
     */
    private String type;

    /**
     * 文件大小（单位：MB）
     */
    private Long size;

    /**
     * 文件存储地址
     */
    private String url;

    /**
     * 文件状态（0：启用，1：禁用）
     */
    private Integer status;

    /**
     * 文件所属用户的ID
     */
    private Long userId;

    /**
     * 文件所属用户的账号
     */
    private String userAccount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}