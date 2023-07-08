package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javainuse.api.incident.ws.WSFetchIncidentResponse;
import com.javainuse.api.incident.ws.WSIncidentRequest;
import com.javainuse.api.incident.ws.WSIncidentResponse;
import com.javainuse.service.IncidentService;

@RestController
@RequestMapping("/incident")
public class IncidentApi {

    private IncidentService incidentService;

    @PostMapping(path = "/create-incident", produces = "application/json", consumes = "application/json")
    public ResponseEntity<WSIncidentResponse> createIncident(@RequestBody WSIncidentRequest wsIncidentRequest) {
        WSIncidentResponse response = incidentService.createIncident(wsIncidentRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/update-incident", produces = "application/json", consumes = "application/json")
    public ResponseEntity<WSIncidentResponse> updateIncident(@RequestBody WSIncidentRequest wsIncidentRequest) {
        WSIncidentResponse response = incidentService.updateIncident(wsIncidentRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<WSIncidentResponse> deletePost(@PathVariable String id) {
        WSIncidentResponse response = incidentService.deleteIncident(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<WSFetchIncidentResponse[]> getAllIncidents() {
        WSFetchIncidentResponse[] response = incidentService.fetchIncidents();
        return ResponseEntity.ok(response);
    }

    @Autowired
    public void setIncidentService(IncidentService incidentService) {
        this.incidentService = incidentService;
    }
}
