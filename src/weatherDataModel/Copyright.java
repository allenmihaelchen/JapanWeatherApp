package weatherDataModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Copyright {
	private List<Provider> provider;
	private String link;
	private String title;
	
	public List<Provider> getProvider() {
		return provider;
	}
	public void setProvider(List<Provider> provider) {
		this.provider = provider;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	



	

}
