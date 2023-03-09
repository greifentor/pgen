package de.ollie.pgen;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LowerCaseRandomCharacterProviderTest {

	@InjectMocks
	private LowerCaseRandomCharacterProvider unitUnderTest;

	@ParameterizedTest
	@CsvSource({"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"})
	void returnAnLowerCaseCharacterAtLeastOneTimes(String expected) {
		int i = 0;
		boolean found = false;
		while ((i < 1000) && !found) {
			if (expected.charAt(0) == unitUnderTest.get()) {
				found = true;
			}
			i++;
		}
		if (!found) {
			fail("'" + expected + "' has not been produced.");
		}
	}

}