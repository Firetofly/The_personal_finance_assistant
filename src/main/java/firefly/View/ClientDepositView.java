/*
 * Copyright (c)
 */

package firefly.View;

public interface ClientDepositView {
    String getFullName();
    long getIdAccount();
    String getDepositName();
    double getValue();
    double getPercent();
    String getCurrency();
    int getMonth();
}
