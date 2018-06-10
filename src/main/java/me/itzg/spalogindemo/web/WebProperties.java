package me.itzg.spalogindemo.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Geoff Bourne
 * @since Jun 2018
 */
@Component
@ConfigurationProperties("app.web")
@Data
public class WebProperties {
    String loginSuccessRedirectUrl;
}
