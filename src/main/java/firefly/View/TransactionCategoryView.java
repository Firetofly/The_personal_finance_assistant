/*
 * Copyright (c)
 */

package firefly.View;

import java.time.LocalDateTime;

public interface TransactionCategoryView  {
    String getNameOfTransaction();
    String getNumberOfTransactions();
    LocalDateTime getIncomeDate();
    double getSum();
    String getCurrency();
}
