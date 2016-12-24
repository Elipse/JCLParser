/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.model;

import java.util.List;

/**
 *
 * @author elialva
 */
public class STEP {

    private PGM program;
    private List<DSN> listDSN;
    private List<String> text;

    /**
     * @return the program
     */
    public PGM getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(PGM program) {
        this.program = program;
    }

    /**
     * @return the listDSN
     */
    public List<DSN> getListDSN() {
        return listDSN;
    }

    /**
     * @param listDSN the listDSN to set
     */
    public void setListDSN(List<DSN> listDSN) {
        this.listDSN = listDSN;
    }

    /**
     * @return the text
     */
    public List<String> getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(List<String> text) {
        this.text = text;
    }

}
