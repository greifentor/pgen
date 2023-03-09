package de.ollie.pgen;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UpperCaseRandomCharacterProviderTest {


	@InjectMocks
	private UpperCaseRandomCharacterProvider unitUnderTest;

	@ParameterizedTest
	@CsvSource({"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"})
	void returnAnUpperCaseCharacterAtLeastOneTimes(String expected) {
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