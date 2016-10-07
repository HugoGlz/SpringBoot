package hello;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver {
	
	private final static Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	private CountDownLatch latch;
	
	@Autowired
	public Receiver(CountDownLatch latch){
		this.latch = latch;
	}
	
	public void receivedMessage(String message){
		logger.info(String.format("Received <%s>",message));
		latch.countDown();
	}
}
