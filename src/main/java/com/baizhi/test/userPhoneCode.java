package com.baizhi.test;

import com.baizhi.util.PhoneCodeUtil;

public class userPhoneCode {

    public static void main(String[] args) {

        String phoneMsg = PhoneCodeUtil.getPhoneMessage("13720990211");
        System.out.println(phoneMsg);

    }
}
