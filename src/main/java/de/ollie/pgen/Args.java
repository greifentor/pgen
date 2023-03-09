package de.ollie.pgen;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class Args {

	public static int DEFAULT_LENGTH = 255;
	public static int MINIMUM_LENGTH = 10;

	@Parameter(names = "-length", description = "Length of the password.", required = false)
	private int length = DEFAULT_LENGTH;

}
