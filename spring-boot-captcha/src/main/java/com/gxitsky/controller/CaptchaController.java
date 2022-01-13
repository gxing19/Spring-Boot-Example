package com.gxitsky.controller;

import cn.hutool.captcha.ICaptcha;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaType;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gxing
 * @desc 验证码
 * @date 2022/1/13
 */
@RestController
public class CaptchaController {

    /**
     * @param request  请求
     * @param response 响应
     * @desc easyCaptcha 生成验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/easyCaptcha/get")
    public void getEasyCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 默认常规验证码,4位,
//        CaptchaUtil.out(request, response);

        int DEFAULT_LEN = 4;  // 默认长度
        int DEFAULT_WIDTH = 130;  // 默认宽度
        int DEFAULT_HEIGHT = 48;  // 默认高度
        // 数字和字母验证码
        Captcha captcha = new SpecCaptcha(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN);
        // 数字和字母验证码 GIF闪烁
//        Captcha captcha = new GifCaptcha(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN);
        // 算术验证码
//        Captcha captcha = new ArithmeticCaptcha(DEFAULT_WIDTH * 2, DEFAULT_HEIGHT * 2, DEFAULT_LEN);
        // 汉字验证码
//        Captcha captcha = new ChineseCaptcha(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN);
        // 汉字验证码 GIF闪烁
//        Captcha captcha = new ChineseGifCaptcha(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN);
        System.out.println(captcha.text().toLowerCase());
        CaptchaUtil.out(captcha, request, response);
    }

    /**
     * @param request 请求
     * @desc easyCaptcha 校验验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/easyCaptcha/verify")
    public String verifyEasyCaptcha(HttpServletRequest request, String verCode) throws IOException {
        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            throw new RuntimeException("验证码错误");
        }
        return "SUCCESS";
    }

    /**
     * @param request  请求
     * @param response 响应
     * @desc hutoolCaptcha 生成验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/hutoolCaptcha/get")
    public void hutoolCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
//        LineCaptcha lineCaptcha = cn.hutool.captcha.CaptchaUtil.createLineCaptcha(200, 100, 4, 15);
        ICaptcha lineCaptcha = cn.hutool.captcha.CaptchaUtil.createGifCaptcha(200, 100, 4);
        request.getSession().setAttribute("hutool-captcha", lineCaptcha.getCode());
        lineCaptcha.write(response.getOutputStream());
        System.out.println(lineCaptcha.getCode());

//        //图形验证码写出，可以写出到文件，也可以写出到流
//        lineCaptcha.write("d:/line.png");
//        //输出code
//        Console.log(lineCaptcha.getCode());
//        //验证图形验证码的有效性，返回boolean值
//        lineCaptcha.verify("1234");
//
//        //重新生成验证码
//        lineCaptcha.createCode();
//        lineCaptcha.write("d:/line.png");
//        //新的验证码
//        Console.log(lineCaptcha.getCode());
//        //验证图形验证码的有效性，返回boolean值
//        lineCaptcha.verify("1234");

    }

    /**
     * @param request 请求
     * @desc hutoolCaptcha 校验验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/hutoolCaptcha/verify")
    public String hutoolCaptcha(HttpServletRequest request, String verCode) {
        String code = (String) request.getSession().getAttribute("hutool-captcha");
        if (!code.equals(verCode)) {
            throw new RuntimeException("验证码错误");
        }
        return "SUCCESS";
    }

    /**
     * @param request  请求
     * @param response 响应
     * @desc happyCaptcha 生成验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/happyCaptcha/get")
    public void happyCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HappyCaptcha.require(request, response).build().finish();
//        HappyCaptcha.require(request, response).style(CaptchaStyle.ANIM).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.ARITHMETIC).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.ARITHMETIC_ZH).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.CHINESE).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.NUMBER).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.NUMBER_ZH_CN).build().finish();
//        HappyCaptcha.require(request, response).type(CaptchaType.NUMBER_ZH_HK).build().finish();
        HappyCaptcha.require(request, response).type(CaptchaType.WORD).build().finish();
        System.out.println(request.getSession().getAttribute(HappyCaptcha.SESSION_KEY));
    }

    /**
     * @param request 请求
     * @desc hutoolCaptcha 校验验证码
     * @author gxing
     * @date 2022/1/12 14:42
     */
    @RequestMapping("/happyCaptcha/verify")
    public String happyCaptcha(HttpServletRequest request, String verCode) {
        boolean verification = HappyCaptcha.verification(request, verCode, true);
        if (!verification) {
            throw new RuntimeException("验证码错误");
        }
        return "SUCCESS";
    }
}
