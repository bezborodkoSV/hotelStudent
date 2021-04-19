package com.studenthoteltest.demo.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
//shows that this is an entity that needs to be stored in the database
@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Size(min=5, message = "Не меньше 5 знаков")
    private String username;

    @Column
    @Size(min = 5,message = "Не меньше 5 знаков")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<Role> roles;


    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    private Residents residents;

    @OneToOne(mappedBy = "users")
    private ApplicationsForAccommodation applicationsForAccommodation;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//Constructor start

    public Users() {
    }



//Constructor finish
    //Other


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                ", residents=" + residents +
                ", applicationsForAccommodation=" + applicationsForAccommodation +
                '}';
    }
}
