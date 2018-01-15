package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Emil on 2018-01-03.
 */
@SessionScoped
@Named("service")
public class Service implements Serializable {
    private String title;
    private String inGameName;
    private String ServiceType;
    private String League;
    private long removeDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInGameName() {
        return inGameName;
    }

    public void setInGameName(String inGameName) {
        this.inGameName = inGameName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getLeague() {
        return League;
    }

    public void setLeague(String league) {
        League = league;
    }

    public long getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(long removeDate) {
        this.removeDate = removeDate;
    }
}
