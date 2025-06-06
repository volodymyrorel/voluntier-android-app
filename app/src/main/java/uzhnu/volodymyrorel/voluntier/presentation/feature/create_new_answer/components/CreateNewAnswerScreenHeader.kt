package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_answer.CreateNewAnswerStateUi
import uzhnu.volodymyrorel.voluntier.R
import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

@Composable
fun CreateNewAnswerScreenHeader(
    state: CreateNewAnswerStateUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text =
                when (state.type) {
                    Demand.TYPE_FUNDRAISING -> stringResource(R.string.fundraising)
                    Demand.TYPE_VOLUNTEERS -> stringResource(R.string.volunteers)
                    else -> stringResource(R.string.material)
                },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.demandTitle, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(12.dp))

        if (state.demandDescription != null) {
            Text(text = stringResource(R.string.description), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = state.demandDescription, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(text = stringResource(R.string.created_at_column), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.createdAt, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(R.string.updated_at_column), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.updatedAt, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        if (state.type == "fundraising") {
            Text(text = "Raised", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = state.currentSum.toString(), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.out_of), style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = state.targetSum.toString(), style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewAnswerScreenHeaderPreview() {
    AppTheme {
        CreateNewAnswerScreenHeader(
            state = CreateNewAnswerStateUi(
                demandId = "123",
                demandTitle = "Збір на РУСОРІЗ",
                demandDescription = "Допоможіть закрити суму\n" +
                        "Там ще 2 з чимось тисяч лишилось зібрати",
                currentSum = 250.0,
                targetSum = 25000.0,
                type = "fundraising",
                createdAt = "01/01/1980",
                updatedAt = "01/01/2000"
            )
        )
    }
}