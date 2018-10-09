package com.bankex.pay.di.importorcreate;

import com.bankex.pay.presentation.ui.importorcreate.ImportOrCreateFragment;
import com.bankex.pay.presentation.ui.importwallet.passphrase.ImportPassPhraseFragment;
import com.bankex.pay.presentation.ui.importwallet.privatekey.ImportPrivateKeyFragment;

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

    void inject(ImportPassPhraseFragment fragment);

    void inject(ImportPrivateKeyFragment fragment);
}
