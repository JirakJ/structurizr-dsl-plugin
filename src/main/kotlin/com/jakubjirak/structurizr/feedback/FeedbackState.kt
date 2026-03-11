/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.feedback

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service

/**
 * Application-level persistent state for the feedback notification system.
 */
@Service(Service.Level.APP)
@State(name = "com.jakubjirak.structurizr.FeedbackState", storages = [Storage("com-jakubjirak-structurizr-feedback.xml")])
class FeedbackState : PersistentStateComponent<FeedbackState.State> {

    /** Serializable state data class. */
    class State {
        var firstActivation: Long = 0L
        var feedbackShown: Boolean = false
        var dismissed: Boolean = false
    }

    private var myState = State()

    /** Returns the current state. */
    override fun getState(): State = myState

    /** Loads state from persistent storage. */
    override fun loadState(state: State) {
        myState = state
    }

    companion object {
        /** Returns the application-level service instance. */
        fun getInstance(): FeedbackState =
            service()
    }
}
