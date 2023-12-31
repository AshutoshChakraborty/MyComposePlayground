package com.example.mycomposeplayground
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun MainLayout(navController: NavHostController?) {
    Column(modifier = Modifier.fillMaxHeight()) {

        Image(painter = painterResource(id = R.drawable.background), contentDescription = "image")
        Button(onClick = { navController?.navigate("second") }) {
            Text(text = "Click me", color = Color.White)
        }
        ShowList(viewModel())

    }

}

@Composable
fun ShowList(viewModel: MyViewModel = viewModel()) {

    MyListView(viewModel.myListState) { item ->
        viewModel.onItemSelected(item)
    }


}

@Composable
private fun MyListView(myListState: List<MyChild>?, onLongClick: (MyChild) -> Unit) {
    LazyColumn() {
        if (myListState != null) {
            items(myListState, key = { item -> item.id }) { item ->
                Child(
                    item, onLongClick = { onLongClick(item) }
                )
            }
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Child(
    item: MyChild,
    onLongClick: () -> Unit
) {
    val selectedColor = if (item.isSelected) Color.LightGray else Color.White
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(selectedColor)
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = {},
            ), verticalAlignment = Alignment.CenterVertically
    ) {

        ItemImage(item.image)
        Text(text = item.name, modifier = Modifier.wrapContentHeight())
    }
}

@Composable
private fun ItemImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Icon",
        modifier = Modifier
            .height(60.dp)
            .width(60.dp)
    )
}

@Preview(
    device = "id:pixel_3a", showSystemUi = true
)

@Composable
fun MainLyoutPreview() {
    MainLayout(null)
}


