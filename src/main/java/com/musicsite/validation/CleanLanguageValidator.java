package com.musicsite.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CleanLanguageValidator implements ConstraintValidator<CleanLanguage, String> {

    private List<String> REGEXES =
            Arrays.asList(
                    "\\.*k+u+r+w+\\.*",
                    "\\.*j+e+b+\\.*",
                    "\\.*c+h+u+j+\\.*",
                    "\\.*p+i+e+r+d+\\.*",
                    "\\.*f+u+c+k+\\.*",
                    "\\.*b+i+a*t+c+h+\\.*");

    @Override
    public void initialize(CleanLanguage cleanLanguage) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        for (String regex : REGEXES) {

            if (Pattern.matches(regex, string))
                return false;

        }

        return true;
    }
}
