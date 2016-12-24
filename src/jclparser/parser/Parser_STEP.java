/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jclparser.model.CARD;
import jclparser.model.CARD_IKJEFT01;
import jclparser.model.CARD_SORT;
import jclparser.model.DSN;
import jclparser.model.PGM;
import jclparser.model.STEP;

/**
 *
 * @author elialva
 */
public class Parser_STEP {

    private static final Map<String, String> MAP = new HashMap<>();
    private static final Map<String, Class> TYPE = new HashMap<>();

    static {
        MAP.put("SORT", "SYSIN");
        MAP.put("IKJEFT01", "SYSTSIN");
        TYPE.put("SORT", CARD_SORT.class);
        TYPE.put("IKJEFT01", CARD_IKJEFT01.class);
    }

    //Se da por hecho que no contiene comentarios ?! y esta aplanado ?!
    public static STEP parse(List<String> text) {

        String programName = "";
        List<String> listDsnName = new ArrayList<>();
        List<String> listDsnMode = new ArrayList<>();

        String label = "";
        String statement = "EXEC";

        PGM program = null;
        List<DSN> listDSN = new ArrayList<>();

        for (String line : text) {

            //Si hay corte de etiqueta hay que registrar un PGM o un DD
            String tmp = line.substring(2, 10).trim();
            if (!tmp.isEmpty()) {
                switch (statement) {
                    case "EXEC":
                        program = new PGM();
                        program.setName(programName);
                        break;
                    case "DD":
                        for (int i = 0; i < listDsnName.size(); i++) {
                            DSN dataset = new DSN();
                            dataset.setLabel(label);
                            dataset.setName(listDsnName.get(i));
                            dataset.setMode(listDsnMode.get(i));
                            dataset.setType("");

                            String dd = MAP.get(program.getName());
                            if (dd != null && dd.equals(dataset.getLabel())) {
                                Class clas = TYPE.get(program.getName());
                                CARD card = Parser.parse(clas, dataset.getName());
                                program.setCard(card);
                                dataset.setType(clas.getSimpleName());
                            } else {
                                listDSN.add(dataset);
                            }
                        }
                        listDsnName.clear();
                        listDsnMode.clear();
                        break;
                    default:
                }
                label = tmp;
            }

            Matcher m = Pattern.compile("EXEC\\s+\\w*=\\w+[\\s\\r\\,]").matcher(line);
            if (m.find()) {
                statement = "EXEC";
            }

            m = Pattern.compile("PGM=\\w+").matcher(line);
            programName = m.find() ? m.group().substring(4) : "**ERROR**";

            //Al corte de etiqueta
            if (line.contains(" DD ")) {
                statement = "DD";
            }

            m = Pattern.compile("DSN=[\\w\\.\\(\\+\\)]+").matcher(line);
            if (m.find()) {
                String dsnName = m.group().substring(4);
                listDsnName.add(dsnName);
            }

            if (line.contains("DISP=")) {
                String mode;
                if (line.contains("DISP=(,") || line.contains("DISP=(NEW")) {
                    mode = "OUTPUT";
                } else {
                    mode = "INPUT";
                }
                listDsnMode.add(mode);
            }
        }

        if (listDsnName.size() > 0) {
            DSN dataset = new DSN();
            dataset.setLabel(label);
            dataset.setName(listDsnName.get(listDsnName.size() - 1));
            dataset.setMode(listDsnMode.get(listDsnName.size() - 1));
            dataset.setType("");

            String dd = MAP.get(program.getName());
            System.out.println("dd###    " + dd);
            System.out.println("dataset# " + dataset.getLabel());
            if (dd != null && dd.equals(dataset.getLabel())) {
                Class clas = TYPE.get(program.getName());
                CARD card = Parser.parse(clas, dataset.getName());
                program.setCard(card);
                dataset.setType(clas.getSimpleName());
            } else {
                listDSN.add(dataset);
            }
        }

        STEP step = new STEP();
        step.setText(text);
        step.setProgram(program);
        step.setListDSN(listDSN);
        return step;
    }

    private static void createDsn() {

    }
}
