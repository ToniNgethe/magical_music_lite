package com.toni.margicalmusic.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.theme.gray_a

@Composable
fun CustomSearchField(onSearch: (String) -> Unit) {
    // search
    val text = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    modifier = Modifier.padding(all = 10.dp),
                    tint = gray_a
                )
                BasicTextField(
                    value = text.value,
                    maxLines = 1,
                    onValueChange = {
                        text.value = it
                        onSearch.invoke(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    textStyle = TextStyle.Default.copy(color = MaterialTheme.colors.onSurface)
                )
            }
            if (text.value.isEmpty()) Text(
                color = gray_a, text = "Search", modifier = Modifier.padding(start = 50.dp)
            )
        }
    }

}