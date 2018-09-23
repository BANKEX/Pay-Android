package com.bankex.pay.di.importorcreate;

import com.bankex.pay.presentation.ui.importorcreate.ImportOrCreateFragment;

import dagger.Subcomponent;

/**
 * Сабкомпонент экрана импорта или создания
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
@Subcomponent(modules = {ImportOrCreateModule.class})
@ImportOrCreateScope
public interface ImportOrCreateComponent {

    @Subcomponent.Builder
    interface Builder {
        ImportOrCreateComponent.Builder makeImportOrCreateModule(ImportOrCreateModule module);

        ImportOrCreateComponent build();
    }

    void inject(ImportOrCreateFragment fragment);
}
