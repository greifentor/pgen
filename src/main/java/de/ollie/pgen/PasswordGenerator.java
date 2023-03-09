package de.ollie.pgen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PasswordGenerator {

	private static Map<CharacterType, RandomCharacterProvider> randomCharacterProvider = Map.of(
			CharacterType.LOWER_CASE,
			new LowerCaseRandomCharacterProvider(),
			CharacterType.UPPER_CASE,
			new UpperCaseRandomCharacterProvider(),
			CharacterType.DIGITS,
			new DigitRandomCharacterProvider(),
			CharacterType.SPECIAL,
			new SpecialCharacterRandomCharacterProvider());

	public String createNewPassword(Args args) {
		List<CharacterTypeChecker> allCharacterTypeChecker = Arrays
				.asList(
						new CharacterTypeChecker(c -> ((c >= 'a') && (c <= 'z')), CharacterType.LOWER_CASE),
						new CharacterTypeChecker(c -> ((c >= 'A') && (c <= 'Z')), CharacterType.UPPER_CASE));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.getLength(); i++) {
			sb.append(getCharacter(sb, args.getLength() / 2, allCharacterTypeChecker));
		}
		return sb.toString();
	}

	private char getCharacter(StringBuilder sb, int halfOfLength, List<CharacterTypeChecker> allCharacterTypeChecker) {
		if (!allCharacterTypeChecker.isEmpty() && (sb.length() >= halfOfLength)) {
			return getCharacterByType(allCharacterTypeChecker
					.get((int) (Math.random() * (double) allCharacterTypeChecker.size())).getCharacterType());
		}
		int i = (int) (Math.random() * (double) randomCharacterProvider.size());
		char c = randomCharacterProvider.get(CharacterType.values()[i]).get();
		return c;
	}

	private char getCharacterByType(CharacterType characterType) {
		return randomCharacterProvider.get(characterType).get();
	}

	private enum CharacterType {
		DIGITS,
		LOWER_CASE,
		SPECIAL,
		UPPER_CASE
	}

	@Data
	@AllArgsConstructor
	private static class CharacterTypeChecker {

		private Function<Character, Boolean> checker;
		private CharacterType characterType;

	}

}