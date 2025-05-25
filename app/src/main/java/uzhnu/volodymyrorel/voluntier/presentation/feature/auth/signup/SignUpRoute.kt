package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        stateUi = state,
        onSignUpClicked = viewModel::onSignUpClicked,
        onVolunteerClicked = viewModel::onVolunteerClicked,
        onOrganizationClicked = viewModel::onOrganizationClicked,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onRepeatPasswordChanged = viewModel::onRepeatPasswordChanged,
        onUserSurnameChanged = viewModel::onUserSurnameChanged,
        onUserNameChanged = viewModel::onUserNameChanged,
        onUserFatherNameChanged = viewModel::onUserFatherNameChanged,
        onUserIsAdultChanged = viewModel::onUserIsAdultChanged,
        onOrgPublicNameChanged = viewModel::onOrgPublicNameChanged,
        onOrgGovNameChanged = viewModel::onOrgGovNameChanged,
        onOrgTypeChanged = viewModel::onOrgTypeChanged,
        onOrgCodeChanged = viewModel::onOrgCodeChanged
    )
}

@Composable
fun SignUpScreen(
    stateUi: SignUpStateUi,
    onSignUpClicked: () -> Unit,
    onVolunteerClicked: () -> Unit,
    onOrganizationClicked: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
    onUserSurnameChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onUserFatherNameChanged: (String) -> Unit,
    onUserIsAdultChanged: (Boolean) -> Unit,
    onOrgPublicNameChanged: (String) -> Unit,
    onOrgGovNameChanged: (String) -> Unit,
    onOrgTypeChanged: (String) -> Unit,
    onOrgCodeChanged: (String) -> Unit
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "VolunTier"
        )
        Text(
            text = "Sign Up"
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.email,
            onValueChange = { onEmailChanged(it) },
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.password,
            onValueChange = { onPasswordChanged(it) },
            label = {
                Text("Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.repeatPassword,
            onValueChange = { onRepeatPasswordChanged(it) },
            label = {
                Text("Repeat password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onVolunteerClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (stateUi.isOrganization) Color.Transparent
                        else MaterialTheme.colorScheme.primary

                )
            ) {
                Text(
                    text = "Volunteer",
                    color =
                        if (stateUi.isOrganization) Color.Black
                        else Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = onOrganizationClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    if (stateUi.isOrganization) MaterialTheme.colorScheme.primary
                    else Color.Transparent
                )

                ) {
                Text(
                    text = "Organization",
                    color =
                        if (stateUi.isOrganization) Color.Unspecified
                        else Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (!stateUi.isOrganization) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.userSurname,
                onValueChange = { onUserSurnameChanged(it) },
                label = {
                    Text("Your Surname")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.userName,
                onValueChange = { onUserNameChanged(it) },
                label = {
                    Text("Your Name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.userFatherName,
                onValueChange = { onUserFatherNameChanged(it) },
                label = {
                    Text("Your Middle Name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Done
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = stateUi.userIsAdult,
                    onCheckedChange = {onUserIsAdultChanged(!stateUi.userIsAdult)}
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "I am above 18 years old"
                )
            }
        } else {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.orgPublicName,
                onValueChange = { onOrgPublicNameChanged(it) },
                label = {
                    Text("Org. public name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.orgGovName,
                onValueChange = { onOrgGovNameChanged(it) },
                label = {
                    Text("Org. government name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.orgType,
                onValueChange = { onOrgTypeChanged(it) },
                label = {
                    Text("Org. type")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.orgCode,
                onValueChange = { onOrgCodeChanged(it) },
                label = {
                    Text("Org. code (8 digits)")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Unspecified,
                    imeAction = ImeAction.Done
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSignUpClicked
        ) {
            Text(
                text = "Sign Up"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenVolunteerPreview() {
    AppTheme {
        SignUpScreen(
            stateUi = SignUpStateUi.DEFAULT,
            onSignUpClicked = {},
            onVolunteerClicked = {},
            onOrganizationClicked = {},
            onPasswordChanged = {},
            onEmailChanged = {},
            onRepeatPasswordChanged = {},
            onUserSurnameChanged = {},
            onUserNameChanged = {},
            onUserFatherNameChanged = {},
            onUserIsAdultChanged = {},
            onOrgPublicNameChanged = {},
            onOrgGovNameChanged = {},
            onOrgTypeChanged = {},
            onOrgCodeChanged = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenOrganizationPreview() {
    AppTheme {
        SignUpScreen(
            stateUi = SignUpStateUi.DEFAULT.copy(isOrganization = true),
            onSignUpClicked = {},
            onVolunteerClicked = {},
            onOrganizationClicked = {},
            onPasswordChanged = {},
            onEmailChanged = {},
            onRepeatPasswordChanged = {},
            onUserSurnameChanged = {},
            onUserNameChanged = {},
            onUserFatherNameChanged = {},
            onUserIsAdultChanged = {},
            onOrgPublicNameChanged = {},
            onOrgGovNameChanged = {},
            onOrgTypeChanged = {},
            onOrgCodeChanged = {}
        )
    }
}