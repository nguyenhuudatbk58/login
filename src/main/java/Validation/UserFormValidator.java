package Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Model.User;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty.user.fullName",
				"This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email", "This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.user.address", "This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.user.city", "This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.user.country", "This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.user.userName",
				"This field is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password",
				"This field is required");

		if (!emailValidator.valid(user.getEmail())) {
			errors.rejectValue("email", "Pattern.user.email","Email is invalid.");
		}

	}

}
