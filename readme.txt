Requires mongoDB installed and running.
Mongo DB:
- http://www.mongodb.org/
Rfam:
-http://rfam.sanger.ac.uk/

Run by using command "
java -jar "MongoDatabaseRfamHandler.jar" <args>" while in /dist folder.

Possible arguments:
-import <IDRFAM1>,<IDRFAM2>... : imports RNA families from a list of IDs separated by commas.
-get <ORGANISM> : displays the sequences and secondary structure of a given organism.
-search <REGEXP>: displays organisms that contains the given regular expression.
