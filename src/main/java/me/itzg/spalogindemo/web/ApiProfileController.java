package me.itzg.spalogindemo.web;

import me.itzg.spalogindemo.model.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Geoff Bourne
 * @since Jun 2018
 */
@RestController
@RequestMapping("/api/profile")
public class ApiProfileController {

    @GetMapping
    public Profile getMyProfile(Principal principal) {
        if (principal != null) {
            Profile profile = new Profile();
            profile.setLoggedIn(true);
            profile.setUsername(principal.getName());
            return profile;
        }
        else {
            return new Profile();
        }
    }
}
