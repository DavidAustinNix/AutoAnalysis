package edu.utah.hci.auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class MiscDelme {

	public static void main(String[] args) throws IOException {
		//String con = "jdbc:sqlserver://hci-db.hci.utah.edu:1433;databaseName=gnomex;user=pipeline;password=xxxxxx;encrypt=true;trustServerCertificate=true";
		//new GNomExDbQuery(con, true);
		Util.sendMuttEmail("Hello Subject Single", "david.nix@hci.utah.edu", "Hello World Body");
		Util.sendMuttEmail("Hello Subject Double", "david.nix@hci.utah.edu,david.austin.nix@gmail.com", "Hello World Body");

	}

}
