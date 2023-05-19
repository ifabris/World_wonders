package hr.algebra.world_wonders.view.main


import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hr.algebra.world_wonders.model.Wonder

@ExperimentalPagingApi
@Composable
fun WondersScreen(
    modifier: Modifier = Modifier,
    wondersState: WonderState
) {
    val wonders = wondersState.wonders.collectAsLazyPagingItems()


    Scaffold {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 12.dp)
        ) {
            items(
                items = wonders,
                key = { wonder -> wonder.id }
            ) { wonder ->
                wonder?.let {
                    WonderItem(
                        modifier,
                        wonder = it
                    )
                }
            }
        }
    }



}

@Composable
fun WonderItem(
    modifier: Modifier,
    wonder: Wonder
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = modifier.padding(vertical = 5.dp)) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(wonder.backdrop_path)
                        .crossfade(true)
                        .build(),
                    contentDescription = wonder.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .height(450.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Row {
                Button(onClick = { playAudio(wonder.audio) }) {
                    Text("Story")
                }
            }

            Text(
                modifier = modifier.padding(top = 6.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                text = "${wonder.name}"
            )
            Text(
                modifier = modifier.padding(top = 4.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary,
                text = "${wonder.country}"
            )
            Text(text = wonder.text)
        }
    }

}


fun playAudio(audio: String) {
    val mMediaPlayer = MediaPlayer()
    mMediaPlayer.reset()
    mMediaPlayer.setDataSource(audio)
    mMediaPlayer.prepare()
    mMediaPlayer.start()
}


