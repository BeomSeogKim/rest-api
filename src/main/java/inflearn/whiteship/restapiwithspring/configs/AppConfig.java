package inflearn.whiteship.restapiwithspring.configs;

import inflearn.whiteship.restapiwithspring.accounts.Account;
import inflearn.whiteship.restapiwithspring.accounts.AccountRole;
import inflearn.whiteship.restapiwithspring.accounts.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static inflearn.whiteship.restapiwithspring.accounts.AccountRole.ADMIN;
import static inflearn.whiteship.restapiwithspring.accounts.AccountRole.USER;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Account account = Account.builder()
                        .email("kbs4520@naver.com")
                        .password("Tommy")
                        .roles(Set.of(ADMIN, USER))
                        .build();
                accountService.saveAccount(account);
            }
        };
    }
}
