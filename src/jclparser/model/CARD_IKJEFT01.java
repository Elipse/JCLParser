/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author elialva
 */
public class CARD_IKJEFT01 extends CARD {

    private String program;
    private String plan;

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    @Override
    public void parse() {
        System.out.println("Parsing..." + this);
        List<String> text = member.getText();
        String pgm = "";

        for (String line : text) {
            Matcher m = Pattern.compile("PROGRAM\\(\\w+\\)").matcher(line);

            if (m.find()) {
                program = StringUtils.substringBetween(m.group(), "(", ")");
            }

            m = Pattern.compile("PLAN\\(\\w+\\)").matcher(line);

            if (m.find()) {
                plan = StringUtils.substringBetween(m.group(), "(", ")");
            }
        }
    }
}
