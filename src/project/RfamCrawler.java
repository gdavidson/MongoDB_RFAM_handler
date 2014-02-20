/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 1gdavidson
 */
public class RfamCrawler implements Crawler {

    private static final String rfamURL = "http://rfam.sanger.ac.uk/family/alignment/download/format?alnType=seed&nseLabels=0&format=stockholm&download=1&acc=";

    @Override
    public List<Molecule> getMolecules(String databaseID) throws FileNotFoundException, URISyntaxException, IOException {
        ArrayList<Molecule> molecules = new ArrayList<>();
        String accessURL = rfamURL + databaseID;
        HashMap<String, StringBuilder> seqMap = new HashMap();
        URL fURL = new URL(accessURL);
        URLConnection urlCon = fURL.openConnection();
        InputStream is = urlCon.getInputStream();
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder bracketNotation = new StringBuilder();
        String fam = "";
        while ((line = bf.readLine()) != null) {

            String tline = line.trim();
            if (tline.startsWith("#=GF ID")) {
                String [] arr = tline.split("\\s+");
                fam = arr[2];
            }
            if (tline.startsWith("#=GC SS_cons")) {
                String[] arr = tline.split("\\s+");
                bracketNotation = bracketNotation.append(arr[2]);
            }
            if (!tline.startsWith("#")) {
                if (!tline.startsWith("/")) {
                    if (!tline.equals("")) {
                        String[] sline = tline.split("\\s+");
                        String orga = sline[0];
                        StringBuilder seq = new StringBuilder(sline[1]);
                        if (seqMap.containsKey(orga)) {
                            StringBuilder prevSeq = seqMap.get(orga);
                            StringBuilder newSeq = prevSeq.append(seq);
                            seqMap.put(orga, newSeq);
                        } else {
                            seqMap.put(orga, seq);
                        }


                    }
                }
            }

        }


        for (String orga : seqMap.keySet()) {
            
            StringBuilder seq = seqMap.get(orga);
            StringBuilder rnass = new StringBuilder();
            rnass.append(bracketNotation);

            for (int i = 0; i < seq.length(); i++) {
                char c = seq.charAt(i);
                if (c == '.') {
                    seq.deleteCharAt(i);
                    rnass.deleteCharAt(i);
                    i--;
                }
            }
            RNA rna = new RNA(orga, seq.toString(), rnass.toString());
            rna.setFamily(databaseID);
            rna.setfID(fam);
            molecules.add(rna);
        }

        return molecules;
    }
}
