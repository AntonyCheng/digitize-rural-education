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
    String FILE_TYPE_SHARE = "share";
    String FILE_TYPE_FOURC = "fourC";
    String FILE_TYPE_CUSTOMIZE = "customize";
    String FILE_TYPE_AVATAR = "avatar";
    List<String> FILE_TYPE = new ArrayList<String>() {
        {
            // 资源共享
            add(FILE_TYPE_SHARE);
            // 4c
            add(FILE_TYPE_FOURC);
            // 定制化
            add(FILE_TYPE_CUSTOMIZE);
            // 用户头像
            add(FILE_TYPE_AVATAR);
        }
    };

    /**
     * 文件大小上下限（单位：比特）
     */
    Long FILE_MAX_SIZE = 600 * 1024 * 1024L;
    Long FILE_MIN_SIZE = 0L;

    /**
     * 文件大小单位
     */
    String FILE_UNIT_B = "B";
    String FILE_UNIT_KB = "KB";
    String FILE_UNIT_MB = "MB";

    /**
     * 启用/禁用状态
     */
    Integer STATUS_ON = 0;
    Integer STATUS_OFF = 1;
    List<Integer> STATUS = new ArrayList<Integer>() {
        {
            add(STATUS_ON);
            add(STATUS_OFF);
        }
    };

}