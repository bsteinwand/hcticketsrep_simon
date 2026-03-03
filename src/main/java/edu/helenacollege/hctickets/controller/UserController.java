package edu.helenacollege.hctickets.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.helenacollege.hctickets.dto.UserCreateDto;
import edu.helenacollege.hctickets.dto.UserResponseDto;
import edu.helenacollege.hctickets.dto.UserUpdateDto;
import edu.helenacollege.hctickets.model.Role;
import edu.helenacollege.hctickets.service.DataCacheService;
//import edu.helenacollege.hctickets.model.User;
import edu.helenacollege.hctickets.service.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

import java.util.List;
import java.util.Set;

//import com.github.wimdeblauwe.htmx.annotation.HxRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DataCacheService dataCacheService;

    public UserController(UserService userService, DataCacheService dataCacheService) {
        this.userService = userService;
        this.dataCacheService = dataCacheService;
    }

    @GetMapping
    public String listUsers(Model model) {
//        List<User> users = userService.findAll();
    	List<UserResponseDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/new")
    @HxRequest
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserCreateDto());
        model.addAttribute("roles", dataCacheService.findRoles()); 
        return "user/form";
    }

    @PostMapping
    @HxRequest
    public String createUser(@Valid @ModelAttribute UserCreateDto user, @RequestParam(required = false) Set<String> roles, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", List.of("USER", "ADMIN"));
            return "user/form";
        }
        //userService.saveUser(user, roles != null ? roles : Set.of());
        userService.create(user);
        model.addAttribute("users", userService.findAll());
        return "user/partials :: userRows";
    }

    @GetMapping("/{id}/edit")
    @HxRequest
    public String editUserForm(@PathVariable Integer id, Model model) {
    	UserResponseDto user = userService.findUserWithRole(id);
        if (user == null) {
            return "user/list"; // fallback
        }
        model.addAttribute("user", user);
        System.err.println("RoleId: " + user.roleId());
        model.addAttribute("roles", dataCacheService.findRoles()); //List.of("USER", "ADMIN"));
        return "user/form";
    }

    @PostMapping("/{id}")
    @HxRequest
    public String updateUser(@PathVariable Integer id, @Valid @ModelAttribute UserUpdateDto user, @RequestParam(required = true) int roleId, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", dataCacheService.findRoles());
            return "user/form";
        }
        //user.setId(id);
        //user.roleId(roleId);
        userService.update(id, user);
        model.addAttribute("users", userService.findAll());
        //return "user/partials :: userRow";
        return "user/partials/userRow";
    }
}