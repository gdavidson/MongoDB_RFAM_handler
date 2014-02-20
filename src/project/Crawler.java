/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 *
 * @author 1gdavidson
 */
public interface Crawler {
    List<Molecule> getMolecules(String databaseID) throws FileNotFoundException, URISyntaxException, IOException;
}
