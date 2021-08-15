/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 * 
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the 
 * same hostname as startUrl. 

Return all urls obtained by your web crawler in any order.

Your crawler should:

Start from the page: startUrl
Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
Do not crawl the same link twice.
Explore only the links that are under the same hostname as startUrl.

As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use 
http protocol without any port specified. For example, the urls http://leetcode.com/problems and 
http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and 
http://example.com/abc are not under the same hostname.

The HtmlParser interface is defined as such: 

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  public List<String> getUrls(String url);
}
Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have 
three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, 
while urls and edges are not directly accessible to you in code.
 * 
 */

class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // to store unique urls in bfs path
        Set<String> uniqueUrls = new HashSet<String>();
        // do bfs from start url
        Queue<String> queue = new LinkedList<>();
        queue.offer(startUrl);
        String hostname = startUrl.split("/")[2];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String url = queue.poll();
                // to do bfs on each url only once
                if (uniqueUrls.contains(url)) {
                    continue;
                }
                uniqueUrls.add(url);
                for (String childUrl : htmlParser.getUrls(url)) {
                    // check for same hostname as start
                    String childHostName = childUrl.split("/")[2];
                    if (childHostName.equals(hostname))
                        queue.offer(childUrl);
                }
            }
        }
        // convert set to list
        List<String> ans = new ArrayList<>();
        for (String s : uniqueUrls) {
            ans.add(s);
        }
        return ans;
    }
}