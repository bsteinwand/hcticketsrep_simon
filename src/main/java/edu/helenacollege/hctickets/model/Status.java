package edu.helenacollege.hctickets.model;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "Status", schema = "hctickets")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Code", nullable = false, length = 20)
    private String code;

    @Column(name = "Value", nullable = false, length = 40)
    private String value;

    @Column(name = "StatusType", nullable = false, length = 20)
    private String statusType;

    @Column(name = "ItemOrder", nullable = false)
    private Integer itemOrder;

    @Column(name = "Active", nullable = false)
    private Boolean active;

}