package com.vault.crudproject.service;

import com.vault.crudproject.constants.AppUserRole;
import com.vault.crudproject.json.RegistrationRequest;
import com.vault.crudproject.model.AppUser;
import com.vault.crudproject.utils.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signUpUser(
                AppUser.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .appUserRole(AppUserRole.USER)
                        .build()
        );
    }
}
