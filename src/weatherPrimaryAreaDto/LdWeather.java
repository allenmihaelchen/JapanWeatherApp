package weatherPrimaryAreaDto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="source")
public class LdWeather {
	
	
	private List<Pref> pref;

	@XmlElement(name="pref")
	public List<Pref> getPref() {
		return pref;
	}

	public void setPref(List<Pref> pref) {
		this.pref = pref;
	}


}
