package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.*;

@WebServlet(name = "hello-servlet-with-RB", value = "/hello-servlet-with-Prop")
public class HelloServletWithProp extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! hello-servlet-with-Prop";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties properties = new Properties();
        try {
            InputStream in = HelloServlet.class.getResourceAsStream("/logging-apl.properties");
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // ロガーを取得してログレベルをINFOに設定
        Logger logger = Logger.getLogger(HelloServletWithProp.class.getName());
        logger.setLevel(Level.INFO);

        // ハンドラーを作成してロガーに登録
        Handler handler = new FileHandler(properties.getProperty("java.util.logging.FileHandler.pattern"));
        logger.addHandler(handler);

        // フォーマッターを作成してハンドラーに登録
        Formatter formatter =  new SimpleFormatter();
        System.setProperty("java.util.logging.SimpleFormatter.format",properties.getProperty("java.util.logging.SimpleFormatter.format"));
        handler.setFormatter(formatter);

        // INFOメッセージを出力
        logger.info("Infoinfo -with-RB");
        logger.log(Level.INFO, "INFOメッセージ-with-RB");
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}