package at.vipaso.assign

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Looper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashActivityTest {

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    @Mock
    private lateinit var mockNetworkInfo: NetworkInfo

    private lateinit var splashActivity: SplashActivity

    @Test
    fun testIsNetworkAvailableWithNetworkConnection() {
        Mockito.`when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mockNetworkInfo)
        Mockito.`when`(mockNetworkInfo.isConnected).thenReturn(true)

        val result = splashActivity.isNetworkAvailable()

        assert(result)
    }

    @Test
    fun testIsNetworkAvailableWithoutNetworkConnection() {
        Mockito.`when`(mockConnectivityManager.activeNetworkInfo).thenReturn(null)

        val result = splashActivity.isNetworkAvailable()

        assert(!result)
    }

}
