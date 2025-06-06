package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.R
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.home_user.HomeUserStateUi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AnswerItem(
    state: HomeUserStateUi.AnswerUi
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                        if (state.demandType == Demand.TYPE_FUNDRAISING)
                            painterResource(R.drawable.ic_card_60)
                        else if (state.demandType == Demand.TYPE_VOLUNTEERS)
                            painterResource(R.drawable.ic_person_60)
                        else painterResource(R.drawable.ic_healing_60),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1F)
                ) {
                    Text(
                        text = state.orgName,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = state.demandTitle,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (state.demandDescription != null) {
                        Text(
                            text = state.demandDescription,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                }
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(4.dp))
            if (state.answerDescription != null) {
                Text(
                    text = "${stringResource(R.string.replied)} ${state.answerDescription}",
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (state.demandType == Demand.TYPE_FUNDRAISING) {
                Text(
                    text = "${stringResource(R.string.donated)} ${state.answerSum}",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = "${stringResource(R.string.answered_at_row)} ${state.answerCreatedAt.format(formatter)}",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerItemPreview() {
    AppTheme {
        AnswerItem(
            state = HomeUserStateUi.AnswerUi(
                orgName = "Organization Name",
                demandTitle = "Demand Title",
                demandDescription = "Demand Description",
                demandType = Demand.TYPE_FUNDRAISING,
                answerSum = "500.0",
                answerDescription = "answer description",
                answerCreatedAt = LocalDateTime.MIN
            )
        )
    }
}