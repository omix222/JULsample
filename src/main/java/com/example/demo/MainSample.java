package com.example.demo;

import java.util.logging.*;

/**
 * JUL の基本利用.
 */
public class MainSample {
    public static void main(String[] args) throws Exception{
        // ロガーを取得してログレベルをINFOに設定
        Logger logger = Logger.getLogger(MainSample.class.getName());
        logger.setLevel(Level.INFO);

        // ハンドラーを作成してロガーに登録
        Handler handler = new FileHandler("/tmp/StandAlone_sample.log");
        logger.addHandler(handler);

        // フォーマッターを作成してハンドラーに登録
        Formatter formatter =  new SimpleFormatter();
        handler.setFormatter(formatter);

        // INFOメッセージを出力
        logger.log(Level.INFO, "INFOメッセージ");
    }
}
