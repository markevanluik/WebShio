package ee.mark.webshiospring.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persons")
public class Person {
    @Id
    @NotBlank
    private String personCode;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    private String phone;

    @NotBlank
    private String password;
}
