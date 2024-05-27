package com.pplanaturmo.inrappproject.auth.token;

import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Schema(description = "Modelo de tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Schema(description = "ID del token")
    @Id
    @GeneratedValue
    public Integer id;

    @Schema(description = "Valor del token")
    @Column(unique = true)
    public String token;

    @Schema(description = "Tipo de token")
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    @Schema(description = "Booleano token revocado")
    public boolean revoked;

    @Schema(description = "Booleano token caducado")
    public boolean expired;

    @Schema(description = "Usuario al que pertenece el token")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    public User user;
}