package com.fieldofdreams.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "difficulties")
public class Difficulty
{
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Id
//    String id;

    Integer minLength = 1;

    Integer maxLength = 1;

    Double attemptsMultiplier = 1.5;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Integer level;
}
