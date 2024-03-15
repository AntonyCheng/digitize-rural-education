package top.sharehome.springbootinittemplate.model.vo.recource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资源详情Vo类
 *
 * @author AntonyCheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResourceDetailVo implements Serializable {

    private static final long serialVersionUID = 6549735237763810446L;

    /**
     * 资源ID
     */
    private Long id;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 资源简介
     */
    private String info;

    /**
     * 资源所属用户ID
     */
    private Long userId;

    /**
     * 资源所属用户的账号
     */
    private String userAccount;

    /**
     * 资源所属用户的头像
     */
    private String userAvatar;

    /**
     * 资源对应文件ID
     */
    private Long fileId;

    /**
     * 资源对应文件名称
     */
    private String fileName;

    /**
     * 资源对应文件URL
     */
    private String fileUrl;

    /**
     * 资源对应文件大小
     */
    private String fileSize;

    /**
     * 资源发布时间
     */
    private LocalDateTime createTime;

}
