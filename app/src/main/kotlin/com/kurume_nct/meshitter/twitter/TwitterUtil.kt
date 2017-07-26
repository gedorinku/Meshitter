package com.kurume_nct.meshitter.twitter

import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by gedorinku on 2017/07/26.
 */
object TwitterUtil {

    val twitter: Twitter

    init {
        val config = ConfigurationBuilder()
                .setOAuthConsumerKey(Secrets.consumerKey)
                .setOAuthConsumerSecret(Secrets.consumerSecret)
                .setOAuthAccessToken(Secrets.accessToken)
                .setOAuthAccessTokenSecret(Secrets.accessTokenSecret)
                .build()
        twitter = TwitterFactory(config).instance
    }
}