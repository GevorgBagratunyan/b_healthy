package com.blueteam.tracker.exception.validation;

import java.util.ArrayList;
import java.util.Collection;

public class ValidationError {

    private final Collection<Violation> violations;

    public ValidationError() {
        this.violations = new ArrayList<>();
    }

    public void addViolation(String field, String message) {
        this.violations.add(new Violation(field, message));
    }

    public Collection<Violation> getViolations() {
        return violations;
    }
}
