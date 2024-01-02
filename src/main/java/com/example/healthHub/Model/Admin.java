package com.example.healthHub.Model;

import com.example.healthHub.Dto.Admin.TokenDto;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private List<Token> tokens;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

//    public Token generateToken(TokenDto tokenDto){
//        Token generatedToken = new Token();
//       generatedToken.setGeneratedFor(tokenDto.getGeneratedFor());
//        generatedToken.setToken(UUID.randomUUID().toString());
//        generatedToken.setCreatedAt(Instant.now());
//       generatedToken.setExpiresAt(Date.from(Instant.now()));
//       tokens.add(generatedToken);
//       return generatedToken;
//
//   };

}
