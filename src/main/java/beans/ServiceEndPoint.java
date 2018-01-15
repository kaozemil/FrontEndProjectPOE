package beans;

import org.codehaus.jackson.map.ObjectMapper;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Emil on 2018-01-03.
 */
@Named("endPoint")
@SessionScoped
public class ServiceEndPoint implements Serializable{

    public ServiceEndPoint(){
        /*try {
            loadServices();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private Service service;
    private ArrayList<Service> services;
    private String serviceType;
    private String league;
    private String inGameName;
    private String title;
    private int results = 0;
    private long removeDate;


    public void loadServices() throws IOException{
        String getServicesURL = "http://194.28.123.207:8080/poe.service/resource/service?serviceType="+serviceType+"&league="+league;
        services = new ArrayList<Service>();

        //Opel url connection and set crud property
        HttpURLConnection connection = getHttpURLConnection(getServicesURL, "GET");

        //Make sure the response is not catastrophic
        if(connection.getResponseCode()!=200){
            throw new IOException("API Error. Could not fetch your services.");
        }

        //Store response from API
        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String out;
        String responseString ="";
        while((out = br.readLine())!=null){
            responseString += out;
        }

        //Create objects from response
        //JSONObject responseObject = new JSONObject(responseString);
        if(!responseString.equals("No service found.")){
        JSONArray jsonArray = new JSONArray(responseString);
        int j = jsonArray.length();
        results = j;
        for (int i = 0; i < j; ++i){
            JSONObject currentService = jsonArray.getJSONObject(i);
            service = new Service();
            service.setInGameName(currentService.getString("inGameName"));
            service.setLeague(currentService.getString("league"));
            service.setServiceType(currentService.getString("serviceType"));
            service.setTitle(currentService.getString("title"));

            services.add(service);
        }} else {
            services.clear();
            results = 0;
        }
    }

    public void createService() throws IOException{
        String createServiceURL = "http://194.28.123.207:8080/poe.service/resource/service";

        service = new Service();
        service.setTitle(title);
        service.setServiceType(serviceType);
        service.setLeague(league);
        service.setInGameName(inGameName);
        service.setRemoveDate(removeDate);

        //Opel url connection and set crud property
        HttpURLConnection postUrlConnection = getHttpURLConnection(createServiceURL, "POST");

        ObjectMapper mapper = new ObjectMapper();
        String jsonUser = mapper.writeValueAsString(service);
        service.setTitle("");
        service.setInGameName("");
        service.setRemoveDate(0);

        PrintWriter out = new PrintWriter(postUrlConnection.getOutputStream());
        out.println(jsonUser);
        out.close();
        if(postUrlConnection.getResponseCode()!=200){
            throw new IOException("API Error. Incorrect response from server.");
        }


    }

    private HttpURLConnection getHttpURLConnection(String pathURL, String type) throws IOException {
        URL url = new URL(pathURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-type", MediaType.APPLICATION_JSON);
        connection.setRequestMethod(type);
        connection.setRequestProperty("Accept",MediaType.APPLICATION_JSON);
        return connection;
    }

    public String CollectInGameName(int serviceListIndex){
        String gameName = services.get(serviceListIndex).getInGameName();
        return gameName;
    }

    public void copyToClipboard(String gameName){

    }


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getInGameName() {
        return inGameName;
    }

    public void setInGameName(String inGameName) {
        this.inGameName = inGameName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(long removeDate) {
        this.removeDate = removeDate;
    }
}
