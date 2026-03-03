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
@Table(name = "Application", schema = "hctickets")
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "AppName", nullable = false, length = 30)
    private String appName;

    @Column(name = "Description", nullable = false, length = 200)
    private String description;

    @Column(name = "CreationDate", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

}
