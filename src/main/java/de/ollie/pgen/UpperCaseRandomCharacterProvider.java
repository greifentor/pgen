package de.ollie.pgen;

public class UpperCaseRandomCharacterProvider extends RandomCharacterProvider {

	private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public UpperCaseRandomCharacterProvider() {
		super(UPPER_CASE_CHARACTERS);
	}

}
