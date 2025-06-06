package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.R
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
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isPasswordVisible by remember { mutableStateOf(false) }
        val icon =
            if (isPasswordVisible) painterResource(R.drawable.ic_visibility_off_24)
            else painterResource(R.drawable.ic_visibility_on_24)
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.email,
            onValueChange = { onEmailChanged(it) },
            label = {
                Text(text = stringResource(R.string.email))
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
                Text(text = stringResource(R.string.password))
            },
            trailingIcon = {
                IconButton(
                    onClick = {isPasswordVisible = !isPasswordVisible}
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation =
                if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.repeatPassword,
            onValueChange = { onRepeatPasswordChanged(it) },
            label = {
                Text(text = stringResource(R.string.repeat_password))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation()
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
                    text = stringResource(R.string.volunteer),
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
                    text = stringResource(R.string.organization),
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
                    Text(text = stringResource(R.string.your_surname))
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
                    Text(text = stringResource(R.string.your_name))
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
                    Text(text = stringResource(R.string.your_middle_name))
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
                    text = stringResource(R.string.i_am_above_18_years_old),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = stateUi.orgPublicName,
                onValueChange = { onOrgPublicNameChanged(it) },
                label = {
                    Text(text = stringResource(R.string.org_public_name))
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
                    Text(text = stringResource(R.string.org_government_name))
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
                    Text(text = stringResource(R.string.org_type))
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
                    Text(text = stringResource(R.string.org_code_8_digits))
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
            Text(text = stringResource(R.string.sign_up))
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