package com.chiasmera.quizudfordring_compose.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiasmera.quizudfordring_compose.Data.SampleData
import com.chiasmera.quizudfordring_compose.Model.Category
import com.chiasmera.quizudfordring_compose.Model.EDifficulty

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryChosen: (amount: Int, catID: Int, difficulty: String) -> Unit,
    currentdifficulty: EDifficulty
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(categories) {
            FilledTonalButton(
                onClick = { onCategoryChosen(10, it.id, currentdifficulty.string) },
                contentPadding = PaddingValues(12.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical=8.dp)

            ) {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = ">",
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.End,
                        color = Color.White
                    )
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CategoryListPreview() {
    CategoryList(
        categories = SampleData.categories,
        onCategoryChosen = { i: Int, i1: Int, s: String -> },
        currentdifficulty = EDifficulty.MEDIUM
    )
}