/* Copyright 2026 Jakub Jirák. All rights reserved. Licensed under the proprietary license. See LICENSE for details. */
package com.jakubjirak.structurizr.feedback

import com.intellij.ide.BrowserUtil
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import kotlin.coroutines.Continuation

/**
 * Non-intrusive feedback prompt shown once after 7 days of plugin usage.
 */
class FeedbackNotifier : ProjectActivity {

    /** Checks activation time and shows feedback notification if appropriate. */
    override suspend fun execute(project: Project) {
        val feedbackState = FeedbackState.getInstance()
        val state = feedbackState.state ?: return
        val now = System.currentTimeMillis()

        if (state.firstActivation == 0L) {
            state.firstActivation = now
            return
        }

        if (state.feedbackShown || state.dismissed) return

        val daysSinceFirst = (now - state.firstActivation) / (1000 * 60 * 60 * 24)
        if (daysSinceFirst < 7) return

        state.feedbackShown = true

        NotificationGroupManager.getInstance()
            .getNotificationGroup("Structurizr DSL Feedback")
            .createNotification(
                "Structurizr DSL",
                "You've been using this plugin for over a week — thank you! \uD83C\uDF89\nWe'd love your feedback to help us improve.",
                NotificationType.INFORMATION
            )
            .addAction(NotificationAction.createSimpleExpiring("\uD83D\uDCE7 Send Feedback") {
                BrowserUtil.browse("mailto:feedback@jakubjirak.com?subject=Feedback: Structurizr%20DSL")
            })
            .addAction(NotificationAction.createSimpleExpiring("⭐ Rate on Marketplace") {
                BrowserUtil.browse("https://plugins.jetbrains.com/plugin/29351/reviews")
            })
            .addAction(NotificationAction.createSimpleExpiring("Later") {
                state.feedbackShown = false
            })
            .addAction(NotificationAction.createSimpleExpiring("Don't show again") {
                state.dismissed = true
            })
            .notify(project)
    }
}
