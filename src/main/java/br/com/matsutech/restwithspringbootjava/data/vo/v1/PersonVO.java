package br.com.matsutech.restwithspringbootjava.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.Mapping;


import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"idPerson", "firstName", "lastName", "address", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }
    private Long idPerson;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    public PersonVO() {
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO person = (PersonVO) o;
        return Objects.equals(idPerson, person.idPerson)
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(address, person.address)
                && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, firstName, lastName, address, gender);
    }
}
