import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.data.remote.Status
import com.appointment.myapplication.repository.DashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardRepositoryTest {

    private lateinit var roomDAO: RoomDAO
    private lateinit var apiService: ApiService
    private lateinit var dashboardRepository: DashboardRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        roomDAO = Mockito.mock(RoomDAO::class.java)
        apiService = Mockito.mock(ApiService::class.java)
        dashboardRepository = DashboardRepository(roomDAO, apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun saveDrugs(): Unit = runBlocking {
        val drugsList = listOf(DrugInfo(5,"asprin","","500mg"), DrugInfo(6,"asprin","","100mg"))

        dashboardRepository.saveDrugsInDB(drugsList)

        Mockito.verify(roomDAO).saveDrugs(drugsList)
    }

}
