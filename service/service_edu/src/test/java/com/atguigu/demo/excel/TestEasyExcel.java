package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-23-上午9:34
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写的操作
        //1.设置写入文件夹的地址和excel文件名称
        String filename = "a.xlsx";
        //2.调用方法
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    public static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("sb"+i);
            list.add(demoData);
        }
        return list;
    }
}
