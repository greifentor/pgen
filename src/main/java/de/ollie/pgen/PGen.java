package de.ollie.pgen;

import com.beust.jcommander.JCommander;

import java.io.PrintStream;

public class PGen {

	static PrintStream out = System.out;

	public static void main(String[] argv) {
		Args args = new Args();
		JCommander.newBuilder().addObject(args).build().parse(argv);
		if (args.getLength() < Args.MINIMUM_LENGTH) {
			out.println("ERROR: Length can not be less than " + Args.MINIMUM_LENGTH);
			return;
		}
		out.println(new PasswordGenerator().createNewPassword(args));
	}

}