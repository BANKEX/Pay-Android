package com.bankex.pay.di.importorcreate;

import android.content.Context;

import com.bankex.pay.data.repository.IImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.IImportWalletFromPassPhraseRepository;
import com.bankex.pay.data.repository.IImportWalletFromPrivateKeyRepository;
import com.bankex.pay.data.repository.IPasswordStoreRepository;
import com.bankex.pay.data.repository.IPayWalletRepository;
import com.bankex.pay.data.repository.ImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.ImportWalletFromPassPhraseRepository;
import com.bankex.pay.data.repository.ImportWalletFromPrivateKeyRepository;
import com.bankex.pay.data.repository.PasswordStoreRepository;
import com.bankex.pay.domain.interactor.IImportWalletFromPassPhraseInteractor;
import com.bankex.pay.domain.interactor.IImportWalletFromPrivateKeyInteractor;
import com.bankex.pay.domain.interactor.ImportWalletFromPassPhraseInteractor;
import com.bankex.pay.domain.interactor.ImportWalletFromPrivateKeyInteractor;
import com.bankex.pay.presentation.presenter.ImportPassPhrasePresenter;
import com.bankex.pay.presentation.presenter.ImportPrivateKeyPresenter;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.navigation.importorcreate.ImportWalletRouter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

import java.io.File;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль экрана Создания/Импорта
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
@Module
public class ImportOrCreateModule {

    @Provides
    @ImportOrCreateScope
    IImportWalletFromKeyStoreRepository provideImportWalletFromKeyStoreRepository(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
        return new ImportWalletFromKeyStoreRepository(file);
    }

    @Provides
    @ImportOrCreateScope
    IPasswordStoreRepository providePasswordStoreRepository(Context context) {
        return new PasswordStoreRepository(context);
    }

    @Provides
    @ImportOrCreateScope
    IImportWalletFromPrivateKeyRepository provideImportWalletFromPrivateKeyRepository() {
        return new ImportWalletFromPrivateKeyRepository();
    }

    @Provides
    @ImportOrCreateScope
    IImportWalletFromPassPhraseRepository provideImportWalletFromPassPhraseRepository() {
        return new ImportWalletFromPassPhraseRepository();
    }

    @Provides
    @ImportOrCreateScope
    IImportWalletFromPrivateKeyInteractor provideImportWalletFromPrivateKeyInteractor(IImportWalletFromPrivateKeyRepository importWalletFromPrivateKeyRepository,
                                                                                      IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                                                      IPasswordStoreRepository passwordStoreRepository,
                                                                                      IPayWalletRepository payWalletRepository) {
        return new ImportWalletFromPrivateKeyInteractor(importWalletFromPrivateKeyRepository, importWalletFromKeyStoreRepository, passwordStoreRepository, payWalletRepository);
    }

    @Provides
    @ImportOrCreateScope
    IImportWalletFromPassPhraseInteractor provideImportWalletFromPassPhraseInteractor(IImportWalletFromPassPhraseRepository importWalletFromPassPhraseRepository,
                                                                                      IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                                                      IPasswordStoreRepository passwordStoreRepository,
                                                                                      IPayWalletRepository payWalletRepository) {
        return new ImportWalletFromPassPhraseInteractor(importWalletFromPassPhraseRepository, importWalletFromKeyStoreRepository, passwordStoreRepository, payWalletRepository);
    }

    @Provides
    @ImportOrCreateScope
    IImportWalletRouter provideImportWalletRouter() {
        return new ImportWalletRouter();
    }

    @Provides
    @ImportOrCreateScope
    ImportPassPhrasePresenter provideImportPassPhrasePresenter(IImportWalletFromPassPhraseInteractor importWalletFromPassPhraseInteractor,
                                                               IRxSchedulersUtils rxSchedulersUtils) {
        return new ImportPassPhrasePresenter(importWalletFromPassPhraseInteractor, rxSchedulersUtils);
    }

    @Provides
    @ImportOrCreateScope
    ImportPrivateKeyPresenter provideImportPrivateKeyPresenter(IImportWalletFromPrivateKeyInteractor importWalletFromPrivateKeyInteractor,
                                                               IRxSchedulersUtils rxSchedulersUtils) {
        return new ImportPrivateKeyPresenter(importWalletFromPrivateKeyInteractor, rxSchedulersUtils);
    }
}
