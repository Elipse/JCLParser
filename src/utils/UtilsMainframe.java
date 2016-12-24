/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jclparser.model.MEMBER;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author elialva
 */
public class UtilsMainframe {

    public static Map<String, MEMBER> filesToMembers(Collection<File> listFiles, String pdsName, Class claze) {

        Map<String, MEMBER> pds = new HashMap<>();

        for (File listFile : listFiles) {

            try {

                MEMBER m = (MEMBER) claze.newInstance();
                pds.put(listFile.getName(), m);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(UtilsMainframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pds;
    }

    static String PATH = "C:\\Users\\IBM_ADMIN\\Documents\\@GNP\\201612 JCL_DFD\\BIBS";
    static String CARDS = "C:\\Users\\IBM_ADMIN\\Documents\\@GNP\\201612 JCL_DFD\\BIBS\\ERPL4CAR";

    public static MEMBER readMember(String pds, String name) {

        File f = new File(CARDS + "\\" + name);

        if (pds.equalsIgnoreCase("SYS1.VIDA.PROCLIB")) {
            f = new File(PATH + "\\PROCLIB" + "\\" + name);
        }

        MEMBER member = null;

        try {
            List<String> text = FileUtils.readLines(f);
            member = new MEMBER(pds, name, text);
        } catch (IOException ex) {
            Logger.getLogger(UtilsMainframe.class.getName()).log(Level.SEVERE, null, ex);
        }

        return member;
    }

    public static MEMBER readMember(String dsn) {
        System.out.println("dsn " + dsn);
        Matcher m = Pattern.compile("\\(\\w+\\)").matcher(dsn);
        if (m.find()) {
            String pds = StringUtils.substringBefore(dsn, "(");
            String mmbr = StringUtils.substringBetween(m.group(), "(", ")");
            System.out.println("pdsd sd " + pds + " mmbr " + mmbr);
            MEMBER member = readMember(pds, mmbr);
            return member;
        }
        return new MEMBER();
    }
}
