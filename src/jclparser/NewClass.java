package jclparser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jclparser.model.CARD;
import jclparser.model.CARD_IKJEFT01;
import jclparser.model.CARD_SORT;
import jclparser.model.DSN;
import jclparser.model.PROC;
import jclparser.model.STEP;
import static jclparser.parser.Parser.parse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import static jclparser.parser.Parser.parse;
import static jclparser.parser.Parser.parse;
import static jclparser.parser.Parser.parse;

/**
 *
 * @author elialva
 */
public class NewClass {

    public static File analyze(File f) {

        String procedure = StringUtils.substringBeforeLast(f.getName(), ".");

        if (f.exists()) {
            f.delete();
        }

        System.out.println("gepeto args2 = " + procedure);

        String tmp;

        PROC proc = parse(PROC.class, "SYS1.VIDA.PROCLIB", procedure);

        List<STEP> steps = proc.getListSTEP();

        List<String> nodes = new ArrayList<>();
        List<String> fromsTos = new ArrayList<>();

        String nodeTemplate = "{\"key\": \"%s\", \"category\": \"program\", \"color\": \"lightblue\", \"label\": \"%s\", \"num\": \"%s\", \"card\": \"%s\"},";
        String dsnTemplate = "{\"key\": \"%s\", \"color\": \"magenta\", \"label\": \"%s\"},";
        String fromToTemplate = "{\"from\": \"%s\", \"to\": \"%s\"},";

        Map<String, Integer> programs = new HashMap<>();
        Set<String> files = new HashSet<>();

        for (STEP step : steps) {
            String pgm = step.getProgram().getName();
            String xxx = "";

            CARD card = step.getProgram().getCard();
            if (card != null) {
                if (card instanceof CARD_IKJEFT01) {
                    pgm = ((CARD_IKJEFT01) card).getProgram();
                }

                xxx = step.getProgram().getCard().toString();

                System.out.println("VVV " + step.getProgram().getCard().toString());
//                if (card instanceof CARD_SORT) {
//                    pgm = ((CARD_SORT) card).getProgram();
//                }
            }

            Integer num = programs.get(pgm);
            num = num == null ? 1 : num + 1;
            programs.put(pgm, num);

            if (num > 1) {
                pgm = pgm + " (" + num + ")";
            }

            String t = String.format(nodeTemplate,
                    pgm,
                    "XXX",
                    "XXX",
                    xxx
            );

            nodes.add(t);

            List<DSN> dsns = step.getListDSN();
            for (DSN dsn : dsns) {
                if (dsn.getName().trim().isEmpty()) {
                    continue;
                }

                if (files.add(dsn.getName())) {

                    String u = String.format(dsnTemplate,
                            dsn.getName(),
                            dsn.getLabel());
                    files.add(nodeTemplate);
                    nodes.add(u);
                }

                String from = dsn.getMode().equals("INPUT") ? dsn.getName() : pgm;
                String to = !dsn.getMode().equals("INPUT") ? dsn.getName() : pgm;
                tmp = String.format(fromToTemplate, from, to);

                if (!pgm.equalsIgnoreCase("COPIADOR")) {
                    fromsTos.add(tmp);
                }

                System.out.println("Label: "
                        + StringUtils.rightPad(dsn.getLabel(), 8)
                        + " Dsn: " + dsn.getName()
                        + " Type: " + dsn.getType()
                        + " Mode: " + dsn.getMode());
            }
        }

        String lastNode = nodes.remove(nodes.size() - 1);
        lastNode = StringUtils.removeEnd(lastNode, ",");
        nodes.add(lastNode);
        String lastFromsTos = fromsTos.remove(fromsTos.size() - 1);
        lastFromsTos = StringUtils.removeEnd(lastFromsTos, ",");
        fromsTos.add(lastFromsTos);

        try {
            FileUtils.writeStringToFile(f, "{\"class\": \"go.GraphLinksModel\",\"nodeDataArray\": [", true);
            FileUtils.writeLines(f, nodes, true);
            FileUtils.writeStringToFile(f, "],", true);
            FileUtils.writeStringToFile(f, "\"linkDataArray\": [", true);
            FileUtils.writeLines(f, fromsTos, true);
            FileUtils.writeStringToFile(f, "]}", true);
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("directoru " + f.getAbsolutePath());

//        for (String node : nodes) {
//            System.out.println(node + ",");
//        }
//
//        for (String fromTo : fromsTos) {
//            System.out.println(fromTo + ",");
//        }
        return f;
    }

    public static void main(String[] args) {

    }

}
