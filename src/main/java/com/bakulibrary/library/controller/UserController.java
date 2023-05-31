package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.UpdatePasswordDTO;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showUserDetails(Model model) {
        model.addAttribute("updatePassword", new UpdatePasswordDTO());
        return "user-details";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@Valid @ModelAttribute("updatePassword") UpdatePasswordDTO updatePasswordDTO,
                                 BindingResult bindingResult,
                                 HttpSession httpSession) {

        String newPassword = updatePasswordDTO.getNewPassword();
        String confirmNewPassword = updatePasswordDTO.getConfirmNewPassword();

        if (!newPassword.equals(confirmNewPassword)) {
            bindingResult.rejectValue("confirmNewPassword", null, "Not match with new password!");
        }
        if (bindingResult.hasErrors()) {
            return "user-details";
        }

        User user = (User) httpSession.getAttribute("loggedInUser");
        String currentPassword = updatePasswordDTO.getCurrentPassword();
        boolean passwordMatches = passwordEncoder.matches(currentPassword, user.getPassword());

        if (!passwordMatches) {
            bindingResult.rejectValue("currentPassword", null, "Wrong password!");
        }
        if (bindingResult.hasErrors()) {
            return "user-details";
        }

        userService.updatePassword(newPassword, user.getEmail());
        return "redirect:/account?success";
    }

}
