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
public interface Molecule {
   
public String getName();
public String getSequence();
public BasicDBObject toJSON();
public String getSsBracketNotation();
public String getFamily();
public String getfID();
}
