package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun ButtonComponent(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = true,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.main_color)),
        shape = RoundedCornerShape(10.dp)

    ) {
        Text(title, fontSize = 20.sp ,
            fontFamily = FontFamily(Font(R.font.text_bold)))
    }
}
@Composable
fun TextFieldComponent(
    userInput: String,
    placeholder: String = "" ,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onWrite: (String) -> Unit

) {

    TextField(
        value = userInput,
        modifier = modifier,
      onValueChange = {
          onWrite(it)
          print("value changed")
      },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = colorResource(R.color.main_color)
        ),
        shape = RoundedCornerShape(12.dp),

        placeholder = {
            Text(placeholder,
                modifier = Modifier.fillMaxWidth() ,
                textAlign = TextAlign.Left,
                color = Color.LightGray,
                fontSize = 14.sp
            )

        }
    )
}


@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)

        ,
        title = "Login",
        onClick = {}
    )
}


@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview() {
    var userInput by remember { mutableStateOf("")}

   /* TextFieldComponent(
        userInput = userInput,
        placeholder = "username",
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp)
            .background(Color.White.copy(0.6F)) ,
        onWrite = {
            userInput = it
        }

    )*/
}
/*

        UserInputField(
            value = password,
            onValueChange = { password = it },
            label = "كلمة المرور",
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "إخفاء كلمة المرور" else "إظهار كلمة المرور"
                    )
                }
            }
        )
 */

/*

@Composable
fun UserInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null
) {

    val textSelectionColors = TextSelectionColors(
        handleColor = colorResource(R.color.primary_color),
        backgroundColor = colorResource(R.color.primary_color).copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },

            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),

            singleLine = true,

            visualTransformation = visualTransformation,

            keyboardOptions = keyboardOptions,

            trailingIcon = trailingIcon,

            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Right,
                color = colorResource(R.color.textColor)
            )
            ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.primary_color),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = colorResource(R.color.black),
                cursorColor = colorResource(R.color.primary_color)
            )
        )
    }

}

 */


/*
@Composable
fun TextFieldComponent(
    title: String = "",
    questionNumber: String ="",
    isNote: Boolean = false,
    question: SurveyQuestion? = null,
    placeholder: String = "",
    userInput: String = "",
    onValueChange: (String) -> Unit = {},

    ) {

    var currentUserInput by remember { mutableStateOf(userInput) }

    val textSelectionColors = TextSelectionColors(
        handleColor = colorResource(R.color.primary_color),
        backgroundColor = colorResource(R.color.primary_color).copy(alpha = 0.4f)
    )

    LaunchedEffect(userInput) {
        currentUserInput = userInput
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        QuestionTitle(questionNumber , title , isRequired = question?.required ?: "false")

        CompositionLocalProvider(
            LocalTextSelectionColors provides textSelectionColors,
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
            TextField(
                value = currentUserInput,
                onValueChange = {

                    onValueChange(it)

                    currentUserInput = it
                    if(isNote){
                        Log.d("TextFieldComponent", "TextFieldComponent: $question")

                        question?.note = currentUserInput
                        if (question != null) {

                            FormInputScreenViewModel.getInstance().updateDropDownAnswerWithNote(question)
                        }
                    }else{
                        if(question != null){
                            FormInputScreenViewModel.getInstance().updateTextAnswer(question , currentUserInput)
                        }
                    }
                },
                modifier = Modifier

                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(56.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = true
                    )
                    .background(Color.White, RoundedCornerShape(12.dp)),

                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = colorResource(R.color.primary_color)
                ),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right,
                    color = colorResource(R.color.insideTextColor)
                ),

                singleLine = true,
                placeholder = {
                    Text(text = placeholder,
                    color =colorResource(R.color.insideTextColor),
                        fontFamily = FontFamily(Font(R.font.regular_font)),
                        fontSize = 16.sp
                    )
                }

            )
        }
    }
}

 */

/*
struct TextFieldView: View {
    let title: String
    let isSecure: Bool
    @Binding var userInput: String
    @FocusState private var isTextFieldFocused: Bool
    @State private var isPasswordVisible: Bool = false

    var body: some View {
        VStack(alignment: .leading, spacing: 3) {
            Text(title)
                .font(.custom("IBMPlexSansArabic-Medium", size: 14))
                .foregroundColor(.black)
                .fontWeight(.medium)

            ZStack(alignment: .trailing) {
                fieldView()
                    .focused($isTextFieldFocused)
                    .foregroundColor(.black)
                    .font(.custom("IBMPlexSansArabic-Medium", size: 14))
                    .padding(.horizontal, 10)
                    .frame(height: 52)
                    .background(Color.white)
                    .opacity(0.6)
                    .cornerRadius(10)
                    .shadow(radius: 1)
                    .multilineTextAlignment(.leading)
                    .autocapitalization(.none)

                if isSecure {
                    Button(action: {
                        isPasswordVisible.toggle()
                    }) {
                        Image(systemName: isPasswordVisible ? "eye.slash" : "eye")
                            .foregroundColor(Color.gray)
                            .padding(.trailing, 12)
                    }
                }
            }
            .frame(maxWidth: UIScreen.main.bounds.width * 0.9)
            .padding(.bottom, 10)
        }
        .padding(.horizontal)
    }

 */
/*

@Composable
fun PrimaryButton(
    title: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {
        Text(title, fontSize = 20.sp ,
            fontFamily = FontFamily(Font(R.font.regular_font)),
        )
    }
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color))
    ) {
        content()
    }
}

 */