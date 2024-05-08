package com.example.machinetest.utility

import android.net.Uri
import java.util.regex.Pattern


fun ValidateRegister(
    username:String?,
    email:String?,
    password:String?,
    conpassword:String?
):Boolean{
    return if (username.equals("")||email.equals("")||password.equals("")||conpassword.equals("")){
        false
    }
    else {
        true
    }
}
fun ValidateLogin(
    username:String?,
    password:String?,

):Boolean{
    return if (username.equals("")||password.equals("")){
        false
    }
    else {
        true
    }
}



fun checkpasswordmatch(password: String?,conpassword: String?):Boolean{
    if (password.equals(conpassword)){
        return true
    }
    else{
        return false
    }
}
fun validateemail(email: String?):Boolean{
    val emailRegex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"
    val pattern = Pattern.compile(emailRegex)
    val matcher = pattern.matcher(email)
    return matcher.matches()

}
fun validateproductdetails(
    productName:String?,
    productdesc:String?,
    productprice:String?,
    productcode:String?,
    category_id:Int?,
    image:Uri?
):Boolean{
    return if (productName.equals("")||productdesc.equals("")||productprice.equals("")||productcode.equals("")||category_id==0||image==null){
        false
    } else{
        true
    }


}


val initialstate="initial"
val successstate="success"