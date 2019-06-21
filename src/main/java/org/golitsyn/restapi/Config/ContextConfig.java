package org.golitsyn.restapi.Config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Sergey Golitsyn (deft) on 15.06.2019
 */

@Configuration
@NoArgsConstructor
@EnableAspectJAutoProxy
@PropertySource({"classpath:application-${persistenceTarget:local}.properties"})
public class ContextConfig {
}
