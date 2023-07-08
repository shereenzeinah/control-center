package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.javainuse.api.incident.ws.WSFetchIncidentResponse;
import com.javainuse.api.incident.ws.WSIncidentRequest;
import com.javainuse.api.incident.ws.WSIncidentResponse;
import java.util.Collections;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    KafkaTemplate<String, WSIncidentRequest> KafkaJsontemplate;

    RestTemplate restTemplate = new RestTemplate();;
    private final String CREATE_INCIDENT_TOPIC = "create-incident";
    private final String UPDATE_INCIDENT_TOPIC = "update-incident";
    private final String DELETE_INCIDENT_TOPIC = "delete-incident";
    private final String FETCH_INCIDENTS_URL = "http://localhost:8280/incident/1.0.0";

    @Override
    public WSIncidentResponse createIncident(WSIncidentRequest wsIncidentRequest) {
        KafkaJsontemplate.send(CREATE_INCIDENT_TOPIC, wsIncidentRequest);
        WSIncidentResponse response = new WSIncidentResponse("Created successfully");
        return response;
    }

    @Override
    public WSIncidentResponse updateIncident(WSIncidentRequest wsIncidentRequest) {
        KafkaJsontemplate.send(UPDATE_INCIDENT_TOPIC, wsIncidentRequest);
        WSIncidentResponse response = new WSIncidentResponse("Updated successfully");
        return response;
    }

    @Override
    public WSIncidentResponse deleteIncident(String id) {
        WSIncidentRequest wsDeleteRequest = new WSIncidentRequest();
        wsDeleteRequest.setId(id);
        KafkaJsontemplate.send(DELETE_INCIDENT_TOPIC, wsDeleteRequest);
        WSIncidentResponse response = new WSIncidentResponse();
        response.setResponseMessage("Deleted successfully");
        return null;
    }

    @Override
    public WSFetchIncidentResponse[] fetchIncidents() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5UZG1aak00WkRrM05qWTBZemM1TW1abU9EZ3dNVEUzTVdZd05ERTVNV1JsWkRnNE56YzRaQT09In0.eyJhdWQiOiJodHRwOlwvXC9vcmcud3NvMi5hcGltZ3RcL2dhdGV3YXkiLCJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhcHBsaWNhdGlvbiI6eyJvd25lciI6ImFkbWluIiwidGllclF1b3RhVHlwZSI6InJlcXVlc3RDb3VudCIsInRpZXIiOiIxMFBlck1pbiIsIm5hbWUiOiJJbmNpZGVudEhhbmRsaW5nIiwiaWQiOjIsInV1aWQiOm51bGx9LCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJ0aWVySW5mbyI6eyJCcm9uemUiOnsidGllclF1b3RhVHlwZSI6InJlcXVlc3RDb3VudCIsInN0b3BPblF1b3RhUmVhY2giOnRydWUsInNwaWtlQXJyZXN0TGltaXQiOjAsInNwaWtlQXJyZXN0VW5pdCI6bnVsbH19LCJrZXl0eXBlIjoiUFJPRFVDVElPTiIsInN1YnNjcmliZWRBUElzIjpbeyJzdWJzY3JpYmVyVGVuYW50RG9tYWluIjoiY2FyYm9uLnN1cGVyIiwibmFtZSI6IkZldGNoSW5jaWRlbnRzIiwiY29udGV4dCI6IlwvaW5jaWRlbnRcLzEiLCJwdWJsaXNoZXIiOiJhZG1pbiIsInZlcnNpb24iOiIxIiwic3Vic2NyaXB0aW9uVGllciI6IkJyb256ZSJ9XSwiY29uc3VtZXJLZXkiOiJsempjY3dwQzhlbThVZlMxeDVJYmV4dkpwbFFhIiwiZXhwIjoxNjgxNzUwMTU2LCJpYXQiOjE2ODE3NDIxNTYsImp0aSI6ImIwZmFkNDU4LWQzNWItNDcyOC05YjM3LTljM2MxZTVkMGFlYiJ9.iIV5ExTlfZhzsLc37vKe1B1FEFJn1r-qg_xuZqb-fqcQHIGqA2R7CIkxHUvGYH6vR3wP0arsHHRwXm-Wud2AC1gDbZ2fwHnPotljq5AND2W1RJGZWjsp88ZP3-M9MNP_07e1dy9VqwZ-jWimgtPV5Px10ERrOjmAjmlfcQComvGU8oB3BQb-cl5iAOzwHScVDRPbV17_mgCMSWBLzzenKKCAJcH75nSPj9jnGUZwZYm3Ly7TcLrw9UUWrx0uZnZCjvutKLoysX_ywYHuoofBiWUDbD8uUJCeH8Vyp3hIWSgqyEdnvjZupKj9em6XXU8zbXaZdXgL1aZJTD8vnSxZsw";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<WSFetchIncidentResponse[]> response = restTemplate.exchange(FETCH_INCIDENTS_URL, HttpMethod.GET,
                entity, WSFetchIncidentResponse[].class);
        return response.getBody();
    }
}
