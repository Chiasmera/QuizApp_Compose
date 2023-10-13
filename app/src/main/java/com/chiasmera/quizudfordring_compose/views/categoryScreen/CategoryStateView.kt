package com.chiasmera.quizudfordring_compose.views.categoryScreen

//import androidx.compose.material.Text

//

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.chiasmera.quizudfordring_compose.controller.CategoryState
import com.chiasmera.quizudfordring_compose.data.SampleData
import com.chiasmera.quizudfordring_compose.model.Category
import com.chiasmera.quizudfordring_compose.model.EDifficulty

@Composable
fun CategoryStateView(
    modifier: Modifier = Modifier,
    categoryState: CategoryState,
    onCategoryChosen: (amount: Int, catID: Int, difficulty: String) -> Unit
) {
    var difficulty by remember { mutableStateOf(EDifficulty.MEDIUM) }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        when (categoryState) {
            is CategoryState.Success -> CategoryView(
                categories = categoryState.categories,
                onCategoryChosen = onCategoryChosen,
                onDifficultySelection = { index ->
                    difficulty = EDifficulty.values().asList()[index]
                },
                currentdifficulty = difficulty
            )

            is CategoryState.Loading -> Text("Loading...")
            is CategoryState.Error -> Text(text = "Network error")
        }

    }
}

@Composable
fun CategoryView(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategoryChosen: (amount: Int, catID: Int, difficulty: String) -> Unit,
    onDifficultySelection: (index: Int) -> Unit = {},
    currentdifficulty: EDifficulty
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Select a category",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold

        )

        ButtonToggleGroup(
            items = EDifficulty.values().asList(), onClick = onDifficultySelection
        )

        CategoryList(
            categories = categories,
            onCategoryChosen = onCategoryChosen,
            currentdifficulty = currentdifficulty
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun CategoriesPreview() {
    CategoryView(categories = SampleData.categories,
        onCategoryChosen = { i: Int, i1: Int, s: String -> },
        onDifficultySelection = { },
        currentdifficulty = EDifficulty.MEDIUM
    )
}