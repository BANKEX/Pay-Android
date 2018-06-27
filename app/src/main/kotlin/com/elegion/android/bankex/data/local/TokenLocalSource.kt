package com.elegion.android.bankex.data.local

import com.elegion.android.bankex.data.model.NetworkInfo
import com.elegion.android.bankex.data.model.TokenInfo
import com.elegion.android.bankex.data.model.Wallet

import io.reactivex.Completable
import io.reactivex.Single

interface TokenLocalSource {
    fun put(networkInfo: NetworkInfo, wallet: Wallet, tokenInfo: TokenInfo): Completable
    fun fetch(networkInfo: NetworkInfo, wallet: Wallet): Single<Array<TokenInfo>>
}
