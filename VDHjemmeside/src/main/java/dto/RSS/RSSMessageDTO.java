package dto.RSS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSSMessageDTO {
	private String title;
	private String description;
	private String link;
	private String author;
	private String guid;
	private String expire;
	private String project;
	private boolean valid;



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		splitDescription(description);
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public void splitDescription(String description) {
		String expireStartID = "Udløber:</b> ";
		String expireEndID = "</div>";
		String textStartID = "<html>";
		String textEndID = "</html>";
		String projectStartID = "Projekt:</b> ";
		String projectEndID = "</div>";

		int expireStart = description.indexOf(expireStartID);
		int expireEnd = description.indexOf(expireEndID);

		int textStart = description.indexOf(textStartID);
		int textEnd = description.indexOf(textEndID);

		int projectStart = description.indexOf(projectStartID);
		int projectEnd = description.lastIndexOf(projectEndID);

		String expire = description.substring(expireStart, expireEnd);
		String text = description.substring(textStart, textEnd);
		String project = description.substring(projectStart, projectEnd);


		expire = expire.replace(expireStartID, "");
		text = text.replace(textStartID, "");
		project = project.replace(projectStartID, "");
		
		
		//int occurenceOfSpan = text.;
		while(text.contains("<span")) {
			int spanStart = text.indexOf("<span");
			int spanEnd = text.indexOf("</span>") + 7;
			String toBeReplaced = text.substring(spanStart, spanEnd);
			text = text.replace(toBeReplaced, "");
		}
		if(text.contains("<br")) {
			int start = text.indexOf("<br");
			int end = text.indexOf("/>") + 2;
			String toBeReplaced = text.substring(start, end);
			text = text.replace(toBeReplaced, "");
		}
		if(text.contains("<div")) {
			int start = text.indexOf("<div");
			int end = text.indexOf("/div>") + 5;
			String toBeReplaced = text.substring(start, end);
			text = text.replace(toBeReplaced, "");
		}
		
		
		
		text = text.replace("<p>", "");
		text = text.replace("</p>", "");
		text = text.replace("&lt;","<");
		text = text.replace("&gt;", ">");
		
		

		if(text.contains("\n")) {
			text = text.replace("\n", "");
		}
		expire = expire.trim();
		text = text.trim();
		project = project.trim();

	
		
		this.expire = expire;
		this.description = text;
		this.project = project;
//		System.out.println(expire);
//		System.out.println(text);
//		System.out.println(project);

		
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			Date date = format.parse(expire);
			valid = date.after(new Date());
//			System.out.println(this.valid);
//			System.out.println(date.toString());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		






	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nRSSMessageDTO \n\tTitle:\t\t" + title + "\n\tDescription:\t" + description + "\n\tLink:\t\t" + link + "\n\tAuthor:\t\t"
				+ author + "\n\tGuid:\t\t" + guid + "\n\tExpire:\t\t" + expire + "\n\tProject:\t" + project
				+ "\n\tValid:\t\t" + valid;
	}




}
