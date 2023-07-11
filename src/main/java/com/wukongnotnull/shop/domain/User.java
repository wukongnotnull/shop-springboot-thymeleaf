package com.wukongnotnull.shop.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long uId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0表示未删除，1表示已经删除
     */
    private Integer isDeleted;

    /**
     * 
     */
    private Integer version;

    /**
     * 
     */
    private Integer sex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uId=").append(uId);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", email=").append(email);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", version=").append(version);
        sb.append(", sex=").append(sex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}