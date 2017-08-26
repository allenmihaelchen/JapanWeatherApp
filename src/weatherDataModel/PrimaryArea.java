package weatherDataModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class PrimaryArea {
	
	private Channel channel;
	
	public Channel getChannel() {
		return channel;
	}

	@XmlElement(name="channel")
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	

}
