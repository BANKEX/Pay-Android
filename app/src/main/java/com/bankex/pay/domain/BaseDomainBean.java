package com.bankex.pay.domain;

/**
 * Класс предназначен для передачи:
 * - успешного ответа
 * - ошибки типа <strong>warning</strong>
 * <p>
 * в слой presentation.
 * <p>
 * Модель предназначена для хранения в слое domain, чтобы не нарушать архитектуру-17
 *
 * @param <T> - модель ожидаемого ответа
 * @author Gevork Safaryan on 31.05.2018
 */
public class BaseDomainBean<T> {

    /**
     * Данные
     */
    private T successObject;

    /**
     * Ошибка
     */
    private ErrorMessage errorObject;

    /**
     * Получить модель с успешными данными
     *
     * @return T
     */
    public T getSuccessObject() {
        return successObject;
    }

    public void setSuccessObject(T successObject) {
        this.successObject = successObject;
    }

    /**
     * Получить модель с ошибкой
     *
     * @return {@link ErrorMessage}
     */
    public ErrorMessage getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(ErrorMessage errorObject) {
        this.errorObject = errorObject;
    }
}
