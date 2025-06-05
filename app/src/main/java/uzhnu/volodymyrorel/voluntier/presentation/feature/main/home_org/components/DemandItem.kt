package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_org.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.R
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DemandItem(
    demand: Demand,
    onClick: (String) -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick(demand.uid)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .height(60.dp),
                    painter =
                        if (demand.type == Demand.TYPE_FUNDRAISING)
                            painterResource(R.drawable.ic_card_60)
                        else if (demand.type == Demand.TYPE_VOLUNTEERS)
                            painterResource(R.drawable.ic_group_60)
                        else painterResource(R.drawable.ic_healing_60),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1F)
                ) {
                    Text(
                        text = demand.title,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            if (demand.description != null) {
                Text(
                    text = demand.description,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (demand.type == Demand.TYPE_FUNDRAISING) {
                Text(text = "Raised", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = demand.currentSum.toString(), style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "out of", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = demand.targetSum.toString(), style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = "Created at: ${demand.createdAt.format(formatter)}",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Updated at: ${demand.updatedAt.format(formatter)}",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DemandItemPreview() {
    AppTheme {
        DemandItem(
            demand = Demand(
                uid = "",
                ownerId = "",
                type = Demand.TYPE_FUNDRAISING,
                title = "Збір коштів",
                description = "Description",
                targetSum = 250000.0,
                currentSum = 50000.0,
                createdAt = LocalDateTime.MIN,
                updatedAt = LocalDateTime.MIN
            ),
            onClick = {}
        )
    }
}