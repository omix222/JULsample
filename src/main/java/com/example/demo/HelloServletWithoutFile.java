package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.*;

@WebServlet(name = "hello-servlet-nonfile", value = "/hello-servlet-nonfile")
public class HelloServletWithoutFile extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! hello-servlet-nonfile";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ロガーを取得してログレベルをINFOに設定
        Logger logger = Logger.getLogger(HelloServletWithoutFile.class.getName());
        logger.setLevel(Level.INFO);

        // ハンドラーを作成してロガーに登録
        Handler handler = new FileHandler("/tmp/sample.log");
        logger.addHandler(handler);

        // フォーマッターを作成してハンドラーに登録
        Formatter formatter =  new SimpleFormatter();
        handler.setFormatter(formatter);

        // INFOメッセージを出力
        logger.info("Infoinfo");
        logger.log(Level.INFO, "INFOメッセージ");
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