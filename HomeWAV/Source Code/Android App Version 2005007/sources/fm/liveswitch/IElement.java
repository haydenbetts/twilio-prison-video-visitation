package fm.liveswitch;

public interface IElement {
    String getExternalId();

    String getId();

    String getPipelineJson();

    void setExternalId(String str);
}
