package adminPageUIs;

public class PostsAdminPageUI {
	public static final String CLASSIC_EDITOR_BUTTON = "//span[@class='dropdown visible']//a[text()='Classic Editor']";
	public static final String SEARCH_POSTS_TEXTBOX = "//input[@Id='post-search-input']";
	public static final String SEARCH_POSTS_BUTTON = "//input[@value='Search Posts']";
	public static final String DYNAMIC_ROW_VALUE_AT_COLUMN_NAME = "//td[@data-colname='%s']//a[text()='%s']";
	public static final String SELECTION_MENU_EDITOR = "//span[@class='expander']";
	public static final String TITLE_AT_COLUMN_TITLE_LINK = "//a[@class='row-title' and text()='%s']";
	public static final String CLASSIC_LINK_HIDDEN_AT_TITLE = "//a[@class='row-title' and text()='%s']//parent::strong//following-sibling::div[@class='row-actions']//a[text()='Classic Editor']";
	public static final String TRASH_HIDDEN_AT_TITLE = "//a[@class='row-title' and text()='%s']//parent::strong//following-sibling::div[@class='row-actions']//span[@class='trash']//a";
	public static final String NO_ITEMS_MESSAGE = "//tr[@class='no-items']//td[text()='No posts found.']";
}
