/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;

/**
 *
 * @author 1gdavidson
 */
public class SecondaryStructure {
    private ArrayList<Helix> helices;
    private ArrayList<SingleStrand> singleStrands;

    public SecondaryStructure(ArrayList<Helix> helices, ArrayList<SingleStrand> singleStrands) {
        this.helices = helices;
        this.singleStrands = singleStrands;
    }

    /**
     * @return the helices
     */
    public ArrayList<Helix> getHelices() {
        return helices;
    }

    /**
     * @param helices the helices to set
     */
    public void setHelices(ArrayList<Helix> helices) {
        this.helices = helices;
    }

    /**
     * @return the singleStrands
     */
    public ArrayList<SingleStrand> getSingleStrands() {
        return singleStrands;
    }

    /**
     * @param singleStrands the singleStrands to set
     */
    public void setSingleStrands(ArrayList<SingleStrand> singleStrands) {
        this.singleStrands = singleStrands;
    }
    
        
    public BasicDBObject toJSON() {
        BasicDBObject o = new BasicDBObject();
        BasicDBList helicesBDL = new BasicDBList();
        BasicDBList singleStrandsBDL = new BasicDBList();
for (Helix h : helices) {
    BasicDBObject hJSON = new BasicDBObject();
    hJSON.put("name", h.getName());
    hJSON.put("starts", h.getStarts());
    hJSON.put("ends", h.getEnds());
    helicesBDL.add(hJSON);
}
o.put("helices", helicesBDL);
for (SingleStrand ss : singleStrands) {
        BasicDBObject sJSON = new BasicDBObject();
    sJSON.put("name", ss.getName());
    sJSON.put("start", ss.getStart());
    sJSON.put("end", ss.getEnd());
    singleStrandsBDL.add(sJSON);
}
o.put("singleStrands", singleStrandsBDL);
        return o;

    }
    
}
