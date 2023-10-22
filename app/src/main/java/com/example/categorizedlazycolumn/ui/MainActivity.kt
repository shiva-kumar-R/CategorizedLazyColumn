package com.example.categorizedlazycolumn.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.categorizedlazycolumn.ui.data.Category
import com.example.categorizedlazycolumn.ui.data.names
import com.example.categorizedlazycolumn.ui.theme.CategorizedLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val namesList = names.map {
            Category(
                name = it.key.toString(),
                items = it.value
            )
        }
        setContent {
            CategorizedLazyColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Category(categories = namesList)
                }
            }
        }
    }
}

@Composable
fun CategoryHeader(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(16.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        fontSize = 14.sp
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Category(
    modifier: Modifier = Modifier,
    categories: List<Category>
) {
    LazyColumn(modifier = modifier) {
        categories.forEach {category ->
            stickyHeader { 
                CategoryHeader(text = category.name)
            }
            items(category.items){
                CategoryItem(text = it)
            }
        }
    }
}