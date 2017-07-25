package com.kurume_nct.meshitter.view

import android.accounts.AuthenticatorDescription

/**
 * Created by hanah on 7/25/2017.
 */
data class Article<out T>(val description: T)

data class Tag(val tags : List<String>)