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
public abstract class AbstractMolecule implements Molecule {
    protected String name;
    protected String sequence;

    public AbstractMolecule(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
    }
    
    public BasicDBObject toJSON;
    
}
