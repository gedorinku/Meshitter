package com.kurume_nct.meshitter.twitter

import com.kurume_nct.meshitter.Secrets
import io.reactivex.Single
import twitter4j.Paging
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

    fun nextMeshiTerroTargetsScreenNames(): Single<List<String>> = Single.fromCallable {
        val paging = Paging(1, 200)
        twitter.getUserTimeline(paging)
                .maxBy { it.userMentionEntities.size }
                ?.userMentionEntities
                .orEmpty()
                .map { it.screenName }
                .toList()
    }
}