package cn.fast.auth.util;

import cn.fast.web.common.result.Result;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {

    public static void out(HttpServletResponse response, Result result) {
        PrintWriter out = null;
        Gson gson = new Gson();
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(new Gson().toJson(result));
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
