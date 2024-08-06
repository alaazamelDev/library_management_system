package com.maids.library_management_system.book.valdiators;


import com.maids.library_management_system.book.constraints.UniqueTitle;
import com.maids.library_management_system.book.repositories.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

    private final BookRepository bookRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        if (title == null) return true;
        return bookRepository.findByTitle(title).isEmpty();
    }
}
