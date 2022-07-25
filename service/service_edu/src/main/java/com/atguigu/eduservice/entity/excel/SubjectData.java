package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-23-上午10:30
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubject;
    @ExcelProperty(index = 1)
    private String twoSubject;

}
