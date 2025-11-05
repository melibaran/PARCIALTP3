package com.example.financeapp.ui.screen.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financeapp.R
import com.example.financeapp.ui.components.AuthScreenLayout
import com.example.financeapp.ui.components.Item1
import com.example.financeapp.ui.components.Item2
import com.example.financeapp.ui.theme.Light_green
import com.example.financeapp.ui.theme.Void
import com.example.financeapp.ui.theme.poppinsFamily

@Composable
fun OnBoardingScreen(
    title: String,
    imageRes: Int,
    onNext: () -> Unit,
) {
    AuthScreenLayout(title = title){

                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(240.dp)
                                .clip(CircleShape)
                                .background(Light_green)
                        )
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Onboarding Illustration",
                            modifier = Modifier
                                .size(260.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 28.dp)
                    ) {
                        Text(
                            text = "Next",
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Void,
                            lineHeight = 10.sp,
                            modifier = Modifier.clickable { onNext() }
                        )
                        Spacer(Modifier.height(12.dp))
                        if (imageRes == R.drawable.ilustracion_mano){
                            Item1()
                        }else{
                            Item2()
                        }
                    }
    }
}