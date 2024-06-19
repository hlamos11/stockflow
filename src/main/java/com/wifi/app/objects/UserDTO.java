package com.wifi.app.objects;

import com.wifi.app.repository.ValidPassword;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

public class UserDTO {

    @NotBlank(message = "Ingrese el nombre de usuario")
    @Length(max = 10, message = "El usuario no debe exceder los 10 caracteres")
    private String username;

    @NotBlank(message = "Ingrese su nombre")
    @Length(max = 10, message = "El nombre no debe exceder los 10 caracteres")
    private String firstname;

    @NotBlank(message = "Ingrese su apellido")
    @Length(max = 10, message = "El apellido no debe exceder los 10 caracteres")
    private String lastname;

    @ValidPassword
    private String password;
    @NotBlank(message = "Repetir la contraseña")
    private String rpassword;

    private Boolean enabled;
    private Timestamp created_at;
    private Date date_expired;
    private String typeuser;
    private Integer fail_attempt;
    private Timestamp lock_time;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Date getDate_expired() {
        return date_expired;
    }
    public void setDate_expired(Date date_expired) {
        this.date_expired = date_expired;
    }

    public Integer getFail_attempt() {
        return fail_attempt;
    }

    public void setFail_attempt(Integer fail_attempt) {
        this.fail_attempt = fail_attempt;
    }

    public Timestamp getLock_time() {
        return lock_time;
    }

    public void setLock_time(Timestamp lock_time) {
        this.lock_time = lock_time;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", typeuser='" + typeuser + '\'' +
                ", password='" + password + '\'' +
                ", rpassword='" + rpassword + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", enabled=" + enabled +
                ", created_at=" + created_at +
                ", date_expired=" + date_expired +
                '}';
    }
}
