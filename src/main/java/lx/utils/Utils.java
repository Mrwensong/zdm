package lx.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import lx.model.Crawlable;

public class Utils {

    public static String buildMessage(List<? extends Crawlable> zdms) {
        zdms.sort(Comparator.comparingInt(Crawlable::obtainSortOrder).reversed());

        StringBuilder s = new StringBuilder();
        s.append("<table border=\"1\">");
        s.append("<tr><th>图</th><th>标题</th><th>价格</th><th>赞/评</th><th>平台</th></tr>");
        zdms.forEach(z -> s.append(z.toHtmlTr()));
        s.append("</table>");
        return s.toString();
    }

    public static HashSet<String> readFile(String path) {
        HashSet<String> res = new HashSet<>();
        try (FileInputStream in = new FileInputStream(path);
             InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                res.add(lineTxt);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(path + "文件读取失败");
        }
        return res;
    }

    /**
     * 按行写入
     *
     * @param path
     * @param list
     */
    public static void write(String path, boolean append, List<String> list) {
        try(FileWriter fr =new FileWriter(path, append);
            BufferedWriter bw = new BufferedWriter(fr)) {
            for (String s : list) {
                bw.write(s);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
