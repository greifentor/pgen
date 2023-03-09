package de.ollie.pgen;

public class DigitRandomCharacterProvider extends RandomCharacterProvider {

	private static final String DIGIT_CHARACTERS = "0123456789";

	public DigitRandomCharacterProvider() {
		super(DIGIT_CHARACTERS);
	}

}
