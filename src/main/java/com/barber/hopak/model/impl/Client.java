package com.barber.hopak.model.impl;

import com.barber.hopak.web.domain.impl.ClientDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements com.barber.hopak.model.Entity<ClientDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Check> checks;
    @Column(nullable = false, length = 30)
    private String firstname;
    @Column(nullable = false, length = 30)
    private String lastname;
    @Column(nullable = false, length = 19, unique = true)
    private String phoneNumber;

    @Override
    public ClientDto toDto() {
        return ClientDto.builder()
                .id(this.id)
                .checks(this.checks)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .phoneNumber(this.phoneNumber).build();
    }
}