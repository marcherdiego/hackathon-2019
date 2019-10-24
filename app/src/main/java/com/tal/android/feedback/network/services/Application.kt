package com.tal.android.feedback.network.services

import android.app.Application
import com.tal.android.configurators.Configurator
import com.tal.android.configurators.ConfiguratorManager
import com.tal.android.feedback.BuildConfig
import com.tal.android.feedback.network.NetworkingConfigurator

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = HashMap<String, Any?>()
        config[Configurator.DEBUG_MODE] = BuildConfig.DEBUG
        config[Configurator.CONTEXT] = this
        config[Configurator.BASE_URL] = "https://evening-sea-85841.herokuapp.com/"
        config[Configurator.THUMBOR_LOCATION] = ""

        config[Configurator.NETWORKING_API_URL] = ""
        config[Configurator.NETWORKING_API_SECURE] = ""
        config[Configurator.NETWORKING_OAUTH_URL] = ""
        config[Configurator.NETWORKING_OAUTH_SECURE] = ""
        config[Configurator.NETWORKING_READ_TIMEOUT_SECONDS] = ""
        config[Configurator.NETWORKING_CONNECT_TIMEOUT_SECONDS] = ""
        NetworkingConfigurator().configure(config)
        ConfiguratorManager.configureLibraries(config)
    }
}