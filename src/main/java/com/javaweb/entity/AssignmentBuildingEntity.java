package com.javaweb.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="assignmentbuilding")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AssignmentBuildingEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildings;

    @ManyToOne
    @JoinColumn(name ="staffid")
    private UserEntity staffs;
}
