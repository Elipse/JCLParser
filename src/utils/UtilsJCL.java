/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import jclparser.model.MEMBER;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author elialva
 */
public class UtilsJCL {

    public static List<MEMBER> find(List<MEMBER> list, String query) {

        List<MEMBER> result = new ArrayList();

        outer:
        for (MEMBER member : list) {
            List<String> content = member.getText();
            inner:
            for (String line : content) {
                if (line.contains(query)) {
                    result.add(member);
                    break;
                }
            }
        }

        return result;
    }

//    public static List<Member> find(List<Member> list, MEMBER query) {
//        List<Member> result = new ArrayList();
//
//        outer:
//        for (MEMBER member : list) {
//            List<String> content = member.getText();
//            inner:
//            for (String line : content) {
//                if (line.contains(query.getName())) {
//                    result.add(member);
//                    break;
//                }
//            }
//        }
//
//        return result;
//    }
    public static List<String> plain(List<String> readLines) {

        List<String> jcl = new ArrayList<>();
        String line = "";
        for (String readLine : readLines) {
            readLine = readLine.length() >= 72 ? readLine.substring(0, 72).trim() : readLine.trim();

            if (readLine.startsWith("//*")) {
                continue;
            }

            if (line.isEmpty()) {
                line += readLine;
            } else {
                /* elimina el // de las líneas de continuación */
                line += readLine.replaceFirst("//", "").trim();
            }

            if (!readLine.endsWith(",")) {
                jcl.add(line);
                line = "";
            }
        }
        return jcl;
    }

    public static void process(MEMBER get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
