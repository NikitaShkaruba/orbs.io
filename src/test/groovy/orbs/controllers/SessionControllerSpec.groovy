package orbs.controllers
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll
import org.springframework.http.MediaType

class SessionControllerSpec extends Specification {

    def sessionController = new SessionController()
    MockMvc mockMvc = standaloneSetup(sessionController).build()

    @Unroll("POST to /login with '#login', expect for '#message' with # #httpcode")
    def "/login route login and check data"() {
        when:
        def request = post('/login')
        if (login != null) {
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
        ''           | HttpStatus.BAD_REQUEST | 'Login not found'
        'progga'     | HttpStatus.OK          | 'Post to /login : progga'
        'username'   | HttpStatus.OK          | 'Post to /login : username'
        'testuser'   | HttpStatus.OK          | 'Post to /login : testuser'
    }

}
