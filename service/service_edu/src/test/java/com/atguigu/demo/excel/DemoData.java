package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-23-上午9:33
 */
@Data
public class DemoData {
    //设置Excel表头名称
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;

}
