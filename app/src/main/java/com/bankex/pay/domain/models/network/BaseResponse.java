package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Модель базового ответа
 *
 * @param <T>  тип rows
 * @param <T1> тип heads
 * @author Gevork Safaryan on 29.05.2018
 */
public class BaseResponse<T, T1> extends BaseHeadResponse<T1> {

    @SerializedName("rows")
    @Expose
    private T rows;

    public T getRows() {
        return rows;
    }
}
