package com.example.demo;

import java.io.*;
import java.util.logging.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream in = HelloServlet.class.getResourceAsStream("/logging-apl.properties");
        LogManager.getLogManager().readConfiguration(in);
        // ロガーを取得してログレベルをINFOに設定
        Logger logger = Logger.getLogger(HelloServlet.class.getName());
        //logger.setLevel(Level.INFO);
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