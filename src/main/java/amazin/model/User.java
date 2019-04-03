package amazin.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Indexed
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Size(min = 1, message = "First name cannot be empty")
    @Column(name="user_first_name")
    @Field
    private String firstName;
    @NotNull
    @Size(min = 1, message = "Last name cannot be empty")
    @Column(name="user_last_name")
    @Field
    private String lastName;
    @NotNull
    @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "Not a valid email address")
    @Field
    @Column(unique = true, name="user_email")
    private String email;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Size(max = 72, message = "Password must be less than 72 characters")
    @Column(name="user_password")
    @Field
    private String password;
    @NotNull
    @Transient
    private String passwordConfirmation;
    @ManyToMany
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
