/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author 1gdavidson
 */
public class Helix {
    private String name;
    private int[] starts;
    private int[] ends;

    public Helix(String name, int[] starts, int[] ends) {
        this.name = name;
        this.starts = starts;
        this.ends = ends;
    }

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
     * @return the starts
     */
    public int[] getStarts() {
        return starts;
    }

    /**
     * @param starts the starts to set
     */
    public void setStarts(int[] starts) {
        this.starts = starts;
    }

    /**
     * @return the ends
     */
    public int[] getEnds() {
        return ends;
    }

    /**
     * @param ends the ends to set
     */
    public void setEnds(int[] ends) {
        this.ends = ends;
    }
    
    public int getLength() {
        return ((ends[0] - starts[0])+1);
    }
    
}
