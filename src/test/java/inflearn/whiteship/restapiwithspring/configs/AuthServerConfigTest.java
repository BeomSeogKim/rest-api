package inflearn.whiteship.restapiwithspring.configs;

import inflearn.whiteship.restapiwithspring.accounts.Account;
import inflearn.whiteship.restapiwithspring.accounts.AccountRole;
import inflearn.whiteship.restapiwithspring.accounts.AccountService;
import inflearn.whiteship.restapiwithspring.common.BaseControllerTest;
import inflearn.whiteship.restapiwithspring.events.TestDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static inflearn.whiteship.restapiwithspring.accounts.AccountRole.ADMIN;
import static inflearn.whiteship.restapiwithspring.accounts.AccountRole.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        // Given
        String username = "kbs4520@naver.com";
        String password = "Tommy";
        Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(ADMIN, USER))
                .build();

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                        .with(httpBasic(clientId, clientSecret))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;

    }
}