package com.example.companyemployee.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "massage")
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Employee fromId;
    private Employee toId;
    @ManyToMany(mappedBy = "massage")
    private Set<Employee> employees = new HashSet<>();



}
