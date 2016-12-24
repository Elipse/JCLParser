/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import jclparser.model.CARD;
import jclparser.model.CARD_FTP;
import jclparser.model.CARD_IKJEFT01;
import jclparser.model.CARD_SORT;
import jclparser.model.MEMBER;
import jclparser.model.PROC;
import utils.UtilsMainframe;

/**
 *
 * @author elialva
 */
public class Parser {

    public static <T> T parse(Class<T> clas, String pdsName, String memberName) {

        MEMBER member = UtilsMainframe.readMember(pdsName, memberName);

//        if (clas == CARD_IKJEFT01.class) {
//            Parser_CARDRUN p = Parser_CARDRUN.getInstance();
//            return (T) Parser_CARDRUN.parse(member);
//        }
//
        if (clas == PROC.class) {
            Parser_PROC p = Parser_PROC.getInstance();
            return (T) Parser_PROC.parse(member);
        }
        return null;
    }

    public static <T extends CARD> CARD parse(Class<T> clas, String pdsName) {

        System.out.println("GODINEZ CHETOS");

        try {
            MEMBER member = UtilsMainframe.readMember(pdsName);
            CARD card = clas.newInstance();
            card.setMember(member);
            card.parse();
            return card;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
