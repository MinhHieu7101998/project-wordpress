package adminPageUIs;

public class AbstractPageUI {
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String DYNAMIC_MENU_LINK = "//div[@class='wp-menu-name' and text()='%s']";
	public static final String MEDIA_PROGRESS_BAR_ICON = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ALL_UPLOAD_IMAGE = "//div[@class='thumbnail']//img";
	public static final String ADD_TITLE_TEXTBOX = "//input[@name='post_title']";
	public static final String IFRAME_CONTENT_TEXTAREA = "//iframe[@id='content_ifr']";
	public static final String ADD_CONTENT_TEXTAREA = "//body[@id='tinymce']//p";
	public static final String CATEGORIES_TAB = "//h2[text()='Categories']";
	public static final String DYNAMIC_CATEGORY_CHECKBOX_BY_NAME  = "//ul[@id='categorychecklist']//li//label[text()=' %s']";
	public static final String TAGS_TEXTBOX = "//input[@id='new-tag-post_tag']";
	public static final String ADD_TAGS_BUTTON = "//input[@class='button tagadd']";
	public static final String SET_FEATURED_IMAGE_LINK = "//a[text()='Set featured image']";
	public static final String DYNAMIC_TAGS_ADD_SUCCESS_BY_TAGS_NAME = "//li[contains(text(),'%s')]//button[@class='ntdelbutton']";
	public static final String UPLOAD_FILES_BUTTON = "//button[text()='Upload files']";
	public static final String SET_FEATURED_IMAGE_BUTTON = "//button[text()='Set featured image']";
	public static final String REMOVE_THUMBNAIL_LINK = "//a[@id='remove-post-thumbnail']";
	public static final String DYNAMIC_NEW_EDIT_DELETE_SUCCESS_MESSAGE = "//div[@id='message']//p[contains(text(),'%s')]";
	public static final String ALL_POSTS_LINK = "//li[@class='wp-first-item current']//a[text()='All Posts']";
	public static final String DYNAMIC_POSTS_LATEST_BY_TITLE_CATEGORY_AND_TODAY = "//a[text()='%s']/parent::h2//preceding-sibling::p[@class='post-categories']//a[text()='%s']/parent::p//following-sibling::p[@class='post-meta']//a[text()='%s']";
	public static final String DYNAMIC_IMAGE_THUMBNAIL_SELECTED = "//a[@id='set-post-thumbnail']//img[contains(@src,'%s')]";
}
