package com.bankex.pay.domain;

import android.support.annotation.Nullable;

/**
 * @author Gevork Safaryan on 31.05.2018
 */
public class ErrorMessage {

    /**
     * Список ошибок
     */
    private String mErrors;

    /**
     * Список предупреждений
     */
    private String mWarnings;

    @Nullable
    public String getError() {
        return mErrors;
    }

    public void setErrors(String errors) {
        mErrors = errors;
    }

    @Nullable
    public String getWarnings() {
        return mWarnings;
    }

    public void setWarnings(String warnings) {
        mWarnings = warnings;
    }

    /**
     * Есть ли ошибки
     *
     * @return boolean
     */
    public boolean hasErros() {
        return mErrors == null || mErrors.trim().length() > 0;
    }

    /**
     * Есть ли предупреждения
     *
     * @return boolean
     */
    public boolean hasWarnings() {
        return mWarnings == null || mWarnings.trim().length() > 0;
    }


}

