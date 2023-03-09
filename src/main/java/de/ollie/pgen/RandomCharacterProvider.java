package de.ollie.pgen;

public class RandomCharacterProvider {

	private String possibleCharacters;

	protected RandomCharacterProvider(String possibleCharacters) {
		this.possibleCharacters = possibleCharacters;
	}

	public char get() {
		int i = (int) (Math.random() * possibleCharacters.length());
		return possibleCharacters.charAt(i);
	}

}
