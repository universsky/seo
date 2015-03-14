/**
 * 
 */
package seo.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.apache.http.client.ClientProtocolException;

/**
 * @author jack
 *
 */
public class BlogsDownloader {

	/**
	 * 
	 * @param html
	 * @param param
	 * @return ArrayList<String[]> getArticleUrlTitle
	 */
	public static ArrayList<String[]> getArticleUrlTitleByHtml(String html,
			Param param) {

		String articleUrlRegex = param.getArticleUrlRegex();
		// System.out.println(articleUrlRegex);
		String articleUrlStartIndex = param.getArticleUrlStartIndex();
		String articleUrlEndIndex = param.getArticleUrlEndIndex();
		int articleUrlStartOffset = param.getArticleUrlStartOffset();
		int articleUrlEndOffset = param.getArticleUrlEndOffset();

		String articleTitleRegex = param.getArticleTitleRegex();
		String articleTitleStartIndex = param.getArticleTitleStartIndex();
		String articleTitleEndIndex = param.getArticleTitleEndIndex();
		int articleTitleStartOffset = param.getArticleTitleStartOffset();
		int articleTitleEndOffset = param.getArticleTitleEndOffset();

		ArrayList<String[]> articleUrlsTitles = new ArrayList<String[]>();
		// System.out.println(html);
		Matcher matcher = Tools.regexgMatch(articleUrlRegex, html);
		if (null == matcher) {
			return null;
		}
		while (matcher.find()) {
			String articleUrlTitle = matcher.group();

			// System.out.println(articleUrlRegex);
			// System.out.println(articleUrlTitle);

			int s = articleUrlTitle.indexOf(articleUrlStartIndex);
			int t = articleUrlTitle.indexOf(articleUrlEndIndex);

			System.out.println("articleUrlStartIndex: " + s);
			System.out.println("articleUrlEndIndex: " + t);

			int start = s + articleUrlStartOffset;
			int end = t - articleUrlEndOffset;
			if (start > end) {
				continue;
			}
			String articleUrl = articleUrlTitle.substring(start, end);

			// System.out.println(articleUrl);

			String articleTitle = articleUrlTitle.substring(
					articleUrlTitle.indexOf(articleTitleStartIndex)
							+ articleTitleStartOffset,
					articleUrlTitle.indexOf(articleTitleEndIndex)
							- articleTitleEndOffset);

			// System.out.println(articleTitle);
			// [0] url [1] title
			// System.out.println(articleUrl + " " + articleTitle);
			articleUrlsTitles.add(new String[] { articleUrl, articleTitle });
		}

		return articleUrlsTitles;
	}

	/**
	 * 
	 * @param url
	 * @param start
	 * @param end
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getArticleContentUseHttpGet(String url, String start,
			String end) throws ClientProtocolException, IOException {
		// String artcileContentWithOKImgSrc = null;
		String result = Tools.httpGet(url);
		// String result = Tools.driverGet(url);
		// System.out.println(result);

		if (result != null) {
			int startIndex = result.indexOf(start);
			int endIndex = result.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex != -1 && endIndex != -1) {
				result = result.substring(startIndex, endIndex);
			} else {
				result = "Out Of Index";
			}
			// String imgSrcA = "src=\"/files/attachments";
			// String imgSrcB =
			// "src=\"http://www.taobaotest.com/files/attachments";
			// artcileContentWithOKImgSrc = articleContent.replace(imgSrcA,
			// imgSrcB);
		} else {
			result = "NULL";
		}
		return result;

	}

	public static String getArticleContentUseDriverGet(String url,
			String start, String end) throws ClientProtocolException,
			IOException {
		// String artcileContentWithOKImgSrc = null;
		// String result = Tools.httpGet(url);
		String result = Tools.driverGet(url);
		// System.out.println(result);

		if (result != null) {
			int startIndex = result.indexOf(start);
			int endIndex = result.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex != -1 && endIndex != -1) {
				result = result.substring(startIndex, endIndex);
			} else {
				result = "Out Of Index";
			}
			// String imgSrcA = "src=\"/files/attachments";
			// String imgSrcB =
			// "src=\"http://www.taobaotest.com/files/attachments";
			// artcileContentWithOKImgSrc = articleContent.replace(imgSrcA,
			// imgSrcB);
		} else {
			result = "NULL";
		}
		return result;

	}

}
