package de.ollie.pgen;

public class SpecialCharacterRandomCharacterProvider extends RandomCharacterProvider {

	private static final String SPECIAL_CHARACTERS = "$%&/()?=#+*-_;:,.";

	public SpecialCharacterRandomCharacterProvider() {
		super(SPECIAL_CHARACTERS);
	}

}
