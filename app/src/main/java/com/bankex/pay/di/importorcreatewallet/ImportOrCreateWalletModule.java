package com.bankex.pay.di.importorcreatewallet;

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
import com.bankex.pay.domain.interactor.IImportWalletByPassPhraseInteractor;
import com.bankex.pay.domain.interactor.IImportWalletByPrivateKeyInteractor;
import com.bankex.pay.domain.interactor.ImportWalletByPassPhraseInteractor;
import com.bankex.pay.domain.interactor.ImportWalletByPrivateKeyInteractor;
import com.bankex.pay.presentation.presenter.ImportPassPhrasePresenter;
import com.bankex.pay.presentation.presenter.ImportPrivateKeyPresenter;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.navigation.importorcreate.ImportWalletRouter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

import java.io.File;

import dagger.Module;
import dagger.Provides;

/**
 * Module for import or create wallet screen.
 */
@Module
public class ImportOrCreateWalletModule {
    @Provides
    @ImportOrCreateWalletScope
    IImportWalletFromKeyStoreRepository provideImportWalletFromKeyStoreRepository(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
        return new ImportWalletFromKeyStoreRepository(file);
    }

    @Provides
    @ImportOrCreateWalletScope
    IPasswordStoreRepository providePasswordStoreRepository(Context context) {
        return new PasswordStoreRepository(context);
    }

    @Provides
    @ImportOrCreateWalletScope
    IImportWalletFromPrivateKeyRepository provideImportWalletFromPrivateKeyRepository() {
        return new ImportWalletFromPrivateKeyRepository();
    }

    @Provides
    @ImportOrCreateWalletScope
    IImportWalletFromPassPhraseRepository provideImportWalletFromPassPhraseRepository() {
        return new ImportWalletFromPassPhraseRepository();
    }

    @Provides
    @ImportOrCreateWalletScope
    IImportWalletByPrivateKeyInteractor provideImportWalletFromPrivateKeyInteractor(IImportWalletFromPrivateKeyRepository importWalletFromPrivateKeyRepository,
                                                                                      IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                                                      IPasswordStoreRepository passwordStoreRepository,
                                                                                      IPayWalletRepository payWalletRepository) {
        return new ImportWalletByPrivateKeyInteractor(importWalletFromPrivateKeyRepository, importWalletFromKeyStoreRepository, passwordStoreRepository, payWalletRepository);
    }

    @Provides
    @ImportOrCreateWalletScope
    IImportWalletByPassPhraseInteractor provideImportWalletFromPassPhraseInteractor(IImportWalletFromPassPhraseRepository importWalletFromPassPhraseRepository,
                                                                                      IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                                                      IPasswordStoreRepository passwordStoreRepository,
                                                                                      IPayWalletRepository payWalletRepository) {
        return new ImportWalletByPassPhraseInteractor(importWalletFromPassPhraseRepository, importWalletFromKeyStoreRepository, passwordStoreRepository, payWalletRepository);
    }

    @Provides
    @ImportOrCreateWalletScope
    IImportWalletRouter provideImportWalletRouter() {
        return new ImportWalletRouter();
    }

    @Provides
    @ImportOrCreateWalletScope
    ImportPassPhrasePresenter provideImportPassPhrasePresenter(IImportWalletByPassPhraseInteractor importWalletFromPassPhraseInteractor,
                                                               IRxSchedulersUtils rxSchedulersUtils) {
        return new ImportPassPhrasePresenter(importWalletFromPassPhraseInteractor, rxSchedulersUtils);
    }

    @Provides
    @ImportOrCreateWalletScope
    ImportPrivateKeyPresenter provideImportPrivateKeyPresenter(IImportWalletByPrivateKeyInteractor importWalletFromPrivateKeyInteractor,
                                                               IRxSchedulersUtils rxSchedulersUtils) {
        return new ImportPrivateKeyPresenter(importWalletFromPrivateKeyInteractor, rxSchedulersUtils);
    }
}
