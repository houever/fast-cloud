package cn.fast.web.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;
/**
 *
 * 描述:RequestBodyAdvice:解释
 *  允许在将请求的主体读取和转换成一个对象之前对请求进行自定义，
 *  并允许在将其传递到控制器方法作为一个@RequestBody或HttpEntity方法参数之前处理结果对象。
 *  https://blog.csdn.net/niugang0920/article/details/80679096
 * @author houqj
 * @date 2019/7/24
 **/

/**
 * @author houqj
 * @date 2019-07-24 11:08
 */
@Slf4j
@ControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    class MyHttpInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;

        @SuppressWarnings("unchecked")
        public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
            String string = IOUtils.toString(inputMessage.getBody(), "UTF-8");
            Map<String, Object> mapJson = (Map<String, Object>) JSON.parseObject(string, Map.class);
            Map<String, Object> map = new HashMap<String, Object>();
            Set<Map.Entry<String, Object>> entrySet = mapJson.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object objValue = entry.getValue();
                if (objValue instanceof String) {
                    String value = objValue.toString();
                    map.put(key, filterDangerString(value));
                } else { // 针对结合的处理
                    @SuppressWarnings("rawtypes")
                    List<HashMap> parseArray = JSONArray.parseArray(objValue.toString(), HashMap.class);
                    List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
                    for (Map<String, Object> innerMap : parseArray) {
                        Map<String, Object> childrenMap = new HashMap<String, Object>();
                        Set<Map.Entry<String, Object>> elseEntrySet = innerMap.entrySet();
                        for (Map.Entry<String, Object> en : elseEntrySet) {
                            String innerKey = en.getKey();
                            Object innerObj = en.getValue();
                            if (innerObj instanceof String) {
                                String value = innerObj.toString();
                                childrenMap.put(innerKey, filterDangerString(value));
                            }


                        }
                        listMap.add(childrenMap);
                    }
                    map.put(key, listMap);
                }
            }
            this.headers = inputMessage.getHeaders();
            this.body = IOUtils.toInputStream(JSON.toJSONString(map), "UTF-8");
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
    private String filterDangerString(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("\\|", "");
        value = value.replaceAll("&", "");
        value = value.replaceAll(";", "");
        value = value.replaceAll("@", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("\\'", "");
        value = value.replaceAll("<", "");
        value = value.replaceAll("-", "");
        value = value.replaceAll(">", "");
        value = value.replaceAll("\\(", "");
        value = value.replaceAll("\\)", "");
        value = value.replaceAll("\\+", "");
        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "");
        value = value.replaceAll("script", "");
        value = value.replaceAll("select", "");
        value = value.replaceAll("\"", "");
        value = value.replaceAll(">", "");
        value = value.replaceAll("<", "");
        value = value.replaceAll("=", "");
        value = value.replaceAll("/", "");
        return value;
    }

}
