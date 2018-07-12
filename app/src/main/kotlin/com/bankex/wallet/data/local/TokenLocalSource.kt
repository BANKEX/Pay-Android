package com.bankex.wallet.data.local

import com.bankex.wallet.data.model.NetworkInfo
import com.bankex.wallet.data.model.TokenInfo
import com.bankex.wallet.data.model.Wallet

import io.reactivex.Completable
import io.reactivex.Single

interface TokenLocalSource {
    fun put(networkInfo: NetworkInfo, wallet: Wallet, tokenInfo: TokenInfo): Completable
    fun fetch(networkInfo: NetworkInfo, wallet: Wallet): Single<Array<TokenInfo>>
}
