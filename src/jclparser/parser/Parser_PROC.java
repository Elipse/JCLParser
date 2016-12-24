/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.parser;

import java.util.ArrayList;
import java.util.List;
import jclparser.model.MEMBER;
import jclparser.model.PROC;
import jclparser.model.STEP;

/**
 *
 * @author elialva
 */
public class Parser_PROC {

    private static Parser_PROC ppr;

    public static Parser_PROC getInstance() {
        if (ppr == null) {
            return new Parser_PROC();
        }
        return ppr;
    }

    public static PROC parse(MEMBER member) {
        List<String> text = member.getText();

        List<String> step = new ArrayList<>();

        List<List<String>> listSTEP = new ArrayList<>();

        for (String line : text) {
            if (line.startsWith("//*") || line.trim().contains(" PROC")) {
                continue;
            }

            if (line.contains(" EXEC ")) {
                listSTEP.add(step);
                step = new ArrayList<>();
            }

            step.add(line);
        }

        listSTEP.add(step);
        listSTEP.remove(0);

        PROC proc = new PROC();
        List<STEP> steps = new ArrayList<>();

        proc.setListSTEP(steps);
        for (List<String> oneStep : listSTEP) {
            steps.add(Parser_STEP.parse(oneStep));
        }

        proc.setText(member.getText());
        proc.setListSTEP(steps);
        return proc;
    }
}
