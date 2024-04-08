package com.example.elderlycare.user.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.NetworkError
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.ServerError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.elderlycare.R
import com.example.elderlycare.utils.Constants
import org.json.JSONObject
class UserRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_register)

//        옵션 선택할때 표시하는 부분
        setupSpinnerCountry()
        setupSpinnerGender()

        val userType = intent.getStringExtra("userType") ?: ""
        setupViewsVisibility(userType)

        findViewById<Button>(R.id.buttonRegister).setOnClickListener {
            val userFormDto = collectUserData()
            registerUser(userFormDto)
        }
    }
    private fun collectUserData(): JSONObject  {
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()
        val phoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber).text.toString()
        val address = findViewById<EditText>(R.id.editTextAddress).text.toString()
        val country = findViewById<Spinner>(R.id.spinnerCountry).selectedItem.toString()
        val gender = findViewById<Spinner>(R.id.spinnerGender).selectedItem.toString()
        val imageUrl = findViewById<EditText>(R.id.editTextImageUrl).text.toString()

        return JSONObject().apply {
            put("name", name)
            put("email", email)
            put("password", password)
            put("confirmPassword", confirmPassword)
            put("phoneNumber", phoneNumber)
            put("address", address)
            put("country", country)
            put("gender", gender)
            put("imageUrl", imageUrl)
        }
    }
private fun registerUser(userFormDto: JSONObject) {
    val queue = Volley.newRequestQueue(this)
    val url = "${Constants.BASE_URL}/m/user/register"

    val jsonObjectRequest = object : JsonObjectRequest(Method.POST, url, userFormDto,
        { response ->
            // 성공 응답 처리
//            Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
        },
        { error ->
            // 에러 처리
            val errorMessage = when (error) {
                is NetworkError -> "Please check your network connection."
                is ServerError -> {
                    // 서버 에러 메시지 추출 및 표시
                    val errorBody = String(error.networkResponse.data, charset("UTF-8"))
                    "Server Error: $errorBody"
                }
                is AuthFailureError -> "Authentication failed."
                is ParseError -> "Parsing error."
                else -> "Unknown error occurred."
            }
            AlertDialog.Builder(this).setMessage(errorMessage).setPositiveButton("OK", null).show()
        }) {
        @Throws(AuthFailureError::class)
        override fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            headers["Content-Type"] = "application/json"
            return headers
        }
    }

    queue.add(jsonObjectRequest)
}

    private fun setupViewsVisibility(userType: String) {
        // Example for senior; adjust similarly for other user types
        val seniorInfoView = findViewById<LinearLayout>(R.id.seniorInfo)
        val guardianInfoView = findViewById<LinearLayout>(R.id.guardianInfo)
        val caregiverInfoView = findViewById<LinearLayout>(R.id.caregiverInfo)

        seniorInfoView.visibility = if (userType == "senior") View.VISIBLE else View.GONE
        guardianInfoView.visibility = View.GONE // Default to GONE, will be made VISIBLE based on CheckBox
        caregiverInfoView.visibility = if (userType == "caregiver") View.VISIBLE else View.GONE

        // CheckBox for Guardian visibility toggle
        val hasGuardianCheckBox = findViewById<CheckBox>(R.id.hasGuardian)
        hasGuardianCheckBox.setOnCheckedChangeListener { _, isChecked ->
            guardianInfoView.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
    }
        private fun setupSpinnerCountry() {
        val countries = arrayOf("지역을 선택해주세요", "서울", "부산", "제주도", "인천", "대구", "광주", "대전", "울산", "세종", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries)
        val spinnerCountry = findViewById<Spinner>(R.id.spinnerCountry)
        spinnerCountry.adapter = adapter
    }

    private fun setupSpinnerGender() {
        val genders = arrayOf("성별을 선택해주세요", "남자", "여자")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genders)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        spinnerGender.adapter = adapter
    }
}
