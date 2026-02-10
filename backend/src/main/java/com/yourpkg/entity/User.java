package com.yourpkg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String role; // ADMIN, USER

    // getters & setters
    @Column(nullable = false)
    private String password;

    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    public void setPassword(String encode) {
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }

    public void setRole(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'setRole'");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public Object getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    public String getRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }

}