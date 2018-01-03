package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 2018-01-03.
 */
@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {

    private ArrayList<String> leagueList;
    private ArrayList<String> serviceList;

    @PostConstruct
    public void init(){
        leagueList = new ArrayList<String>();
        leagueList.add("Abyss");
        leagueList.add("Hardcore Abyss");
        leagueList.add("Standard");
        leagueList.add("Hardcore");

        serviceList = new ArrayList<String>();
        serviceList.add("Boosting");
        serviceList.add("Rotation");
        serviceList.add("Crafting");
        serviceList.add("Teaming");
    }

    public ArrayList<String> getLeagueList() {
        return leagueList;
    }

    public ArrayList<String> getServiceList() {
        return serviceList;
    }

    public void getServices(){

    }

}
