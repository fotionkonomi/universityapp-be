package al.edu.fti.softwareengineering.universityappbe.api.security.service;

import al.edu.fti.softwareengineering.universityappbe.api.security.model.AuthenticationRequest;
import al.edu.fti.softwareengineering.universityappbe.api.security.model.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
