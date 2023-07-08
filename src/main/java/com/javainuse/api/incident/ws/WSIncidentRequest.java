package com.javainuse.api.incident.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WSIncidentRequest {
    private String id;
    private String title;
    private String address;
    private String phone;
    private String description;
    private String createdDate;
}
