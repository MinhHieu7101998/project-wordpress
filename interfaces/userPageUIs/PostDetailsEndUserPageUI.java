package userPageUIs;

public class PostDetailsEndUserPageUI {
	public static final String POST_TITLE_NAME = "//h1[@class='post-title' and text()='%s']";
	public static final String POST_TAG_NAME = "//div[@class='post-tags']//a[contains(text(),'%s')]";
	public static final String POST_IAMGE_NAME = "//img[@data-image-title='%s']";
	public static final String POST_CATEGORY_NAME = "//p[@class='post-categories']//a[text()='%s']";
	public static final String POST_AUTHOR_NAME = "//span[@class='post-meta-author']//a[text()='%s']";
	public static final String POST_DATE_CREATE = "//span[@class='post-meta-date']//a[text()='%s']";
	public static final String POST_CONTENT = "//div[@class='post-content']//p[text()='%s']";
}
