package com.schoolmanagement.entity.concretes;

import com.schoolmanagement.entity.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Kullanicinin rolleri bu classin uzerinden setlenecek bu yuzden bu class in db ile iliskili olmasi gerekiyor
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING) //enum typedan data almasi icin bunu kullaniyoruz
    @Column(length = 20)
    private RoleType roleType;
}
