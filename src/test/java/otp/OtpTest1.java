package otp;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class OtpTest1 
{

	public static void main(String[] args) 
	{
		// connect to twilio cloud for sms service
		String authid="ACa6e000f61d50ce68a14981410f13069e";
		String authtoken="c1c1eb8634b7ccaaefd9bcf006c783e2";
		Twilio.init(authid, authtoken);
		ResourceSet<Message> messages=Message.reader().read();
		for(Message message:messages)
		{
			System.out.println(message.getFrom()+"-->"+message.getBody());
		}
		

	}

}
