package com.fieldofdreams.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fieldofdreams.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
@Entity(name = "users")
@Data
public class User
{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String id;

    private String userName;

    private String password;

    private Integer difficultyLevel;

    public User(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.password = userName;
    }

}
