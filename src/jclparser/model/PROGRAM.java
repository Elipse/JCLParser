package jclparser.model;

import java.util.List;

/**
 *
 * @author elialva
 */
public abstract class PROGRAM {

    protected MEMBER member;

    public void setMember(MEMBER member) {
        this.member = member;
    }

    public String getPdsName() { //biblioteca donde vive
        return member.getPdsName();
    }

    public List<String> getText() { //contenido
        return member.getText();
    }

    public String getName() { //nombre del member
        return member.getMemberName();
    }

    @Override
    public String toString() {
        return member.getMemberName();
    }

    public abstract void parse();

}
