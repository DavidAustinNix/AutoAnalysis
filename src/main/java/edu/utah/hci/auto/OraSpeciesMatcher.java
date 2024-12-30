package edu.utah.hci.auto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OraSpeciesMatcher {


	/* Pattern and matching ORA reference species to use in compression and decompression
	 * Periodically check https://help.dragen.illumina.com/product-guides/dragen-v4.3/ora-compression for new ones*/
	private String[] patternsToMakeSpecies = new String[] {
			"^human.*", "homo_sapiens",
			".*sapiens.*", "homo_sapiens",
			"^mouse.*", "mus_musculus",
			".*musculus.*", "mus_musculus",
			"^zebrafish.*", "danio_rerio",
			".*rerio", "danio_rerio",
			"^rat.*", "rattus_norvegicus",
			".*norvegicus", "rattus_norvegicus",
			"woodrat", "rattus_norvegicus",
			".*elegans.*", "caenorhabditis_elegans",
			"^caenorhab.*", "caenorhabditis_elegans",
			".*worm.*", "caenorhabditis_elegans",
			"^arabid.*", "arabidopsis_thaliana",
			".*thaliana", "arabidopsis_thaliana",
			"pig", "sus_scrofa",
			".*scrofa", "sus_scrofa",
			".*taurus", "bos_taurus",
			"^sheep.*", "bos_taurus",
			"cow", "bos_taurus",
			"chicken.*", "gallus_gallus",
			".*gallus", "gallus_gallus"
	};
	
	private Pattern[] patterns = null;
	private String[] species = null;
	
	public OraSpeciesMatcher() {
		patterns = new Pattern[(patternsToMakeSpecies.length/2)];
		species = new String[patterns.length];
		int index = 0;
		for (int i=0; i< patternsToMakeSpecies.length; i++) {
			patterns[index]= Pattern.compile(patternsToMakeSpecies[i++], Pattern.CASE_INSENSITIVE);
			species[index]= patternsToMakeSpecies[i];
			index++;
		}
		for (int i=0; i< patterns.length; i++) {
			Util.pl("\t"+patterns[i]+" -> "+species[i]);
		}
	}
	/**@return the species name to use in '--ora-compression-species' argument or "Not Supported"*/
	public String fetchOraSpecies(String gnomexOrganism) {
		Matcher mat = null;
		String toTest = Util.WHITE_SPACE.matcher(gnomexOrganism).replaceAll("");
		for (int i=0; i< patterns.length; i++) {
			mat = patterns[i].matcher(toTest);
			if (mat.matches()) return species[i];
		}
		return "Not Supported";
	}
	
	public static void main (String[] args) {
		OraSpeciesMatcher osm = new OraSpeciesMatcher();
		String[] tests = new String[] {
				"human", "Human and mouse", "Mouse and Human", "Homo Sapiens", "Mus Musculus", "zebra fish", 
				"Rat", "Woodrat", "Worm", "Arabidopsis", "C. elegans", "cow", "Gallus Gallus", "Larry"
		};
		Util.pl("Testing...");
		for (int i=0; i< tests.length; i++) {
			Util.pl(tests[i]+"\t-> "+osm.fetchOraSpecies(tests[i]));
		}
	}
}
