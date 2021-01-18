package com.lokmanrazak.peoplemanager.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "people")
@NamedQueries({
        @NamedQuery(
                name = "Person.listPeople",
                query = "SELECT p FROM Person p"
        ),
        @NamedQuery(
                name = "Person.countPeople",
                query = "SELECT COUNT(p) FROM Person p"
        )
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.MERGE
    )
    private List<Address> addresses = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setPerson(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setPerson(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        return id == person.id &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                Objects.equals(addresses, person.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, addresses);
    }
}
