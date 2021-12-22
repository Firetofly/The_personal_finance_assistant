/*
 * Copyright (c)
 */

package firefly.DTO;

import firefly.Model.Client;
import firefly.Model.Deposit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDepositDTO {

    private String clientLogin;

    private String depositName;

    private double depositValue;

    private double depositPercent;

    private String depositCurrency;

    private LocalDateTime depositDate;

    private double depositIncome;

}
