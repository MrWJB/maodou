package com.jbw.maodou.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangjibin
 * @since 2021-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String age;

    private String sex;

    private String birth;

    private String address;

    private String createdate;

    private String updatedate;

    private Integer deptid;


}
