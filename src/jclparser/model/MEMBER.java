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
public class MEMBER {

    private List<String> text;
    private String pdsName;
    private String memberName;

    public MEMBER() {

    }

    public MEMBER(String pdsName, String memberName, List<String> text) {
        this.pdsName = pdsName;
        this.memberName = memberName;
        this.text = text;
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

    /**
     * @return the pdsName
     */
    public String getPdsName() {
        return pdsName;
    }

    /**
     * @param pdsName the pdsName to set
     */
    public void setPdsName(String pdsName) {
        this.pdsName = pdsName;
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

}
