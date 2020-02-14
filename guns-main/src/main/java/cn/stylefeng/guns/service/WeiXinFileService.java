package cn.stylefeng.guns.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class WeiXinFileService {


    public Boolean importFile(String pathName, String fileName) {
        File file = new File(String.format("%s%s", pathName, fileName));
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = 1;
        String temp = null;
        try {
            while ((temp = bufferedReader.readLine()) != null) {
                if (1 == line) {
                    //微信汇总 第一行
                    String[] split = temp.split(",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
