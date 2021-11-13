/*
 * Copyright (c)
 */

package firefly.View;

import java.util.Date;
import java.time.LocalDateTime;

public interface TransactionCategoryView  {
    String getNameOfTransaction();
    String getNumberOfTransactions();
    Date getIncomeDate();
    double getSum();
    String getCurrency();
}
