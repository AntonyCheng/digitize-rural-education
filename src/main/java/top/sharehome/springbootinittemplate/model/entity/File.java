package top.sharehome.springbootinittemplate.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件类
 *
 * @author AntonyCheng
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_file")
public class File implements Serializable {

    /**
     * 文件ID
     */
    @TableId(value = "file_id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件名称
     */
    @TableField(value = "file_name")
    private String name;

    /**
     * 文件后缀（不含小数点）
     */
    @TableField(value = "file_suffix")
    private String suffix;

    /**
     * 文件模块类型（share：共享资源，fourC：4C微课，customize：定制微课；）
     */
    @TableField(value = "file_type")
    private String type;

    /**
     * 文件大小（单位：比特）
     */
    @TableField(value = "file_size")
    private Long size;

    /**
     * 文件存储地址
     */
    @TableField(value = "file_url")
    private String url;

    /**
     * 文件所属用户的ID
     */
    @TableField(value = "file_user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除（0表示未删除，1表示已删除）
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = -1065008178778413431L;

    public static final String COL_FILE_ID = "file_id";

    public static final String COL_FILE_NAME = "file_name";

    public static final String COL_FILE_SUFFIX = "file_suffix";

    public static final String COL_FILE_TYPE = "file_type";

    public static final String COL_FILE_SIZE = "file_size";

    public static final String COL_FILE_URL = "file_url";

    public static final String COL_FILE_USER_ID = "file_user_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETED = "is_deleted";

}