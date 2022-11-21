package inflearn.whiteship.restapiwithspring.accounts;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;

    @ElementCollection(fetch = EAGER)
    @Enumerated(value = STRING)
    private Set<AccountRole> roles;

}
