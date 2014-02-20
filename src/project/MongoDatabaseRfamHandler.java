/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.mongodb.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 1gdavidson
 */
public class MongoDatabaseRfamHandler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException, IOException {


        Mongo mo = new Mongo();
        DB db = mo.getDB("rfam");
        DBCollection coll = db.getCollection("molecules");

        if (args.length == 0) {
            System.out.println(" Veuillez utiliser une des commandes suivantes:\n"
                    + "-import IDRFAM1,IDRFAM2... : importe dans la BD les ARN des familles données.\n"
                    + "-get ORGANISM : affiche les séquences et les structures secondaires de l'organisme donné. \n"
                    + "-search REGEXP : liste les organismes qui contiennent l'expression régulière donnée. ");
        } else {
            if (args[0].equals("-import")) {
                String[] acs = args[1].split(",");
                for (int i = 0; i < acs.length; i++) {
                    String ac = acs[i];
                    RfamCrawler rc = new RfamCrawler();
                    List<Molecule> molecules = null;
                    try {
                        molecules = rc.getMolecules(ac);
                    } catch (java.io.IOException e) {
                        System.out.println("La famille " + ac + " n'existe pas!");
                    }
                    if (molecules != null) {
                        for (Molecule m : molecules) {
                            String sid = m.getFamily() + "," + m.getName();
                            int id = sid.hashCode();
                            BasicDBObject query = new BasicDBObject("_id", id);
                            if (coll.getCount(query) == 0) {
                                BasicDBObject o = m.toJSON();
                                System.out.println("Inserted: " + m.getName() + " From: " + ac);
                                coll.insert(o);
                            } else {
                                System.out.println("La famille " + ac + " a déja été importée!");
                                break;
                            }

                        }
                    }

                }

            }


            if (args[0].equals("-get")) {
                String name = args[1];
                BasicDBObject query = new BasicDBObject("name", name);
                if (coll.getCount(query) == 0) {
                    System.out.println("Aucun résultats pour " + name);
                } else {
                    DBCursor cursor = coll.find(query);
                    while (cursor.hasNext()) {
                        BasicDBObject o = (BasicDBObject) cursor.next();
                        System.out.println(" ");
                        System.out.println(o.get("family"));
                        System.out.println(o.get("secondaryStructure"));
                        System.out.println(o.get("sequence"));
                        System.out.println(" ");
                    }
                }
            }

            if (args[0].equals("-search")) {
                System.out.println("Début de la recherche.");
                String regexp = args[1];
                Pattern p = Pattern.compile(regexp);
                BasicDBObject query = new BasicDBObject("exists", "yes");
                DBCursor cursor = coll.find(query);
                while (cursor.hasNext()) {
                    BasicDBObject o = (BasicDBObject) cursor.next();
                    String name = (String) o.get("name");
                    Matcher m = p.matcher(name);
                    if (name.contains(regexp) || m.matches()) {
                        System.out.println(name);
                    }

                }
                System.out.println("Recherche terminée!");
            }

            if (!"-search".equals(args[0]) && !"-get".equals(args[0]) && !"-import".equals(args[0])) {
                System.out.println("Commande '" + args[0] + "' inconnue!");
            }


        }
    }
}