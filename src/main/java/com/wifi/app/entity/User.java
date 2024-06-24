package com.wifi.app.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private Date date_expired;
    private String typeuser;
    @Column(name = "fail_attempt")
    private Integer fail_attempt;
    @Column(name = "lock_time")
    private Timestamp lock_time;


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="authority_id"))
    private List<Authority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auth = new HashSet<>();
        authorities.forEach(r -> auth.add(new SimpleGrantedAuthority(r.getAuthority())));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


   /* public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
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
    }*/


}
