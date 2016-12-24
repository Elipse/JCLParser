/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.model;

/**
 *
 * @author elialva
 */
public class PGM {

    private MEMBER member;
    private String name;
    private String type;
    private CARD card;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the card
     */
    public CARD getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(CARD card) {
        this.card = card;
    }
}
