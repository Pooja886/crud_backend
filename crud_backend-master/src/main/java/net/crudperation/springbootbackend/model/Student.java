package net.crudperation.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    //making primary key
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;


    @Column(name = "email_id",nullable = false)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "password",nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "city",nullable = false)
    @NotEmpty
    private String city;

    @Column(name = "phone_number",nullable = false)
    @NotEmpty
    @Size( min = 10,max=10, message = "Enter valid 10 digit number")
    private String phonenumber;

    @NotEmpty
    @Column(name = "role",nullable = false)
    private String role;

    @Column(name ="approved" , nullable = false)
    private Boolean approved=true;

    @Column(name ="approved_status" , nullable = false)
    private String approvedStatus="PENDING";
}