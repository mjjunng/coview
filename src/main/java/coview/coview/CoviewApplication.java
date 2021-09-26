package coview.coview;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EntityScan("coview.coview")
@RequiredArgsConstructor
public class CoviewApplication {
//	private final WebRtcSignalingEndpoint webRtcSignalingEndpoint;
	public static void main(String[] args) {
		SpringApplication.run(CoviewApplication.class, args);
	}


}
