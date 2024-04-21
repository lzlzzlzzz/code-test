package com.code.myweb.domain;

import java.util.Date;

import com.code.common.pojo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户信息对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyAccount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "电话号码不能为空")
    private Long number;


    @Max(value = 5000, message = "预算价格不能超过{value}元")
    @Min(value = 1000, message = "价格预算不能低于{value}元")
    private double price;

    @NotBlank(message = "品牌不能为空")
    @Length(max = 5, message = "品牌长度不能超过{max}")
    private String brand;
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

}
