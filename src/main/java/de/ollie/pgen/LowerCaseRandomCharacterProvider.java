package de.ollie.pgen;

public class LowerCaseRandomCharacterProvider extends RandomCharacterProvider {

	private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

	public LowerCaseRandomCharacterProvider() {
		super(LOWER_CASE_CHARACTERS);
	}

}
