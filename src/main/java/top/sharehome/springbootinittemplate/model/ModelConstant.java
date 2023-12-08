package top.sharehome.springbootinittemplate.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目中一些固定的常量值
 *
 * @author AntonyCheng
 * @since 2023/12/6 23:00
 */

public interface ModelConstant {

    /**
     * 文件模块类型
     */
    List<String> FILE_TYPE = new ArrayList<String>() {
        {
            // 资源共享
            add("share");
            // 4c
            add("fourC");
            // 定制化
            add("customize");
            // 用户头像
            add("avatar");
        }
    };

    /**
     * 文件大小上限（单位：比特）
     */
    Long FILE_MAX_SIZE = 600 * 1024 * 1024L;

    /**
     * 文件大小下限（单位：比特）
     */
    Long FILE_MIN_SIZE = 0L;

    /**
     * 启用/禁用状态
     */
    Integer ON = 0;
    Integer OFF = 1;

}
