package com.google.sample.basiccompose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.sample.basiccompose.Model.Message
import com.google.sample.basiccompose.Model.SampleData
import com.google.sample.basiccompose.ui.theme.BasicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicComposeTheme {
                ShowText(msg = Message("Chuck Bsas" , "I'am Android"))
            }
        }
    }
}

// Need to place the annotation @Composable to show the UI
// Thw @Composable function can only be called in @Composable function

/*
To decorate or configure a composable, Compose uses modifiers.
They allow you to change the composable's size, layout, appearance
or add high-level interactions, such as making an element clickable.
*/

@Composable
fun ShowText(msg : Message){
    Row {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "The Profile Picture",
            modifier = Modifier
                .size(40.dp) // changing the size of the Image
                .clip(CircleShape) // Making the size of the image circular
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column{
           Text(
               text = msg.author,
               color = MaterialTheme.colors.secondaryVariant,
               style = MaterialTheme.typography.subtitle2
           )
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp
            )
            {
               Text(
                   text = msg.body,
                   style = MaterialTheme.typography.body2,
                   modifier = Modifier.padding(all = 4.dp)
               )
            }
        }
    }
}

// To show the stuff or view the stuff, need to place the @Preview annotation
@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PeviewText(){
    BasicComposeTheme{
        ShowText(msg = Message("RFA", "I'am Chuck Bass"))
    }
}

/*
 * LazyColumn and LazyRow.
 * These composables render only the elements that are visible on screen,
 * so they are designed to be very efficient for long lists.
 */

@Composable
fun Conversation(messages : List<Message>){
    LazyColumn{
        items(messages){ message ->
            ShowText(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    BasicComposeTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}