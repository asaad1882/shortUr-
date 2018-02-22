import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import net.visionvalley.iotservices.smac.VanillaApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {VanillaApplication.class},loader = AnnotationConfigContextLoader.class)
@IntegrationTest({"server.port=8090"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
@TestPropertySource(locations="classpath:application.yml")
public class WebSocketTest {
	@Value("${server.port}")
    int port;
	
	    
	    static final String WEBSOCKET_TOPIC = "/usr/smacAPI/alarms";

	    BlockingQueue<String> blockingQueue;
	    WebSocketStompClient stompClient;

	    @Before
	    public void setup() {
	    	WebSocketClient webSocketClient = new StandardWebSocketClient();
	        blockingQueue = new LinkedBlockingDeque();
	        stompClient = new WebSocketStompClient(webSocketClient);
	    }
	    @Test
	    public void shouldReceiveAMessageFromTheServer() throws Exception {
	    	String WEBSOCKET_URI = "ws://localhost:"+port+"/hassantuk?access_token="+
	    	obtainAccessToken("VidSysClient", "vidsysUsr", "Vidsys@123");
	        StompSession session = stompClient
	                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
	                .get(1, TimeUnit.SECONDS);
	        session.subscribe(WEBSOCKET_TOPIC, new DefaultStompFrameHandler());

	        String message = "MESSAGE TEST";
	        session.send(WEBSOCKET_TOPIC, message.getBytes());

	        //Assert.assertEquals(message, blockingQueue.poll(1, SECONDS));
	    }

	    class DefaultStompFrameHandler implements StompFrameHandler {
	        @Override
	        public Type getPayloadType(StompHeaders stompHeaders) {
	            return byte[].class;
	        }

	        @Override
	        public void handleFrame(StompHeaders stompHeaders, Object o) {
	            blockingQueue.offer(new String((byte[]) o));
	        }
	    }
	    private String obtainAccessToken(String clientId, String username, String password) {
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("grant_type", "password");
	        params.put("client_id", clientId);
	        params.put("username", username);
	        params.put("password", password);
	        Response response = RestAssured.given().auth().preemptive()
	          .basic(clientId, "secret").and().with().params(params).when()
	          .post("http://127.0.0.1:"+port+"/oauth/token");
	        return response.jsonPath().getString("access_token");
	    }
	    @Bean
	    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
	        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	        ppc.setIgnoreResourceNotFound(true);
	        return ppc;
	    }

   
}
