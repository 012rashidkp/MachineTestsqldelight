package com.example.machinetest.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.machinetest.R
import com.example.machinetest.ui.theme.AnimatedSplashScreenTheme
import com.example.machinetest.ui.theme.black
import com.example.machinetest.ui.theme.categoryicon
import com.example.machinetest.ui.theme.greylight
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.utility.categoryitems


@Composable
fun textfieldcomponent(
    value:String?,
    changevalue: (String) -> Unit,
    labeltext:String?=null,
    placeholdertext:String?,
    keyboardType: KeyboardType,
    icon: Painter?=null,
    isError:Boolean,
    detailsvalue: String?=null

){


    val defaulticon= painterResource(id = R.drawable.default_icon)
    val tealcolor= colorResource(id = R.color.teal_700)
    val white= colorResource(id = R.color.white)
    var mycolor: Color?=null
    mycolor = if (icon!=null) tealcolor else greylight
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }


    TextField(

        value = value?:"",
        onValueChange = changevalue,
        label = { Text(text = labeltext?:"",

            color = black,
            textAlign = TextAlign.Center,
            fontFamily = popins_regular
        )
        },
        placeholder = {
            Text(text = placeholdertext?:"",
                color = black,
                textAlign = TextAlign.Center,
                fontFamily = popins_regular
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),


        singleLine = true,
        leadingIcon = { Icon(painter = icon?:defaulticon,
            contentDescription = null, tint = mycolor)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(16.dp)),

        colors = TextFieldDefaults.colors(
            cursorColor = greylight,
            focusedContainerColor = greylight,
            disabledContainerColor = greylight,
            errorContainerColor = greylight,
            unfocusedContainerColor = greylight,
            focusedTextColor = black,
            focusedLabelColor = black,
            focusedSupportingTextColor = black,
            unfocusedTextColor = black,
            errorCursorColor = black,
            errorTextColor = black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent

        ),
        isError = isError,
    )
}

@Composable
fun passwordfieldcomponent(
    value:String?,
    changevalue: (String) -> Unit,
    labeltext: String?,
    isError:Boolean
){
    val passwordvisibility= remember { mutableStateOf(false) }
    val passwordicon= painterResource(id = R.drawable.password_icon)
    val hidepasswordicon= painterResource(id = R.drawable.hide_password)
    val showpasswordicon= painterResource(id = R.drawable.show_password)
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= greylight

    TextField(

        value = value!!,
        onValueChange =changevalue,
        label = {
            Text(
                text = labeltext?:"",
                color = black,
                textAlign = TextAlign.Center,
                fontFamily = popins_regular) },
        placeholder = {
            Text(
                text = labeltext?:"",
                color = black,
                textAlign = TextAlign.Center,
                fontFamily = popins_regular) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(painter = passwordicon, contentDescription = null, tint = tealcolor) },

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(16.dp)),


        colors = TextFieldDefaults.colors(
            selectionColors = TextSelectionColors(handleColor = tealcolor, backgroundColor = greycolor),
            cursorColor = greycolor,
            focusedContainerColor = greycolor,
            disabledContainerColor = greycolor,
            errorContainerColor = greycolor,
            unfocusedContainerColor = greycolor,
            focusedTextColor = black,
            focusedLabelColor = black,
            focusedSupportingTextColor = black,
            unfocusedTextColor = black,
            errorCursorColor = black,
            errorTextColor = black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent

        ),
//
        trailingIcon = { Icon(painter = if (!passwordvisibility.value) hidepasswordicon else showpasswordicon , contentDescription =null,
            tint=maincolor ,   modifier = Modifier
                .size(28.dp)
                .clickable {
                    passwordvisibility.value = !passwordvisibility.value
                })
        },

        isError = isError,
        visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySpinner(onClick: (cat_id:Int) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Choose Category") }
    AnimatedSplashScreenTheme {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Transparent)
                    .menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},



                leadingIcon = { Icon(painter = painterResource(id = categoryicon) , contentDescription = "", tint = tealcolor)},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },


                colors = ExposedDropdownMenuDefaults.textFieldColors(

                    cursorColor = Color.Transparent,
                    focusedContainerColor = greylight,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = greylight,
                    unfocusedContainerColor = greylight,
                    focusedTextColor = black,
                    focusedLabelColor = black,
                    unfocusedTextColor = black,
                    errorCursorColor = black,
                    errorTextColor = black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),

                )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.Transparent)

            ) {
                categoryitems.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                selectionOption.catName?:"",
                                fontFamily = popins_regular
                            )
                        },
                        onClick = {
                            selectedOptionText = selectionOption.catName?:""
                            expanded = false
                            onClick(selectionOption.id)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }







}