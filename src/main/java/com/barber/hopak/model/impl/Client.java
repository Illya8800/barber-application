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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements com.barber.hopak.model.Entity<ClientDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String firstname;
    @Column(nullable = false, length = 30)
    private String lastname;
    @Column(nullable = false, length = 19, unique = true)
    private String phoneNumber;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Check> checks;

    @Override
    public ClientDto toDto() {
        return ClientDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .phoneNumber(this.phoneNumber)
                .checks(this.checks == null ? null : this.checks.stream().map(Check::toDto).toList())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(firstname, client.firstname)) return false;
        if (!Objects.equals(lastname, client.lastname)) return false;
        if (!Objects.equals(phoneNumber, client.phoneNumber)) return false;
        return Objects.equals(checks, client.checks);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (checks != null ? checks.hashCode() : 0);
        return result;
    }
}