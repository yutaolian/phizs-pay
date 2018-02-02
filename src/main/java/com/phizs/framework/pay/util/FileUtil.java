/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/yemast/yemaframework">JeeSite</a> All rights reserved.
 */
package com.phizs.framework.pay.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author lianyutao
 * @根据path获得内容
 * @date 2018/1/18 16:38
 * @since 2.1.0
 */
public class FileUtil {

    private static final String encoding = "utf-8";

    /**
     * 获取字符串
     *
     * @param location
     * @return
     */
    public static String getText(String location) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource resource = patternResolver.getResource(location);
        InputStream in = null;
        try {
            in = resource.getInputStream();
            return IOUtils.toString(in, encoding);
        } catch (Exception e) {
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 获取输入流
     *
     * @param location
     * @return
     * @throws
     */
    public static InputStream getInputStream(String location) {
        FileInputStream inputStream= null;
        try {
            File file = new File(location);
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}