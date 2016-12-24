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
public class PROC {

    private List<STEP> listSTEP;
    private List<String> text;
    private MEMBER member;

    /**
     * @return the listSTEP
     */
    public List<STEP> getListSTEP() {
        return listSTEP;
    }

    /**
     * @param listSTEP the listSTEP to set
     */
    public void setListSTEP(List<STEP> listSTEP) {
        this.listSTEP = listSTEP;
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
