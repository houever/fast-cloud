package cn.fast.admin.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author Administrator
 *  cn.fast.auth.oauth2server
 * @Description: 加密工具
 * @date 2018/4/13 16:42
 */
public class BCryptUtil {

    public static String encode(String str) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(str);
    }

    public static boolean isMatch(String passWord, String encodePassWord) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(passWord, encodePassWord);
    }

    //    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode=bCryptPasswordEncoder.encode("123456");
//        String encode1=bCryptPasswordEncoder.encode("mycloud");
//        System.out.println(encode);
//        System.out.println("bcrypt密码对比1:" + bCryptPasswordEncoder.matches("123456", encode));
//        System.out.println("bcrypt密码对比2:" + bCryptPasswordEncoder.matches("mycloud", encode1));
//        System.out.println("bcrypt密码对比2:" + bCryptPasswordEncoder.matches("123456", "$2a$10$fyROqUkAsODZuUMpEuDmVeRSZ43D7SsVU5geaJzreaaev6PK7SseC"));
//
//    }
    public static void main(String[] args) {
        //System.out.println(encode("mycloud"));
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        Optional<String> opt = Optional.ofNullable(map.get("2"));
        System.out.println(opt.isPresent());
        System.out.println(opt.toString());
    }
}
