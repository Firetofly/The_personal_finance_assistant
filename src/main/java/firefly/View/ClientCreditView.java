/*
 * Copyright (c)
 */

package firefly.View;

public interface ClientCreditView {
    String getFullName();
    long getIdAccount();
    String getCreditName();
    double getValue();
    double getPercent();
    String getCurrency();
    int getMonth();
    double getMonthlyPayment();
}
