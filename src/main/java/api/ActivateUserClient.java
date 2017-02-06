package api;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt-hfc on 1/23/17.
 *
 * See this ticket for further information: https://happyfun.atlassian.net/browse/ILL-3313
 */
public class ActivateUserClient
{

    private static final String userEndpoint = "http://vdb-stage.herokuapp.com/v1/activate_user";
    private static final String contentType = "application/x-www-form-urlencoded";
    private static final String secretToken = "happyhappy";

    public static boolean setUserActiveStatus(User user)
    {
        try
        {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPatch httpPatch = new HttpPatch(userEndpoint);
            httpPatch.setHeader("content-type", contentType);

            List<NameValuePair> paramsList = new ArrayList<NameValuePair>(3);
            // Add all params listed in User object
            paramsList.add(new BasicNameValuePair ("user[email]", user.getEmail()) );
            paramsList.add(new BasicNameValuePair ("user[active]", ( String.valueOf(user.isActive()) )));
            paramsList.add(new BasicNameValuePair ("user[secret_token]", secretToken ));
            
            httpPatch.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPatch);
            if(response.getStatusLine().getStatusCode() == 200) return true;
            else return false;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
