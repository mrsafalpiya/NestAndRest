package com.nestandrest.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import jakarta.servlet.http.Part;

/**
 * Utility class providing various validation methods for input data. Contains
 * methods to validate text fields, email addresses, phone numbers, passwords,
 * images, and more.
 */
public class ValidationUtil {

	/**
	 * Validates if a field is null or empty.
	 * 
	 * @param value the string to validate
	 * @return true if the string is null or empty, false otherwise
	 */
	// 1. Validate if a field is null or empty
	public boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Validates if a string contains only alphabetic characters.
	 * 
	 * @param value the string to validate
	 * @return true if the string contains only letters, false otherwise
	 */
	// 2. Validate if a string contains only letters
	public boolean isAlphabetic(String value) {
		return !isNullOrEmpty(value) && value.matches("^[a-zA-Z]+$"); //
	}

	/**
	 * Validates if a string starts with a letter and is composed of only letters
	 * and numbers.
	 * 
	 * @param value the string to validate
	 * @return true if the string starts with a letter and contains only
	 *         alphanumeric characters, false otherwise
	 */
	// 3. Validate if a string starts with a letter and is composed of letters and
	// numbers
	public boolean isAlphanumericStartingWithLetter(String value) {
		return !isNullOrEmpty(value) && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * Validates if a string is either "male" or "female" (case insensitive).
	 * 
	 * @param value the string to validate
	 * @return true if the string is either "male" or "female", false otherwise
	 */
	// 4. Validate if a string is "male" or "female" (case insensitive)
	public boolean isValidGender(String value) {
		return !isNullOrEmpty(value) && (value.equalsIgnoreCase("male") || value.equalsIgnoreCase("female"));
	}

	/**
	 * Validates if a string is a valid email address format. Uses regex pattern to
	 * check for standard email format.
	 * 
	 * @param email the email string to validate
	 * @return true if the string is a valid email format, false otherwise
	 */
	// 5. Validate if a string is a valid email address
	public boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return !isNullOrEmpty(email) && Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates if a string is a valid phone number. Number must be 10 digits and
	 * start with "98".
	 * 
	 * @param number the phone number string to validate
	 * @return true if the string is a valid phone number format, false otherwise
	 */
	// 6. Validate if a number is of 10 digits and starts with 98
	public boolean isValidPhoneNumber(String number) {
		return !isNullOrEmpty(number) && number.matches("^98\\d{8}$");
	}

	/**
	 * Validates if a password meets security requirements. Password must have at
	 * least 1 capital letter, 1 number, 1 symbol, and be at least 8 characters
	 * long.
	 * 
	 * @param password the password string to validate
	 * @return true if the password meets the security requirements, false otherwise
	 */
	// 7. Validate if a password is composed of at least 1 capital letter, 1 number,
	// 1 symbol and is of at least 8 characters
	public boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return !isNullOrEmpty(password) && password.matches(passwordRegex);
	}

	/**
	 * Validates if a file part contains an image with a valid extension. Valid
	 * extensions are: jpg, jpeg, png, gif.
	 * 
	 * @param imagePart the Part object containing the uploaded file
	 * @return true if the file has a valid image extension, false otherwise
	 */
	// 8. Validate if a Part's file extension matches with image extensions (jpg,
	// jpeg, png, gif)
	public boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}

	/**
	 * Validates if two password strings match. Used typically for password
	 * confirmation.
	 * 
	 * @param password       the original password
	 * @param retypePassword the retyped password to compare with
	 * @return true if both passwords are not empty and match, false otherwise
	 */
	// 9. Validate if password and retype password match
	public boolean doPasswordsMatch(String password, String retypePassword) {
		return !isNullOrEmpty(password) && !isNullOrEmpty(retypePassword) && password.equals(retypePassword);
	}

	/**
	 * Validates if a person is at least 16 years old based on their date of birth.
	 * 
	 * @param dob the date of birth to validate
	 * @return true if the person is at least 16 years old, false otherwise
	 */
	// 10. Validate if the date of birth is at least 16 years before today
	public boolean isAgeAtLeast16(LocalDate dob) {
		if (dob == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 16;
	}

	/**
	 * Validates if a name contains only alphabetic characters, allowing spaces
	 * between words. Each word in the name must contain only letters.
	 * 
	 * @param name The name to validate
	 * @return true if the name is valid (each word contains only letters), false
	 *         otherwise
	 */
	public boolean isValidName(String name) {
		if (isNullOrEmpty(name)) {
			return false;
		}

		// Split the name by spaces
		String[] nameParts = name.trim().split("\\s+");

		// Check if there's at least one part
		if (nameParts.length == 0) {
			return false;
		}

		// Validate each part
		for (String part : nameParts) {
			if (!isAlphabetic(part)) {
				return false;
			}
		}

		return true;
	}
}