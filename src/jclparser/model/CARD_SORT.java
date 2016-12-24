/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jclparser.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elialva
 */
public class CARD_SORT extends CARD {

    private String program;

    private final List<String> sentences = new ArrayList<>();

    @Override
    public void parse() {
        System.out.println("Parsing..." + this);
    }

}
