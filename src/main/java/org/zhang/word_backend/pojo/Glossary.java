package org.zhang.word_backend.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Data
/*
 * className: glossary
 * description: 词单类
 * author: Zhang
 * date: 2025/2/24
 */
public class Glossary {
    /** 词单ID（自增主键） */
    private Integer glossaryId;

    /** 用户编号 */
    private Integer userId;

    /** 词单名称 */
    private String listName;

    /** 词单描述 */
    private String description;

    /** 是否公开（默认私有） */
    private Boolean isPublic;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    /** 分类ID */
    private Integer categoryId;

    /** 封面图片URL */
    private String coverUrl;

    /** 封面图片 */
    private String coverImageName;

}
