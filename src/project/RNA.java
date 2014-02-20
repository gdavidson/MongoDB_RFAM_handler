/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.mongodb.BasicDBObject;

/**
 *
 * @author 1gdavidson
 */
public class RNA extends AbstractMolecule {

    private SecondaryStructure secondaryStructure;
    private String ssBracketNotation;
    private String family;
    /*
     * One word name for family
     */
    private String fID;

    public RNA(String name, String sequence, String bracketN) {
        super(name, sequence);
        this.ssBracketNotation=bracketN;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSequence() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the secondaryStructure
     */
    public SecondaryStructure getSecondaryStructure() {
        return secondaryStructure;
    }

    /**
     * @param secondaryStructure the secondaryStructure to set
     */
    public void setSecondaryStructure(SecondaryStructure secondaryStructure) {
        this.secondaryStructure = secondaryStructure;
    }

    @Override
    public BasicDBObject toJSON() {
        BasicDBObject o = new BasicDBObject();
        o.put("name", this.name);
        o.put("sequence", this.sequence);
        o.put("secondaryStructure", this.ssBracketNotation);
        o.put("family", this.fID);
        String sid = family+","+name;
        o.put("exists","yes");
        int id = sid.hashCode();
        o.put("_id",id);
        return o;

    }

    /**
     * @return the ssBracketNotation
     */
    public String getSsBracketNotation() {
        return ssBracketNotation;
    }

    /**
     * @param ssBracketNotation the ssBracketNotation to set
     */
    public void setSsBracketNotation(String ssBracketNotation) {
        this.ssBracketNotation = ssBracketNotation;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the fID
     */
    public String getfID() {
        return fID;
    }

    /**
     * @param fID the fID to set
     */
    public void setfID(String fID) {
        this.fID = fID;
    }
}
