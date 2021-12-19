/*
 * Copyright (c)
 */

package firefly.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_account")
    private long accountId;

    private String name;

    private double value;

    private double percent;

    private String currency;

    //Date of creation deposit
    private LocalDateTime date;

    //Value of monthly income
    private double income;





}
