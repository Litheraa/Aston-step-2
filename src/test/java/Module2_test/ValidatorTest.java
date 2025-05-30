package Module2_test;

import Module2.repository.User;
import Module2.utils.ValidatorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
	User user;

	@BeforeEach
	void setUpUser() {
		user = new User("Barbara", 33, "mail@mail.com");
	}

	@Test
	void validator_test_ok() {
		assertDoesNotThrow(() -> ValidatorUtils.validate(user));
	}

	@Test
	void validator_test_empty() {
		User user = new User();

		IllegalArgumentException exception =
				assertThrows(IllegalArgumentException.class, () -> ValidatorUtils.validate(user));

		assertTrue(exception.getMessage().contains("Validation error"));
	}

	@ParameterizedTest
	@MethodSource("invalidNames")
	void validator_test_invalidNames(String invalidName) {
		user.setName(invalidName);

		IllegalArgumentException exception =
				assertThrows(IllegalArgumentException.class, () -> ValidatorUtils.validate(user));

		assertTrue(exception.getMessage().contains("Validation error"));
	}

	private static Stream<String> invalidNames() {
		return Stream.of("", "A", "a".repeat(256)
		);
	}

	@Test
	void validator_test_email() {
		user.setEmail("mail.ru");

		IllegalArgumentException exception =
				assertThrows(IllegalArgumentException.class, () -> ValidatorUtils.validate(user));

		assertTrue(exception.getMessage().contains("Validation error"));
	}

	@Test
	void validator_test_birthDate_Age() {
		user.setAge(Integer.MAX_VALUE);

		IllegalArgumentException exception =
				assertThrows(IllegalArgumentException.class, () -> ValidatorUtils.validate(user));

		assertTrue(exception.getMessage().contains("Validation error"));
	}
}
