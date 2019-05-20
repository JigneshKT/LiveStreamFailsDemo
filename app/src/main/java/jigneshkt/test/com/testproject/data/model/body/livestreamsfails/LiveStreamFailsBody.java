package jigneshkt.test.com.testproject.data.model.body.livestreamsfails;

public class LiveStreamFailsBody {

    private String loadPostMode;
    private String loadPostNSFW;
    private String loadPostOrder;
    private String loadPostPage;
    private String loadPostTimeFrame;

    public String getLoadPostMode() {
        return loadPostMode;
    }

    public String getLoadPostNSFW() {
        return loadPostNSFW;
    }

    public String getLoadPostOrder() {
        return loadPostOrder;
    }

    public String getLoadPostPage() {
        return loadPostPage;
    }

    public String getLoadPostTimeFrame() {
        return loadPostTimeFrame;
    }




    public LiveStreamFailsBody(String loadPostMode,String loadPostNSFW,String loadPostOrder,
                               String loadPostPage, String loadPostTimeFrame){
        this.loadPostMode = loadPostMode;
        this.loadPostNSFW = loadPostNSFW;
        this.loadPostOrder = loadPostOrder;
        this.loadPostPage = loadPostPage;
        this.loadPostTimeFrame = loadPostTimeFrame;
    }

}
