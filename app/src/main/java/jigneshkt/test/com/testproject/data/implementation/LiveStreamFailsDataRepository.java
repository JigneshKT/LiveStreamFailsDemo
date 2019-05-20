package jigneshkt.test.com.testproject.data.implementation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import io.reactivex.Observable;
import jigneshkt.test.com.testproject.data.api.LiveStreamFailsAPI;
import jigneshkt.test.com.testproject.data.model.body.livestreamsfails.LiveStreamFailsBody;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;
import jigneshkt.test.com.testproject.domain.repository.LiveStreamFailsRepository;

public class LiveStreamFailsDataRepository implements LiveStreamFailsRepository {
    //https://livestreamfails.com/load/loadPosts.php?loadPostMode=standard&loadPostNSFW=0&loadPostOrder=hot&loadPostPage=0&loadPostTimeFrame=day
    private String endPointURL = "https://livestreamfails.com/load/loadPosts.php";
    private LiveStreamFailsAPI liveStreamFailsAPI;
    public LiveStreamFailsDataRepository(LiveStreamFailsAPI liveStreamFailsAPI){
        this.liveStreamFailsAPI = liveStreamFailsAPI;
    }


    @Override
    public Observable<ArrayList<LiveStreamFail>> getLiveStreamsFails() {

        ArrayList<LiveStreamFail> liveStreams = new ArrayList<>();
        LiveStreamFailsBody liveStreamFailsBody = getLiveStreamFailsBody();
        return liveStreamFailsAPI.getLiveStreamFails(liveStreamFailsBody.getLoadPostMode(),
                    liveStreamFailsBody.getLoadPostNSFW(),liveStreamFailsBody.getLoadPostOrder(),liveStreamFailsBody.getLoadPostPage(),
                    liveStreamFailsBody.getLoadPostTimeFrame()).flatMap(responseBody -> {

                 String html = responseBody.string();
                 Document document = Jsoup.parse(html);

                 Elements elements = document.select("div.post-card");
                 for (Element element : elements) {
                     String title = element.selectFirst("p.title").text();

                     String postElement = element.selectFirst("a[href]").attr("href");
                     String postId=null;
                     if(postElement!=null){
                         String[] splits = postElement.split("/");
                         postId = splits[splits.length-1];
                     }

                     String thumbnailUrl = element.selectFirst("img.card-img-top").attr("src");

                     String streamerName = null;
                     if(element.selectFirst("div.stream-info > small.text-muted")!=null &&
                             element.selectFirst("div.stream-info > small.text-muted").select("a[href]").size()>0){
                         streamerName = element.selectFirst("div.stream-info > small.text-muted").select("a[href]").get(0).text();
                     }
                     String gameName = null;

                     if(element.selectFirst("div.stream-info > small.text-muted")!=null &&
                             element.selectFirst("div.stream-info > small.text-muted").select("a[href]").size()>1){
                         gameName =  element.selectFirst("div.stream-info > small.text-muted").select("a[href]").get(1).text();
                     }

                     Boolean isNsfw = element.selectFirst("span.oi-warning") != null;

                     Element pointsElement = element.selectFirst("small.text-muted > span.oi-arrow-circle-top").parent();
                     String points = null;
                     if(pointsElement!=null){
                         points = pointsElement.ownText();
                     }

                     LiveStreamFail liveStreamFail = new LiveStreamFail();
                     liveStreamFail.setGame(gameName);
                     liveStreamFail.setNsfw(isNsfw);
                     liveStreamFail.setPoints(points);
                     liveStreamFail.setPostId(postId);
                     liveStreamFail.setThumbnailUrl(thumbnailUrl);
                     liveStreamFail.setTitle(title);
                     liveStreams.add(liveStreamFail);

                 }
                 return  Observable.just(liveStreams);

             });

    }


    private LiveStreamFailsBody getLiveStreamFailsBody(){
        return new LiveStreamFailsBody("standard","0","hot","0","day");
    }
}
