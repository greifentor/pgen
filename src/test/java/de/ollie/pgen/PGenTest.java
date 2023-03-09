package de.ollie.pgen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class PGenTest {

	private MockPrintStream printStream;
	;

	private PrintStream toRestore;

	@BeforeEach
	void setUp() throws Exception {
		toRestore = PGen.out;
		printStream = new MockPrintStream(System.out);
		PGen.out = printStream;
	}

	@BeforeEach
	void tearDown() {
		PGen.out = toRestore;
	}

	@Test
	void createsAPasswordWithPassedLength() {
		// Prepare
		int length = 42;
		// Run
		PGen.main(new String[]{
				"-length",
				"" + length
		});
		// Check
		assertEquals(length, printStream.lastPrinted.length());
	}

	@Test
	void createsAPasswordWithDefaultLengthIfNoLengthIsPassed() {
		// Prepare
		int length = Args.DEFAULT_LENGTH;
		// Run
		PGen.main(new String[]{});
		// Check
		assertEquals(Args.DEFAULT_LENGTH, printStream.lastPrinted.length());
	}

	@Test
	void PrintsAnErrorMessageIfLengthIsPassedAsLessThan10() {
		// Prepare
		int length = Args.MINIMUM_LENGTH - 1;
		// Run
		PGen.main(new String[]{
				"-length",
				"" + length
		});
		// Check
		assertEquals("ERROR: Length can not be less than " + Args.MINIMUM_LENGTH, printStream.lastPrinted);
	}

	@Test
	void createsAPasswordWithAtLeastOneLowerCaseLetter() {
		// Run
		PGen.main(new String[]{});
		// Check
		assertContainsACharacterInRange(printStream.lastPrinted, c -> (c >= 'a') && (c <= 'z'));
	}

	@Test
	void createsAPasswordWithAtLeastOneUpperCaseLetter() {
		// Run
		PGen.main(new String[]{});
		// Check
		assertContainsACharacterInRange(printStream.lastPrinted, c -> (c >= 'A') && (c <= 'Z'));
	}

	@Test
	void createsAPasswordWithAtLeastOneDigit() {
		// Run
		PGen.main(new String[]{});
		// Check
		assertContainsACharacterInRange(printStream.lastPrinted, c -> (c >= '0') && (c <= '9'));
	}

	@Test
	void createsAPasswordWithAtLeastOneSpecialCharacter() {
		// Run
		PGen.main(new String[]{});
		// Check
		assertContainsACharacterInRange(printStream.lastPrinted, c -> "$%&/()?=#+*-_;:,.".contains("" + c));
	}

	private static void assertContainsACharacterInRange(String s, Function<Character, Boolean> check) {
		for (int i = 0, leni = s.length(); i < leni; i++) {
			if (check.apply(s.charAt(i))) {
				return;
			}
		}
		fail("does not contain any lower case character: " + s);
	}

}


class MockPrintStream extends PrintStream {

	String lastPrinted = null;

	public MockPrintStream(OutputStream out) throws FileNotFoundException {
		super(out);
	}

	@Override
	public void println(String x) {
		lastPrinted = x;
	}

}