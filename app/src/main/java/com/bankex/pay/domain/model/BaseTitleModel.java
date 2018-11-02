package com.bankex.pay.domain.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Модель вью компонента, отображающего базовый заголовок и кнопку добавления
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public class BaseTitleModel extends BaseBankexModel {

    private String title;
    private String addButtonTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddButtonTitle() {
        return addButtonTitle;
    }

    public void setAddButtonTitle(String addButtonTitle) {
        this.addButtonTitle = addButtonTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseTitleModel that = (BaseTitleModel) o;
        return Objects.equal(title, that.title) &&
                Objects.equal(addButtonTitle, that.addButtonTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, addButtonTitle);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("title", title)
                .add("addButtonTitle", addButtonTitle)
                .toString();
    }
}
