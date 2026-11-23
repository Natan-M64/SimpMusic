package com.maxrave.simpmusic.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxrave.simpmusic.ui.theme.typo
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.log_in_again
import simpmusic.composeapp.generated.resources.retry
import simpmusic.composeapp.generated.resources.youtube_playlists_saved_on_device
import simpmusic.composeapp.generated.resources.youtube_playlists_session_expired
import simpmusic.composeapp.generated.resources.youtube_playlists_session_expired_with_saved

/**
 * Explains why the YouTube playlists tab is not showing a live list.
 *
 * Two different situations, deliberately worded apart: an expired session (YouTube said
 * `logged_in=0`, the account is still stored and only needs signing in again) and a failed
 * request whose tiles came from the device cache. Only the first offers a login action — a
 * network blip must not read as being signed out.
 */
@Composable
internal fun YouTubePlaylistStatusBanner(
    sessionExpired: Boolean,
    hasSavedTiles: Boolean,
    onLogIn: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
            Text(
                text =
                    stringResource(
                        when {
                            sessionExpired && hasSavedTiles -> Res.string.youtube_playlists_session_expired_with_saved
                            sessionExpired -> Res.string.youtube_playlists_session_expired
                            else -> Res.string.youtube_playlists_saved_on_device
                        },
                    ),
                style = typo().bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = if (sessionExpired) onLogIn else onRetry) {
                    Text(
                        stringResource(
                            if (sessionExpired) Res.string.log_in_again else Res.string.retry,
                        ),
                    )
                }
            }
        }
    }
}
