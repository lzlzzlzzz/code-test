package com.code.system.domain;

import java.util.Date;

import com.code.common.pojo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * 用户信息对象 system_user_account
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemUserAccount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 帐号状态0正常、1停用
     */
    private Integer status;

    /**
     * 删除标识
     */
    private Integer deleteFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

}
