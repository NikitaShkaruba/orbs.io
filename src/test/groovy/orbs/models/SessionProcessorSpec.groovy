package orbs.controllers
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll
import com.google.gson.Gson
import org.springframework.http.MediaType

class SessionProcessorSpec extends Specification {

    def sessionController = new SessionController()
    MockMvc mockMvc = standaloneSetup(sessionController).build()
    Gson gson = new Gson()

    @Unroll("port to /login with '#login', wait for #httpcode")
    def "/login route login and check data"() {
        when:
        print HttpStatus
        def request = post('/login')
        if (httpcode.value() == 200) {
            request = request.param("login", login)
        }
        def returnObj = mockMvc.perform(request)
                        .andReturn()
                        .getResponse()

        then:
        returnObj.getStatus() == httpcode.value()
        returnObj.contentAsString == message

        where:
        login        | httpcode               | message
        null         | HttpStatus.BAD_REQUEST | 'Login not found'
        'progga'     | HttpStatus.OK          | 'Post to /login : progga'
        'progga'     | HttpStatus.OK          | 'Post to /login : progga'
        'username'   | HttpStatus.OK          | 'Post to /login : username'
        'testuser'   | HttpStatus.OK          | 'Post to /login : testuser'
        ''           | HttpStatus.BAD_REQUEST | 'Login not found'
    }

}
