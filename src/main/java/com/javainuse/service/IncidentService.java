package com.javainuse.service;

import com.javainuse.api.incident.ws.WSFetchIncidentResponse;
import com.javainuse.api.incident.ws.WSIncidentRequest;
import com.javainuse.api.incident.ws.WSIncidentResponse;

public interface IncidentService {

     WSIncidentResponse createIncident(WSIncidentRequest wsIncidentRequest);

     WSIncidentResponse updateIncident(WSIncidentRequest wsIncidentRequest);

     WSIncidentResponse deleteIncident(String id);

     WSFetchIncidentResponse[] fetchIncidents();
}
