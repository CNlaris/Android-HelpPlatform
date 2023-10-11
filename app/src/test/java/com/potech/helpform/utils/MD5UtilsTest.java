package com.potech.helpform.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MD5UtilsTest {
    @Test
    public void Test() {
        String str = "sdaasdasdwqeqweq";
        String md5str = MD5Utils.getMD5String(str);
        System.out.println(md5str);
    }

}