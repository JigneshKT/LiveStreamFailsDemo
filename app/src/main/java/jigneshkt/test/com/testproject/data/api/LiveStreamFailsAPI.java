package jigneshkt.test.com.testproject.data.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LiveStreamFailsAPI {

    @GET("load/loadPosts.php")
    Observable<ResponseBody> getLiveStreamFails(
            @Query("loadPostMode")String loadPostMode,
            @Query("loadPostNSFW")String loadPostNSFW,
            @Query("loadPostOrder")String loadPostOrder,
            @Query("loadPostPage")String loadPostPage,
            @Query("loadPostTimeFrame")String loadPostTimeFrame
    );
}
