package com.skcc.start.entity.common;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String groupCode;
    String code;
    String value;

}
