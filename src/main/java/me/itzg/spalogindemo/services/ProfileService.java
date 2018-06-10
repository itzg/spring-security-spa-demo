package me.itzg.spalogindemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

/**
 * @author Geoff Bourne
 * @since Jun 2018
 */
@Service
@Slf4j
public class ProfileService {
    @EventListener
    public void handleAuthentication(InteractiveAuthenticationSuccessEvent event) {
        log.info("Saw interactive authentication of {}", event.getAuthentication());
    }

    @EventListener
    public void handleAuthentication(AuthenticationSuccessEvent event) {
        log.info("Saw authentication of {}", event.getAuthentication());
    }
}
