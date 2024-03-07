package top.sharehome.springbootinittemplate.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源类
 *
 * @author AntonyCheng
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_resource")
public class Resource implements Serializable {

    /**
     * 资源ID
     */
    @TableId(value = "resource_id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 资源标题
     */
    @TableField(value = "resource_title")
    private String title;

    /**
     * 资源简介
     */
    @TableField(value = "resource_info")
    private String info;

    /**
     * 资源对应文件ID
     */
    @TableField(value = "resource_file_id")
    private Long fileId;

    /**
     * 资源对应文件类型（share：共享资源，fourC：4C微课，avatar：用户头像）
     */
    @TableField(value = "resource_file_type")
    private String fileType;

    /**
     * 资源所属用户ID
     */
    @TableField(value = "resource_user_id")
    private Long userId;

    /**
     * 资源状态（0：启用，1：禁用）
     */
    @TableField(value = "resource_status")
    private Integer status;

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

    private static final long serialVersionUID = 2067179680308465468L;

    public static final String COL_RESOURCE_ID = "resource_id";

    public static final String COL_RESOURCE_TITLE = "resource_title";

    public static final String COL_RESOURCE_INFO = "resource_info";

    public static final String COL_RESOURCE_FILE_ID = "resource_file_id";

    public static final String COL_RESOURCE_FILE_TYPE = "resource_file_type";

    public static final String COL_RESOURCE_USER_ID = "resource_user_id";

    public static final String COL_RESOURCE_STATUS = "resource_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_IS_DELETED = "is_deleted";

}