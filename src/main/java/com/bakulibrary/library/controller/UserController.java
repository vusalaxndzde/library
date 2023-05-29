package com.bakulibrary.library.controller;

import com.bakulibrary.library.dto.UpdatePasswordDTO;
import com.bakulibrary.library.entity.User;
import com.bakulibrary.library.service.inter.UserService;
import org.springframework.security.core.Authentication;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserDetails(Model model) {
        model.addAttribute("updatePassword", new UpdatePasswordDTO());
        return "user-details";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute("updatePassword") UpdatePasswordDTO updatePasswordDTO, Authentication authentication, BindingResult bindingResult) {

        if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmNewPassword())) {
            bindingResult.rejectValue("confirmNewPassword",
                                "4001",
                            "not match with new password!");
        }
        if (bindingResult.hasErrors()) {
            return "user-details";
        }

        String email = authentication.getName();
        String currentPassword = updatePasswordDTO.getCurrentPassword();
        User user = userService.findUserByEmailAndPassword(email, currentPassword);
        if (user == null) {
            bindingResult.rejectValue("currentPassword",
                                "404",
                            "wrong password!");
        }
        if (bindingResult.hasErrors()) {
            return "user-details";
        }


        return "redirect:/account";
    }

}
